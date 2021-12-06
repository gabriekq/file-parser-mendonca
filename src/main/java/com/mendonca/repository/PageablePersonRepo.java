package com.mendonca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mendonca.modell.Person;

public interface PageablePersonRepo extends PagingAndSortingRepository<Person, Integer> {


	
	
}
