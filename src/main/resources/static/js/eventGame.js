//캔버스 세팅
let canvas;
//이미지 그리는 거 도와주는 변수
let ctx;
canvas = document.createElement("canvas")
ctx = canvas.getContext("2d")
canvas.width = 400;
canvas.height = 700;
document.body.appendChild(canvas);

let backgroundImage,spaceshipImage,bulletImage,enemyImage,gameOverImage;
let gameOver = false;

let spaceshipX = canvas.width/2-32;
let spaceshipY = canvas.height-64;
let score = 0;
let bulletList = [];
function Bullet() {
    this.x = 0;
    this.y = 0;
    this.init = function () {
        this.x = spaceshipX+20;
        this.y = spaceshipY;
        this.alive = true;

        bulletList.push(this);
    };
    this.update = function (){
        this.y -= 7;
    };

    this.checkHit = function (){
        for (let i = 0; i<enemyList.length; i++){
            if (this.y <= enemyList[i].y &&
                this.x+8 >= enemyList[i].x &&
                this.x <= enemyList[i].x+20){
                score++;
                this.alive = false;
                enemyList.splice(i,1);
            }
        }
    }
}

function generateRandomValue(min, max) {
    let randomNum = Math.floor(Math.random()*(max-min+1))+min;
    return randomNum;
}

let enemyList = [];
function Enemy() {
    this.x = 0;
    this.y = 0;
    this.init = function (){
        this.y = 0;
        this.x = generateRandomValue(10, canvas.width-42);
        enemyList.push(this);
    };
    this.update = function (){
        this.y += 1.5;

        if (this.y >= canvas.height-32){
            gameOver = true;
            console.log("gameover")
        }
    }
}

function loadImage(){
    backgroundImage = new Image();
    backgroundImage.src = "../static/images/background.jpg";

    spaceshipImage = new Image();
    spaceshipImage.src = "../static/images/spaceship.png";

    bulletImage = new Image();
    bulletImage.src = "../static/images/bullet.png";

    enemyImage = new Image();
    enemyImage.src = "../static/images/enemy.png";

    gameOverImage = new Image();
    gameOverImage.src = "../static/images/gameOver.jpg";
}

let keysDown = {};
function setupKeyboardListener() {
    document.addEventListener("keydown", function(event){
        keysDown[event.key] = true;
        console.log("??", keysDown);
    });

    document.addEventListener("keyup", function (event){
        delete keysDown[event.key];

        if (event.keyCode == 32){
            createBullet();
        }
    });
}

function createBullet() {
    console.log("bullet")
    let b = new Bullet();
    b.init();
}

function createEnemy() {
    const interval = setInterval(function (){
        let e = new Enemy();
        e.init();
    }, 1500)
}

function update() {
    if ("ArrowRight" in keysDown){
        spaceshipX += 2;
    }
    if ("ArrowLeft" in keysDown){
        spaceshipX -= 2;
    }
    if (spaceshipX <= 0 ){
        spaceshipX = 0;
    }
    if (spaceshipX >= canvas.width-64){
        spaceshipX = canvas.width-64;
    }

    for(let i =0; i<bulletList.length; i++){
        if (bulletList[i].alive) {
            bulletList[i].update();
            bulletList[i].checkHit();
        }
    }

    for (let i =0; i<enemyList.length; i++){
        enemyList[i].update();
    }
}

//그려주기
function render() {
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
    ctx.drawImage(spaceshipImage, spaceshipX, spaceshipY);
    ctx.fillText(`Score:${score}`, 10, 25);
    ctx.fillStyle = "white";
    ctx.font = "20px Arial"

    for (let i =0; i<bulletList.length; i++){
        if (bulletList[i].alive) {
            ctx.drawImage(bulletImage, bulletList[i].x, bulletList[i].y);
        }
    }

    for (let i =0; i<enemyList.length; i++){
        ctx.drawImage(enemyImage, enemyList[i].x, enemyList[i].y);
    }
}

function main() {
    if (gameOver == false) {
        update();
        render();
        requestAnimationFrame(main);
    }else{
        ctx.drawImage(gameOverImage,10,100,380,380);
    }
}

loadImage();
setupKeyboardListener();
createEnemy();
main();

// 1. 스페이스바 누르면
// 2. Y좌표 줄어든다 ( -16 보다 작아지면 사라짐 ) 총알의 x값은 스페이스바 누른 순간의 x값
// 3. 총알 여러 개면 저장할 어레이 만들어야 한다 총알은 각각 x,y좌표값 있어야 한다
// 4. 위의 어레이를 render 한다
// 5. 총알을 만드는 클래스를 만든다 ( function 이용해서 만들거 ) like 명세서

