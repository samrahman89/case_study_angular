package com.abdul.library.books.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdul.library.books.pojo.SubcriberBookIdPk;
import com.abdul.library.books.pojo.Subscription;

@FeignClient(value = "subscriberclient", url = "http://localhost:8085/")
public interface SubscriptionClient {

	@RequestMapping(method = RequestMethod.GET, value = "/subscriptions")
	List<Subscription> getSubscriptions(@RequestHeader("Authorization") String authHeader);

	@RequestMapping(method = RequestMethod.POST, value = "/subscription")
	Subscription postSubscription(@RequestHeader("Authorization") String authHeader,
			@RequestBody SubcriberBookIdPk subcriberBookIdPk);

	@RequestMapping(method = RequestMethod.POST, value = "/return")
	Subscription postReturn(@RequestHeader("Authorization") String authHeader,
			@RequestBody SubcriberBookIdPk subcriberBookIdPk);
}
