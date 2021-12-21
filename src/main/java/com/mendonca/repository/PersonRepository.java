package com.mendonca.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mendonca.modell.Person;

public  interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query("SELECT p FROM Person p where p.id >=?1  and p.id <= ?2")
	public List<Person> findByIdRange(Integer id,Integer id2 );
	
	

	
	
}
