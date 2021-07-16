package com.abdul.library.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.library.books.entity.Book;
import com.abdul.library.books.pojo.SubcriberBookIdPk;
import com.abdul.library.books.pojo.SubscriptionDto;
import com.abdul.library.books.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(path = "books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	@PostMapping(path = "books")
	public List<Book> postBooks(@RequestBody List<Book> books) {
		return bookService.postBooks(books);
	}

	@GetMapping(path = "subscriptions")
	public List<SubscriptionDto> getSubscribers(
			@RequestHeader(value = "Authorization", required = true) String authHeader) {
		return bookService.getSubscriptions(authHeader);
	}

	@PostMapping(path = "subscription")
	public SubscriptionDto postSubscription(@RequestHeader(value = "Authorization", required = true) String authHeader,
			@RequestBody SubcriberBookIdPk subcriberBookIdPk) {
		return bookService.postSubscription(subcriberBookIdPk, authHeader);
	}

	@PostMapping(path = "return")
	public SubscriptionDto postReturn(@RequestBody SubcriberBookIdPk subcriberBookIdPk,
			@RequestHeader(value = "Authorization", required = true) String authHeader) {
		return bookService.postReturn(subcriberBookIdPk, authHeader);
	}

}
