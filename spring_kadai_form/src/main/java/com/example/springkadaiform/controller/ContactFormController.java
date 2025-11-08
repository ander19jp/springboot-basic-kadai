package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	
	
//	フォーム画面を表示
	
	@GetMapping("/form")
	public String showForm(@ModelAttribute ContactForm contactForm, Model model) {
		return "contactFormView";
	}
	
//	 * 確認画面を表示（バリデーション処理）
	
	@PostMapping("/confirm")
	public String confirmForm(@Validated @ModelAttribute ContactForm contactForm,
							BindingResult bindingResult,Model model) {
		
		// バリデーションエラーがある場合はフォーム画面に戻る
		if(bindingResult.hasErrors() ) {
			return "contactFormView";
		}
		
		// エラーがなければ確認画面へ
		return "confirmView";
	}

}
