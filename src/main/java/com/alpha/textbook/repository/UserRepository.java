package com.alpha.textbook.repository;

import org.springframework.stereotype.Repository;

import com.alpha.textbook.domain.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    @Query("MATCH (u: User) WHERE u.username = $username RETURN count(u) > 0")
    boolean existsByUsername(String username);
}
