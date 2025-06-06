package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserInputForm;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserController {

	private final UserRepository userRepository;

	// ユーザー登録フォーム表示
	@GetMapping("/userInputForm")
	public String showUserInputForm(UserInputForm userInputForm) {
		return "userInputForm";
	}

	// ユーザー登録処理
	@PostMapping("/userRegister")
	public String registerUser(
			@ModelAttribute @Validated UserInputForm userInputForm,
			BindingResult result) {

		if (!result.hasErrors()) {
			userRepository.insertUser(
					userInputForm.getEid(),
					userInputForm.getEname(),
					userInputForm.getPassword(),
					userInputForm.getMailaddress(),
					userInputForm.getStation(),
					userInputForm.getZip(),
					userInputForm.getAddress(),
					userInputForm.getTel(),
					userInputForm.getSendchk(),
					userInputForm.getPossibleamt());
			return "redirect:/userInputForm?success";
		} else {
			return "userInputForm";
		}
	}
}