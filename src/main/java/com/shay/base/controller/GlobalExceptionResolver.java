package com.shay.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 全局处理系统异常，在springMVC-servlet.xml进行了设置
 * 
 * @author huangsl
 * 
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Result result;
		if (ex instanceof IllegalArgumentException) { // 输入的参数错误
			result = parseException((IllegalArgumentException) ex);
		} else if (ex instanceof IllegalStateException) { // 状态错误
			result = parseException((IllegalStateException) ex);
		} else {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("ex", ex);
			result = new Result(5, "系统繁忙，请稍后再试", ex.getMessage()); // 系统繁忙，请稍后再试
			logger.error("ERR:{}", ex);
		}
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.addStaticAttribute("code", result.getCode());
		view.addStaticAttribute("msg", result.getMsg());
		view.addStaticAttribute("reason", result.getReason());
		return new ModelAndView(view);
	}

	public Result parseException(IllegalStateException ex) {
		int code;
		String msg;
		String reason = "";
		code = 2;
		msg = ex.getMessage();
		return new Result(code, msg, reason);
	}

	public Result parseException(IllegalArgumentException ex) {
		return new Result(2, ex.getMessage(), ex.getMessage());
	}

	public final static class Result {
		private final int code;
		private final String msg;
		private final String reason;

		public Result(int code, String msg, String reason) {
			super();
			this.code = code;
			this.msg = msg;
			this.reason = reason;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public String getReason() {
			return reason;
		}

	}
}
