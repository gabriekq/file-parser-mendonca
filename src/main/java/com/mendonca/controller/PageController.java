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
		

		 List<Person>	peaples = fileService.getPersonsbyPage(pageNumber);
		 model.addAttribute("peaple", peaples);
		
		// numberOfPages = (fileService.getCountPerson() % 10) != 0  ? 1 : 0;
		 numberOfPages = (fileService.getCountPerson() / 10) ;
		 
		 model.addAttribute("currentPageNumber", pageNumber);
		 model.addAttribute("numberOfPages", numberOfPages);
		 
		//peaples.forEach(p -> System.out.println(p.getId()));	
		return "homePage";	
	}
	
	@RequestMapping("fileUp") 
	public String fileUpload() {	
		return "uploadFile";
	}

	
	
	@RequestMapping("movePages") 
	public String movePages(Model model,@RequestParam(required=false,name="idN") String idN) {
		
		 List<Person> peaples;
		
		if(idN != null && idN.equalsIgnoreCase("next")) {
			pageNumber++;
			peaples = fileService.getPersonsbyPage(pageNumber);
			model.addAttribute("peaple", peaples);
			
		}
		
		if(idN != null && idN.equalsIgnoreCase("back")) {
			pageNumber--;
			peaples = fileService.getPersonsbyPage(pageNumber);
			model.addAttribute("peaple", peaples);
			
		}
		model.addAttribute("currentPageNumber", pageNumber);
		model.addAttribute("numberOfPages", numberOfPages);
		return "homePage";	
	}
	
	
	
	
	
}
