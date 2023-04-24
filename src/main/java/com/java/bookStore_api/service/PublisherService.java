package com.java.bookStore_api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Publisher;
import com.java.bookStore_api.repository.PublisherRepository;
import com.java.bookStore_api.serviceImp.PublisherServiceImp;

@Service
public class PublisherService implements PublisherServiceImp {
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Override
	public Publisher createPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		return publisherRepository.save(publisher);
	}

	@Override
	public boolean existsByName(String publisherName) {
		// TODO Auto-generated method stub
		return publisherRepository.existsByPublisherName(publisherName);
	}

	@Override
	public Publisher findByPublisherName(String publisherName) {
		// TODO Auto-generated method stub
		return publisherRepository.findByPublisherName(publisherName).orElse(null);
	}

	@Override
	public List<Publisher> getAllPublishers() {
		// TODO Auto-generated method stub
		return publisherRepository.findAll();
	}

}
