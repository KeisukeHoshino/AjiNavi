package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.ReviewEditForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {

//	レビュー編集画面表示リクエスト
	@PostMapping("/show-edit-form")
	public String showEditForm(@ModelAttribute ReviewEditForm form) {
		return "edit-review";
	}
	
//	レビュー更新リクエスト（登録画面より）
	@PostMapping("/edit-review")
	public String editReview(@Validated @ModelAttribute ReviewEditForm form, BindingResult result) {
		
		// 入力エラーがある場合には、レビュー登録画面に戻す
		if(result.hasErrors()) {
			return "edit-review";
		}
		
		// 正常な場合に、レビュー登録確認画面に遷移する
		return "confirm-edit-review";
	}
	
//	レビュー更新リクエスト（編集確認画面より）
	@PostMapping("/confirm-edit-review")
	public String confirmEditReview(@Validated ReviewEditForm form,BindingResult result, RedirectAttributes redirectAttributes) {
		
		// 入力エラーがある場合には、レビュー編集画面に戻す
		if(result.hasErrors()) {
			return "edit-review";
		}

		ReviewEditForm r = new ReviewEditForm();
		r.setReviewId(form.getReviewId());
		r.setRestaurantId(form.getRestaurantId());
		r.setRestaurantName(form.getRestaurantName());
		r.setUserId(form.getUserId());
		r.setVisitDate(form.getVisitDate());
		r.setRating(form.getRating());
		r.setComment(form.getComment());
		
//		暫定で表示
		System.out.println("--レヴュー更新");
		System.out.println(r);
		
		redirectAttributes.addFlashAttribute("msg", "（レビュー更新）");
		
		return "redirect:/complete";
	}
}
