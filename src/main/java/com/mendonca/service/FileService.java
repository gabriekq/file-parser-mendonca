package com.mendonca.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipOutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.mendonca.modell.Person;
import com.mendonca.modell.PersonFile;

public interface FileService {

	public void parseUploadedFile(MultipartFile multipartFile);
	
    public Long getCountPerson(); // vai virar count
    
    public Optional<PersonFile>  getFileDownload(String id);
    
    public List<Person> getPersonsbyPage(int pageNumber) ;
    
    public List<Person> searchPersonByRangeId(String id);
	
    public byte[] getFilesDownload()throws IOException;
    
}
