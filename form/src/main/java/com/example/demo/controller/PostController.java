package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.PersonForm;
import com.example.demo.service.PersonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class PostController {
	private final PersonService personService;

	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return "form";
	}

	@PostMapping("/result")
	public String checkResult(@ModelAttribute @Validated PersonForm personForm,
			BindingResult result) {
		personService.check(personForm, result);
		if (!result.hasErrors()) {
			return "results";
		} else {
			return "form";
		}
	}
}