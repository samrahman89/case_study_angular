package com.abdul.library.books.pojo;

import java.time.LocalDate;

public class Subscription {
	
	private SubcriberBookIdPk subcriberBookIdPk = new SubcriberBookIdPk();
	
	private LocalDate dateSubscribed = LocalDate.now();
	
	private LocalDate dateReturned;

	public LocalDate getDateSubscribed() {
		return dateSubscribed;
	}

	public void setDateSubscribed(LocalDate dateSubscribed) {
		this.dateSubscribed = dateSubscribed;
	}

	public LocalDate getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDate dateReturned) {
		this.dateReturned = dateReturned;
	}

	public SubcriberBookIdPk getSubcriberBookIdPk() {
		return subcriberBookIdPk;
	}

	public void setSubcriberBookIdPk(SubcriberBookIdPk subcriberBookIdPk) {
		this.subcriberBookIdPk = subcriberBookIdPk;
	}

}
