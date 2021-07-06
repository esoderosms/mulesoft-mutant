package com.soderling.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soderling.challenge.model.Dna;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Integer> {

}
