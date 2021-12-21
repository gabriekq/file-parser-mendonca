package com.mendonca.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendonca.modell.Person;
import com.mendonca.modell.PersonFile;
import com.mendonca.repository.FilePersonRepository;
import com.mendonca.repository.PageablePersonRepo;
import com.mendonca.repository.PersonRepository;

@Service
public class FileServiceInpl implements FileService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FilePersonRepository filePersonRepository;
	
	@Autowired
	private PageablePersonRepo pageablePersonRepo;

	@Override
	public void parseUploadedFile(MultipartFile multipartFile) { // save the object into the DB

		try {
			ObjectMapper mapper = new ObjectMapper();
			// InputStream initialStream = multipartFile.getInputStream();

			byte[] data = multipartFile.getBytes();
			Person person = mapper.readValue(data, Person.class);

			person = personRepository.save(person);
			PersonFile personFile = new PersonFile();
			personFile.setData(data);
			personFile.setFileName(multipartFile.getOriginalFilename());
			personFile.setFileType(multipartFile.getContentType());
			personFile.setId(person.getId());
			filePersonRepository.save(personFile);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	
	
	

	@Override
	public Optional<PersonFile> getFileDownload(String id) {
		
		Optional<PersonFile> personFile = 	filePersonRepository.findById( Integer.parseInt(id));
		
		return personFile;
	}

	@Override
	public List<Person> getPersonsbyPage(int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10, Sort.by("id").ascending());
		
		//Pageable page = PageRequest.of(pageNumber, 10);
		Page<Person> allpeaple =  pageablePersonRepo.findAll(page);
		List<Person>  peaple = allpeaple.toList();
		
		//peaple.forEach(p -> System.out.println(p));
		
		return peaple;
		
	}


	@Override
	public Long getCountPerson() {
		return pageablePersonRepo.count();	
	}


	@Override
	public List<Person> searchPersonByRangeId(String id) {
	
	List<Person> persons;	
	String []inputs;
	int idMaior,idMenor;
	
	try {
	
	if(id.contains("-") &&  (id.indexOf("-") != (id.length()-1)  ) ) {
		
		inputs = id.split("([-])+");
		if( Integer.parseInt(inputs[0])  > Integer.parseInt(inputs[1])  ) {
			idMaior = Integer.parseInt(inputs[0]);
			idMenor = Integer.parseInt(inputs[1]);
		}else {
			idMenor = Integer.parseInt(inputs[0]);
			idMaior = Integer.parseInt(inputs[1]);		
		}
	}else {
		
		idMaior = Integer.parseInt(id.split("([-])+")[0]);
		idMenor = Integer.parseInt(id.split("([-])+")[0]);
		
	}
	
	    persons = personRepository.findByIdRange(idMenor, idMaior);
	    return persons;
	}catch (Exception e) {
		return null;
	}
	    
		
	}

	



}
