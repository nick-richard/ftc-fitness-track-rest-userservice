package com.fitness.track.rest.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.track.rest.userservice.entities.UsersEntity;
import com.fitness.track.rest.userservice.repositories.UsersRepository;

@Service
public class UsersService {
	
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public UsersEntity findByUserId(String userId) {
		UsersEntity usersEntity = usersRepository.findByUserId(userId);
		return usersEntity;
	}
	
	public void createUserId(UsersEntity usersEntity) {
		usersRepository.save(usersEntity);
	}
	
	public void deleteByUserId(String userId) {
		usersRepository.deleteById(userId);
	}
}