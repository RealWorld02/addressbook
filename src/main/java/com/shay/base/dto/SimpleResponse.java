package com.shay.base.dto;

public class SimpleResponse<T> extends BaseResponse {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
