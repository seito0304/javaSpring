package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserInputForm {

	@NotBlank
	private String eid;

	@NotBlank
	private String ename;

	@NotBlank
	private String password;

	@Email
	@NotBlank
	private String mailaddress;

	private String station;

	private String zip;

	private String address;

	private String tel;

	@NotNull
	private Integer sendchk;

	@NotNull
	private Integer possibleamt;
}