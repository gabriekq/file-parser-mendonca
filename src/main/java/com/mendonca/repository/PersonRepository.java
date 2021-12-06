package com.mendonca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendonca.modell.Person;

public  interface PersonRepository extends JpaRepository<Person, Integer> {

}
