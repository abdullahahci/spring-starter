package com.ahci.springstarter.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@RequestMapping("/login")
	public String showForm(Model theModel) {

		return "login";
	}
}
