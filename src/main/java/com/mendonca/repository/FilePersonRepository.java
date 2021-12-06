package com.mendonca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendonca.modell.PersonFile;

public interface FilePersonRepository extends JpaRepository<PersonFile, Integer> {

}
