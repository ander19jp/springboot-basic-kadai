package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoService;

@Controller
public class ToDoController {
	
	private final ToDoService toDoService;
	
	// コンストラクタインジェクション
	public  ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}
	
	// ToDoリスト一覧表示
	@GetMapping("/todo")
	public String shouwToDoList(Model model) {
		
		// 全てのToDoを取得
		List<ToDo> todos = toDoService.findAll();
		
		// ビューにデータを渡す
		model.addAttribute("todos",todos);
		
		// todoView.htmlを表示
		return "todoView";
	}

}
