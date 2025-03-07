package com.invs.invitationssb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping({"/admin", "/login", "/"})
	public String forwardReactRoutes() {
		return "forward:/index.html";
	}
}
