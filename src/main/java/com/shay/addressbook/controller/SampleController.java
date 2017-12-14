package com.shay.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shay.addressbook.entity.Sample;
import com.shay.addressbook.service.SampleService;
import com.shay.base.dto.SimpleResponse;

@Controller
public class SampleController {
	@Autowired
	private SampleService sampleService;

	@RequestMapping(value = "hello", method=RequestMethod.GET)
	public @ResponseBody String hello(String username) {
		System.out.println("*************"+username);
		return "hello";
	}

	@RequestMapping(value = "sample")
	public @ResponseBody List<Sample> one(String username) {
		List<Sample> sample = sampleService.get();
		System.out.println("*************"+username);
		return sample;
	}

}
