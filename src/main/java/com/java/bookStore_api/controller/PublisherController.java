package com.java.bookStore_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.bookStore_api.entity.Publisher;
import com.java.bookStore_api.serviceImp.PublisherServiceImp;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
	
	@Autowired
	PublisherServiceImp publisherServiceImp;
	
	@GetMapping("/allPublisher")
	public ResponseEntity<?> getPublisher() {
		List<Publisher> listPublishers = publisherServiceImp.getAllPublishers();
		
		return new ResponseEntity<List<Publisher>>(listPublishers, HttpStatus.OK);
	}
	
}
