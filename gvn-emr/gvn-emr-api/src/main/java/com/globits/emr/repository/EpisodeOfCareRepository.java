package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.EpisodeOfCare;
import com.globits.emr.domain.concept.Concept;
@Repository
public interface EpisodeOfCareRepository extends JpaRepository<EpisodeOfCare, UUID>{
	@Query("select entity from EpisodeOfCare entity")
    List<EpisodeOfCare> getAll();
}
