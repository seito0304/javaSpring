package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SessionSampleController {
	private final HttpSession session;

	@PostMapping("/second")
	public String index(@RequestParam("val") String val) {
		session.setAttribute("key", val);
		return "second";
	}

	@PostMapping("/third")
	public String index() {
		return "third";
	}
}