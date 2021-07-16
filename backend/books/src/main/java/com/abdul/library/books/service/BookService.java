package com.abdul.library.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.abdul.library.books.entity.Book;
import com.abdul.library.books.feign.SubscriptionClient;
import com.abdul.library.books.pojo.SubcriberBookIdPk;
import com.abdul.library.books.pojo.Subscription;
import com.abdul.library.books.pojo.SubscriptionDto;
import com.abdul.library.books.repository.BookRepository;

import feign.FeignException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private SubscriptionClient subscriptionClient;
	
	public List<Book>getBooks(){
		return bookRepository.findAll();
	}
	
	public List<Book> postBooks(List<Book> books) {
		return bookRepository.saveAll(books);
	}
	
	public List<SubscriptionDto> getSubscriptions(String authHeader){
		
		List<SubscriptionDto> SubscriptionDtos = new ArrayList<SubscriptionDto>();
		
		List<Subscription> subscriptions = subscriptionClient.getSubscriptions(authHeader);
		
		for(Subscription subscription : subscriptions){
			SubscriptionDto subscriptionDto = new SubscriptionDto();
			BeanUtils.copyProperties(subscription, subscriptionDto);
			BeanUtils.copyProperties(subscription.getSubcriberBookIdPk(), subscriptionDto);
			Optional<Book> book = bookRepository.findById(subscription.getSubcriberBookIdPk().getBookId());
			if(book.isPresent())
				BeanUtils.copyProperties(book.get(), subscriptionDto);
			SubscriptionDtos.add(subscriptionDto);
		}
		
		return SubscriptionDtos;
	}
	
	@Transactional
	public SubscriptionDto postSubscription(SubcriberBookIdPk subcriberBookIdPk, String authHeader) {
		SubscriptionDto subscriptionDto = new SubscriptionDto();
		Optional<Book> bookObj = bookRepository.findById(subcriberBookIdPk.getBookId());
		if(!bookObj.isPresent())
			throw new ResourceNotFoundException("Invalid Book Id: " + subcriberBookIdPk.getBookId());
		Book book = bookObj.get();
		if(book.getAvailableCopies() <= 0)
			throw new ResourceNotFoundException("Book Id: " + subcriberBookIdPk.getBookId() + " not available !!!");
		try
		{
			Subscription subscription = subscriptionClient.postSubscription(authHeader, subcriberBookIdPk);
			Integer copies = book.getAvailableCopies();
			book.setAvailableCopies(copies-1);
			BeanUtils.copyProperties(subscription, subscriptionDto);
			BeanUtils.copyProperties(subscription.getSubcriberBookIdPk(), subscriptionDto);
			BeanUtils.copyProperties(book, subscriptionDto);
		}
		catch(FeignException e) {
			throw new ResourceNotFoundException(e.getLocalizedMessage());
		}
		return subscriptionDto;
	}
	
	@Transactional
	public SubscriptionDto postReturn(SubcriberBookIdPk subcriberBookIdPk, String authHeader) {
		SubscriptionDto subscriptionDto = new SubscriptionDto();
		Optional<Book> bookObj = bookRepository.findById(subcriberBookIdPk.getBookId());
		if(!bookObj.isPresent())
			throw new ResourceNotFoundException("Invalid Book Id: " + subcriberBookIdPk.getBookId());
		Book book = bookObj.get();
		try
		{
			Subscription subscription = subscriptionClient.postReturn(authHeader, subcriberBookIdPk);
			Integer copies = book.getAvailableCopies();
			book.setAvailableCopies(copies+1);
			BeanUtils.copyProperties(subscription, subscriptionDto);
			BeanUtils.copyProperties(subscription.getSubcriberBookIdPk(), subscriptionDto);
			BeanUtils.copyProperties(book, subscriptionDto);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException(e.getLocalizedMessage());
		}
		return subscriptionDto;
	}
}
