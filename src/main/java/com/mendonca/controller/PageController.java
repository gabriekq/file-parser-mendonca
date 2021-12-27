package com.mendonca.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendonca.modell.Person;
import com.mendonca.service.FileService;


@Controller
@RequestMapping("/")
public class PageController {

	private int pageNumber = 0;
	private long numberOfPages = 0;
	
	
	
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping("home")  //  http://localhost:8080/home
	public String homePage(Model model) {
		
		return "homePage";	
	}
	
	@RequestMapping("fileUp") 
	public String fileUpload() {	
		return "uploadFile";
	}

	

	
	
	
	
}
