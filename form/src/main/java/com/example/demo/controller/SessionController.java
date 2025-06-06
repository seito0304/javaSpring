package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SessionController {
	private final HttpSession session;

	@GetMapping("/sessionForm")
	public String index() {
		Integer count = (Integer) session.getAttribute("count");
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		session.setAttribute("count", count);

		return "sessionForm";
	}

	@GetMapping("/sessionLogout")
	public String logout() {
		session.removeAttribute("count");
		return "sessionForm";
	}
}