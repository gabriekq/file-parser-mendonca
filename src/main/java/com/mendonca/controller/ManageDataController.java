package com.mendonca.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			currentPage =0 ;
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
	
	
	@GetMapping("/search")
	public List<Person> searchPerson(@RequestParam("id")  String id){
		
		List<Person> personsFound = null;

		personsFound = fileService.searchPersonByRangeId(id);
		return personsFound;
	}
	
	
	
	
	
	
	
}
