package com.java.bookStore_api.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.java.bookStore_api.entity.Author;
import com.java.bookStore_api.entity.Book;
import com.java.bookStore_api.entity.BookAuthor;
import com.java.bookStore_api.entity.BookCategory;
import com.java.bookStore_api.entity.BookStock;
import com.java.bookStore_api.entity.Category;
import com.java.bookStore_api.entity.Publisher;
import com.java.bookStore_api.entity.Stock;
import com.java.bookStore_api.serviceImp.AuthorServiceImp;
import com.java.bookStore_api.serviceImp.BookAuthorServiceImp;
import com.java.bookStore_api.serviceImp.BookCategoryServiceImp;
import com.java.bookStore_api.serviceImp.BookServiceImp;
import com.java.bookStore_api.serviceImp.BookStockServiceImp;
import com.java.bookStore_api.serviceImp.CategoryServiceImp;
import com.java.bookStore_api.serviceImp.FileStorageServiceImp;
import com.java.bookStore_api.serviceImp.PublisherServiceImp;
import com.java.bookStore_api.serviceImp.StockServiceImp;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookServiceImp bookServiceImp;
	
	@Autowired
	StockServiceImp stockServiceImp;
	
	@Autowired
	AuthorServiceImp authorServiceImp;
	
	@Autowired
	CategoryServiceImp categoryServiceImp;
	
	@Autowired
	PublisherServiceImp publisherServiceImp;
	
	@Autowired
	BookStockServiceImp bookStockServiceImp;
	
	@Autowired
	BookCategoryServiceImp bookCategoryServiceImp;
	
	@Autowired
	BookAuthorServiceImp bookAuthorServiceImp;
	
	@Autowired
	FileStorageServiceImp fileStorageServiceImp;
	
	
//	ADD Book
	private void addBook(Book book, String bookName, Optional<Float> bookPrice, String description) {
		book.setName(bookName);
		book.setPrice(bookPrice.orElse(0.0f));
		book.setDescription(description);
	}
	
//	ADD Author
	private void addAuthor(Book book, List<String> authorNames) {
		Set<Author> authors = new HashSet<>();
		for (String authorName : authorNames) {
			Author author;
			if (!authorServiceImp.existsByAuthorName(authorName)) {
				author = new Author();
				author.setAuthorName(authorName);
				author = authorServiceImp.addAuthor(author);
			} else {
				author = authorServiceImp.findByAuthorName(authorName);
			}
			authors.add(author);
		}
		book.setAuthors(authors);
	}
	
//	ADD Category
	private void addCategory(Book book, List<String> categoryNames) {
		Set<Category> categories = new HashSet<>();
		for (String categoryName : categoryNames) {
			Category category;
			if (!categoryServiceImp.existsByCategoryName(categoryName)) {
				category = new Category();
				category.setCategoryName(categoryName);
				category = categoryServiceImp.addCategory(category);
			} else {
				category = categoryServiceImp.findByCategoryName(categoryName);
			}
			categories.add(category);
		}
		book.setCategories(categories);
	}
	
//	ADD Publisher
	private void addPublisher(Book book, String publisherExistName, String publisherName, String publisherEmail,
			Integer publisherPhone, String publisherAddress, String publisherCountry, boolean addNewPublisher) {
		Publisher publisher;
		if (addNewPublisher) {
			publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setEmail(publisherEmail);
			publisher.setCountry(publisherCountry);
			publisher.setPhoneNumber(publisherPhone);
			publisher.setAddress(publisherAddress);
			publisherServiceImp.createPublisher(publisher);
		} else {
			publisher = publisherServiceImp.findByPublisherName(publisherExistName);
		}
		book.setPublisher(publisher);
	}
	
