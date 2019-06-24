package com.ahci.springstarter.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/admin")
public class AdminErrorController implements ErrorController {

    public AdminErrorController() {}

    @GetMapping(value = "/404")
    public String handleError(HttpServletRequest request) {
        return "404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
