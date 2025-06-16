package com.alpha.textbook.repository;

import org.springframework.stereotype.Repository;

import com.alpha.textbook.domain.User;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
        @Query("MATCH (u: User) WHERE u.username = $username RETURN count(u) > 0")
        boolean existsByUsername(String username);

        String MATCH_USER_QUERY = "MATCH (u: User) WHERE toLower(u.username) CONTAINS toLower($username) ";
        String MATCH_USER_JOINED_AT_QUERY = "MATCH (u: User) WHERE u.joinedAt >= $startDate AND u.joinedAt <= $endDate ";
        String MATCH_USER_LAST_LOGIN_AT_AFTER_QUERY = "MATCH (u: User) WHERE u.lastLoginAt >= $cutOffTime ";

        String RETURN_COUNT_OF_USER = "RETURN count(u)";

        @Query(value = MATCH_USER_QUERY +
                        "RETURN u ORDER BY u.username " +
                        "SKIP $skip LIMIT $limit", countQuery = MATCH_USER_QUERY + RETURN_COUNT_OF_USER)
        Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

        @Query(value = MATCH_USER_JOINED_AT_QUERY +
                        "RETURN u ORDER BY u.joinedAt DESC " +
                        "SKIP $skip LIMIT $limit", countQuery = MATCH_USER_JOINED_AT_QUERY + RETURN_COUNT_OF_USER)
        Page<User> findByJoinedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

        @Query(value = MATCH_USER_LAST_LOGIN_AT_AFTER_QUERY +
                        "RETURN u ORDER BY u.lastLoginAt DESC " +
                        "SKIP $skip LIMIT $limit", 
                        countQuery = MATCH_USER_LAST_LOGIN_AT_AFTER_QUERY + RETURN_COUNT_OF_USER)
        Page<User> findByLastLoginAtAfter(LocalDateTime cutOffTime, Pageable pageable);
}
