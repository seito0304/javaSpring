package com.example.demo.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PersonForm {
	
	@NotBlank(message = "名前が入力されていません。")
	@Size(max = 30, message = "名前は 30 文字以下で入力してください。")
	private String name;
	@NotNull(message = "年齢が入力されていません。")
	@Min(value = 18, message = "年齢は 18 以上である必要があります。")
	private String age;
	@NotBlank(message = "ニックネームが入力されていません。")
	@Size(max = 30, message = "ニックネームは 30 文字以下で入力してください。")
	private String nickname;
}
