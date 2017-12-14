package com.shay.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * REST返回数据的基本结构
 * 
 * @author lianhuang
 *
 */
public class BaseResponse {

	/**
	 * 响应编码
	 */
	protected int code;

	/**
	 * 响应消息
	 */
	protected String msg = "";

	/**
	 * 如果有错误，表示错误的原因
	 */
	@JsonInclude(value = Include.NON_NULL)
	protected String reason = "";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 成功的响应实例
	 */
	public final static BaseResponse SUCCESS_INSTANCE = new BaseResponse() {

		@Override
		public void setCode(int code) {
			return;
		}

		@Override
		public void setMsg(String msg) {
			return;
		}

		@Override
		public void setReason(String reason) {
			return;
		}

	};

}
