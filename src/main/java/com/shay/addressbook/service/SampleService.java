package com.shay.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shay.addressbook.DAO.SampleDao;
import com.shay.addressbook.entity.Sample;

@Service
public class SampleService {
	@Autowired
	private SampleDao dao;

	public List<Sample> get() {
		return dao.selectOne();
	}

}
