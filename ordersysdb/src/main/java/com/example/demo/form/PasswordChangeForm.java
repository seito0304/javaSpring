package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PasswordChangeForm {

	@NotBlank
	private String eid;

	@NotBlank
	private String newPassword;
}