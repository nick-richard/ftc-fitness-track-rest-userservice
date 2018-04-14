package com.fitness.track.rest.userservice.controllers;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.track.rest.userservice.entities.UsersEntity;
import com.fitness.track.rest.userservice.services.UsersService;

@RestController
public class UsersController {

    private UsersService usersService;
    
    @Autowired
    public UsersController(UsersService usersService) {
    	this.usersService = usersService;
    }
    
    @GetMapping("/Users/UserId/{userId}")
    public ResponseEntity<UsersEntity> getUsersEntity(@PathVariable("userId") UUID userId) {
    	UsersEntity usersEntity = usersService.findByUserId(userId);
    	if (usersEntity == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UsersEntity>(usersEntity, HttpStatus.OK);
    }
    
	@PostMapping("/Users/UserId/create")
    public ResponseEntity<UsersEntity> createUserEntity(@RequestBody final UsersEntity usersEntity) {
		System.out.println("In Controller.......");
		if (usersEntity == null) {
			System.out.println("Bad Request.......");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	try {
    		System.out.println("Creating User Id from Controller.......");
    		UUID uuid = UUID.randomUUID();
    		usersEntity.setUserId(uuid);
    		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    		usersEntity.setCreatedTimestamp(timestamp);
    		usersService.createUser(usersEntity);
    		return new ResponseEntity<UsersEntity>(usersEntity, HttpStatus.OK);
    	} catch (TransientDataAccessException e) {
    		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @SuppressWarnings({ "rawtypes", "unused" })
	@DeleteMapping("/Users/UserId/{userId}")
    public ResponseEntity deleteUserEntity(@PathVariable("userId") UUID userId) {
    	try {
    		UsersEntity usersEntity = usersService.findByUserId(userId);
    		return new ResponseEntity(HttpStatus.OK);
    	} catch (TransientDataAccessException e) {
    		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    	} catch (Exception e) {
    		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

}