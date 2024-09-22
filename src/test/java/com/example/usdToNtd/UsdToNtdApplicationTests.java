package com.example.usdToNtd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.usdToNtd.controller.DemoController;
import com.example.usdToNtd.servicve.impl.ExchangeServiceImpl;
import com.example.usdToNtd.vo.QueryResult;
import com.example.usdToNtd.vo.UserReq;

@SpringBootTest
class UsdToNtdApplicationTests {


	@Autowired
	ExchangeServiceImpl exchangeServiceImpl;

	@Autowired
	DemoController demoController;


	@Test
	void contextLoads() {

	}

	@Test
	void question1Test() {
		try {
			exchangeServiceImpl.syncExchange();
		} catch (Exception e) {
		}
	}

	@Test
	void question2TestSuccess() throws Exception {
		UserReq userReq=new UserReq();
		userReq.setStartDate("2024/01/01");
		userReq.setEndDate("2024/01/01");
		userReq.setCurrency("usd");

		ResponseEntity<QueryResult> res=demoController.queryExchangeRate(userReq);
		QueryResult q=res.getBody();
				
		if (q!=null) {
			String failCode=q.getError().getCode()==null?"fail":q.getError().getCode();
			Assertions.assertEquals("0000", failCode);
		}else{
			String failCode=null;
			Assertions.assertEquals("0000", failCode);
		}
	}


	@Test
	void question2TestFail() throws Exception {

		UserReq userReq=new UserReq();
		userReq.setStartDate("2022/01/01");
		userReq.setEndDate("2024/01/01");
		userReq.setCurrency("usd");

		ResponseEntity<QueryResult> res=demoController.queryExchangeRate(userReq);
		QueryResult q=res.getBody();
		if (q!=null) {
			String failCode=q.getError().getCode()==null?"fail":q.getError().getCode();
			Assertions.assertEquals("E001", failCode);
		}else{
			String failCode=null;
			Assertions.assertEquals("E001", failCode);
		}
			
		
	}

}
