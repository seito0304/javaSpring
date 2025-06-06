package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.form.PersonForm;

@Service
public class PersonService {
	public void check(PersonForm personForm,
			BindingResult result) {
		String inputName = personForm.getName();
		if (inputName != null && !"".equals(inputName)) {
			if (inputName.strip().length() == 0) {
				result.addError(
						new FieldError(result.getObjectName(), "name", "名前が全角スペースです。"));
			}
		}
		try {
			int inputAge = Integer.parseInt(personForm.getAge());
			if (inputAge < 18 || inputAge >= 30) {
				result.addError(
						new FieldError(result.getObjectName(), "age", "年齢が範囲外です。"));
			}
		} catch (Exception e) {
			result.addError(
					new FieldError(result.getObjectName(), "age", "年齢が不正です。"));
		}
	}

}