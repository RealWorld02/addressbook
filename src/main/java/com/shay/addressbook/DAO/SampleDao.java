package com.shay.addressbook.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shay.addressbook.entity.Sample;

@Repository
public interface SampleDao {

	List<Sample> selectOne();

}
