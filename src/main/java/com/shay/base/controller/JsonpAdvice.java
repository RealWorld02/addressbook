package com.shay.base.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * spring4.1以后支持的特性，支持jsonp方式响应
 * 
 * @author ex_huangsl1
 *
 */
@RestControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	public JsonpAdvice() {
		super("callback");
	}
}
