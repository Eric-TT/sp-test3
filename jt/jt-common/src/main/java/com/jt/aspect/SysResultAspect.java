package com.jt.aspect;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

//@ControllerAdvice //针对controller层生效
@RestControllerAdvice
public class SysResultAspect {
	@ExceptionHandler(RuntimeException.class)
	public SysResult sysResultFail(Exception e) {
		System.out.println("服务器异常"+e.getMessage());
		return SysResult.fail();
	}
}
