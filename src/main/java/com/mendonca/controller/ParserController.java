package com.mendonca.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mendonca.modell.PersonFile;
import com.mendonca.service.FileService;

@RestController
@RequestMapping("files")
public class ParserController {

	
	@Autowired
	FileService fileService;
	
	@Autowired
	PageController pageController;
	
	@PostMapping("/upload")
	public void loadFileIntoDataBase(@RequestParam("file") MultipartFile multipartFile  ) {
		
		fileService.parseUploadedFile(multipartFile);
	}
	
	
	@RequestMapping("/getFile"  )
	public ResponseEntity<Resource> getFile(@RequestParam("id")  String id) {	
		Optional<PersonFile> personFile =  fileService.getFileDownload(id);
		if(personFile.isPresent() == true) {
			PersonFile person =  personFile.get(); 
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(person.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+person.getFileName())
					.body(new ByteArrayResource(person.getData()));
		}else {
			return null;
		}
	}
	
	@RequestMapping("/getFiles"  )
	public ResponseEntity<Resource> getFiles(){
		
		String fileType = "application/zip";
		try {
		
		byte[]  data =	fileService.getFilesDownload();
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(fileType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mendonca.zip")
				.body(new ByteArrayResource(data));
		
		}catch (Exception e) {
			return null;
		}
		
		
	}

	
	
	
}
