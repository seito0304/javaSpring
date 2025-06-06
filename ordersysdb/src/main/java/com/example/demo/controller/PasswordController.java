package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.PasswordChangeForm;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showPasswordChangeForm(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "password_change"; // テンプレート名：templates/password_change.html
    }

    @PostMapping
    public String changePassword(@ModelAttribute @Valid PasswordChangeForm form,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "password_change";
        }

        boolean success = userService.changePassword(form.getEid(), form.getNewPassword());

        if (success) {
            // 成功時は /password/complete にリダイレクト
            return "redirect:/password/complete";
        } else {
            model.addAttribute("message", "パスワードの変更に失敗しました（EIDが見つかりません）。");
            return "password_change";
        }
    }
    @GetMapping("/complete")
    public String showCompletePage() {
        return "password_complete";  // ← このテンプレートを用意
    }
}