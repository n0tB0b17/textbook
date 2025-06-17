package com.alpha.textbook.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.alpha.textbook.domain.Location;

@Repository
public interface LocationRepository extends Neo4jRepository<Location, Long> {

}
