package com.fitness.track.rest.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.track.rest.userservice.entities.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
	UsersEntity findByUserId(String userId);
}