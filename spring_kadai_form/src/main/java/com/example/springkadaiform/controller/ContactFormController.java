package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
    
    /**
     * フォームの表示
     */
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contactFormView";
    }
    
    /**
     * フォーム送信処理
     */
    @PostMapping("/form")
    public String submitForm(
        @Validated @ModelAttribute ContactForm contactForm,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        // バリデーションエラーがある場合は入力画面に戻る
        if (result.hasErrors()) {
            model.addAttribute("contactForm", contactForm);
            return "contactFormView";
        }
        
        // 正常時：contactFormを保存してリダイレクト
        redirectAttributes.addFlashAttribute("contactForm", contactForm);
        return "redirect:/confirm";
    }
    
    /**
     * 確認画面の表示
     */
    @GetMapping("/confirm")
    public String showConfirm(
        @ModelAttribute("contactForm") ContactForm contactForm,
        Model model
    ) {
        // 直接/confirmにアクセスされた場合はフォームに戻す
        if (contactForm == null || contactForm.getName() == null) {
            return "redirect:/form";
        }
        return "confirmView";
    }
}