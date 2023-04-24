package com.java.bookStore_api.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "price")
	private float price;
	
	@ManyToOne
	@JoinColumn(name = "id_publisher")
	private Publisher publisher;
	
	@OneToMany(mappedBy = "book")
	private Set<BookAuthor> bookAuthors;
	
	@OneToMany(mappedBy = "book")
	private Set<BookCategory> bookCategories;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
	private Set<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "book")
	private Set<BookStock> bookStocks;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_author", referencedColumnName = "id"))
	private Set<Author> authors;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id"))
	private Set<Category> categories;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
}
