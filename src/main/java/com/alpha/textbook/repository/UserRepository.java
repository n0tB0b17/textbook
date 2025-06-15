package com.alpha.textbook.repository;

import org.springframework.stereotype.Repository;

import com.alpha.textbook.domain.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {}
