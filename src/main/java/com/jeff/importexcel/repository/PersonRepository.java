package com.jeff.importexcel.repository;

import com.jeff.importexcel.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