//	ADD Stock
	private void updateStock(Book book, String stockName, int quantity) {
		Stock stock = stockServiceImp.findByName(stockName);
	    
	    if (stock == null) {
	        return;
	    }
	    
	    BookStock bookStock = bookStockServiceImp.findByBook(book);
	    
	    if (bookStock != null) {
	        bookStock.setStock(stock);
	    } else {
			bookStock = new BookStock();
			bookStock.setBook(book);
			bookStock.setStock(stock);
		}
	    
	    bookStock.setQuantity(quantity);
	    bookStockServiceImp.createBookStock(bookStock);
	}

	@GetMapping("/allBook")
	public ResponseEntity<?> getinfoBooks() {
		List<Map<String, ?>> listBook = bookServiceImp.getInfoBooks();
		
		return new ResponseEntity<List<Map<String, ?>>>(listBook, HttpStatus.OK);
	}
	
	@GetMapping("/getBookById")	
	public ResponseEntity<?> getBookById(@RequestParam("id") int id) {
	    List<Map<String, ?>> listBookMaps = bookServiceImp.getInfoBooks();

	    Optional<Map<String, ?>> matchedBook = listBookMaps.stream()
	        .filter(bookMap -> id == (Integer) bookMap.get("id"))
	        .findFirst();

	    return matchedBook.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
	        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/add")
	public RedirectView addBook(@RequestParam("name") String bookName,@RequestParam("price") Optional<Float> bookPrice, 
			@RequestParam("authorName") List<String> authorNames, @RequestParam("categoryName") List<String> categoryNames, 
			@RequestParam("stockName") String stockName, @RequestParam("quanity") int quanity,
			@RequestParam("description") String description, @RequestParam("image") MultipartFile image,
			@RequestParam(name = "publisherExist", required = false) String publisherExistName,@RequestParam(name = "publisherName", required = false) String publisherName, 
			@RequestParam(name = "publisherEmail", required = false) String publisherEmail,@RequestParam(name = "publisherPhone", required = false) Integer publisherPhone, 
			@RequestParam(name = "publisherAddress", required = false) String publisherAddress,@RequestParam(name = "publisherCountry", required = false) String publisherCountry,
			@RequestParam("addNewPublisher") boolean addNewPublisher) {
		
		
//		Kiểm tra sách đã có trong csdl hay chưa
		boolean isSuccess = true;
		
		for (Book book : bookServiceImp.getAllBook()) {
			if (book.getName().equalsIgnoreCase(bookName)) {
				isSuccess = false;
				break;
			} 
		}
		
//		Thêm thông tin sách nếu chưa có trong csdl
		if (isSuccess) {
			Book newBook = new Book();
			addBook(newBook, bookName, bookPrice, description);
			newBook.setImage(image.getOriginalFilename());
			fileStorageServiceImp.saveFile(image);
			addAuthor(newBook, authorNames);
			addCategory(newBook, categoryNames);
			addPublisher(newBook, publisherExistName, publisherName, publisherEmail, publisherPhone, publisherAddress, publisherCountry, addNewPublisher);
			bookServiceImp.addBook(newBook);
			updateStock(newBook, stockName, quanity);
		}
		
//		Trả kết quả thông báo cho người dùng
		String redirectUrl = "http://localhost:8081/adminHome/addBook";
		if (!isSuccess) {
			redirectUrl += "?fail=true";
		} else {
			redirectUrl += "?success=true";
		}
		
		return new RedirectView(redirectUrl);
	}
	
	@PostMapping("/updateBook/{id}")
	public RedirectView updateBook(@PathVariable Integer id,@RequestParam("bookName") String bookName,@RequestParam("bookPrice") Optional<Float> bookPrice, 
			@RequestParam("authorName") List<String> authorNames, @RequestParam("categoryName") List<String> categoryNames, 
			@RequestParam("stockName") String stockName, @RequestParam("quantity") int quantity,
			@RequestParam("bookDescription") String bookDescription, @RequestParam("image") MultipartFile image,
			@RequestParam(name = "publisherExist") String publisherExistName,@RequestParam(name = "publisherName", required = false) String publisherName, 
			@RequestParam(name = "publisherEmail", required = false) String publisherEmail,@RequestParam(name = "publisherPhone", required = false) Integer publisherPhone, 
			@RequestParam(name = "publisherAddress", required = false) String publisherAddress,@RequestParam(name = "publisherCountry", required = false) String publisherCountry,
			@RequestParam("addNewPublisher") boolean addNewPublisher) {
		Optional<Book> optionalBook = bookServiceImp.findById(id);
		Book book = optionalBook.get();
//		Kiểm tra tên book trùng
		if (!book.getName().equalsIgnoreCase(bookName) && bookServiceImp.existsByName(bookName)) {
			String redirectUrl = "http://localhost:8081/adminHome/editBook?id=" + id + "&fail=true";
			return new RedirectView(redirectUrl);
		}
		addBook(book, bookName, bookPrice, bookDescription);
		if (image != null && !image.isEmpty() && !image.getOriginalFilename().isEmpty()) {
	        book.setImage(image.getOriginalFilename());
	        fileStorageServiceImp.saveFile(image);
	    }
		addAuthor(book, authorNames);
		addCategory(book, categoryNames);
		addPublisher(book, publisherExistName, publisherName, publisherEmail, publisherPhone, publisherAddress, publisherCountry, addNewPublisher);
		bookServiceImp.addBook(book);
		updateStock(book, stockName, quantity);
		
		String redirectUrl = "http://localhost:8081/adminHome/allBook?updateSuccess=true";
		
		return new RedirectView(redirectUrl);

	}
	
	@GetMapping("/delBook/{id}")
	public RedirectView deleteBook(@PathVariable Integer id) {
		List<BookStock> listbBookStocks = bookStockServiceImp.findAllByBookId(id);
		List<BookCategory> listBookCategories = bookCategoryServiceImp.findAllByBookId(id);
		List<BookAuthor> listBookAuthors = bookAuthorServiceImp.findAllByBookId(id);
		for (BookStock bookStock : listbBookStocks) {
			bookStockServiceImp.deleteAll(bookStock);
		}
		for (BookAuthor bookAuthor : listBookAuthors) {
			bookAuthorServiceImp.deleteAll(bookAuthor);
		}
		for (BookCategory bookCategory : listBookCategories) {
			bookCategoryServiceImp.deleteAll(bookCategory);
		}
		bookServiceImp.deleteById(id);
		
		
		String redirectUrl = "http://localhost:8081/adminHome/allBook?delSuccess=true";
		
		return new RedirectView(redirectUrl);

	}
	
}
