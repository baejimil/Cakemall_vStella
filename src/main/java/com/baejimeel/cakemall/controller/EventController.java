package com.baejimeel.cakemall.controller;

import com.baejimeel.cakemall.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EventController {

    @GetMapping("/event")
    public String eventPage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getRole().equals("ROLE_USER")) {
            // 구매자
            int userId = principalDetails.getUser().getId();
            model.addAttribute("user", principalDetails.getUser());

            return "event";
        }else {
            return "구매자가 아닙니다 !";
        }
    }

}
