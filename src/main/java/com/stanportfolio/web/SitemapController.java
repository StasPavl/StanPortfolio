package com.stanportfolio.web;

import com.stanportfolio.utils.Logger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class SitemapController {
    @GetMapping({"/","/home","/index"})
    public String getHomePage(HttpServletRequest request){
        var message = "Remote connection from user: %s (%s)".formatted(request.getRemoteUser(), request.getRemoteAddr());
        Logger.getLogger().printMessage(message);
        return "index";
    }
}
