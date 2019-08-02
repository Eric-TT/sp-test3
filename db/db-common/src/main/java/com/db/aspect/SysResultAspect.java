package com.db.aspect;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.db.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class SysResultAspect {

	@ExceptionHandler({ Throwable.class })
	public SysResult sysResultFail(Throwable e) {
		e.printStackTrace();
		log.error("服务器异常:" + e.getMessage());
		return SysResult.fail();
	}
}
