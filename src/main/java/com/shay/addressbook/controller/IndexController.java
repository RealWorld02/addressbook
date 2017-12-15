package com.shay.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shay.addressbook.entity.Sample;
import com.shay.addressbook.service.SampleService;

@RestController
public class IndexController {
	
	@Autowired
	private SampleService sampleservice;
	
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public List<Sample> one(String username){
		List<Sample> sam = sampleservice.get();
		return sam;
	}
}
