package com.mendonca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendonca.modell.Person;
import com.mendonca.service.FileService;
@RestController
@RequestMapping("data")
public class ManageDataController {

	private int currentPage = 0;
	
	@Autowired
    private	FileService fileService;
	
	
	
	@RequestMapping("/getDataPerson/{pageMode}")
	public List<Person> getDataPerson(@PathVariable("pageMode")  String pageMode){
		System.out.println(currentPage);
		
		if(pageMode.equalsIgnoreCase("next")) {
			currentPage++;
			return fileService.getPersonsbyPage(currentPage);
		}
		
		if(pageMode.equalsIgnoreCase("back")) {
			currentPage--;
			return fileService.getPersonsbyPage(currentPage);
		}else {
			return fileService.getPersonsbyPage(currentPage);
		}
	}
	
	@GetMapping("/getCurrentPageIndex")
	public int getCurrentPageIndex() {
		return currentPage;
	}
	
	@GetMapping("/getTotalOfPages")
	public int getTotalOfPages() {
		return (int) (fileService.getCountPerson()/10);
	}
	
	
}
