package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean changePassword(String eid, String newPassword) {
		int updated = userRepository.updatePassword(eid, newPassword);
		return updated > 0; // 更新成功ならtrue
	}
}