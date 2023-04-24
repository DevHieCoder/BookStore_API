package com.java.bookStore_api.serviceImp;


import java.util.List;

import com.java.bookStore_api.entity.Publisher;

public interface PublisherServiceImp {
	public Publisher createPublisher(Publisher publisher);
	public boolean existsByName(String publisherName);
	public Publisher findByPublisherName(String publisherName);
	public List<Publisher> getAllPublishers();
}
