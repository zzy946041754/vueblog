package com.zzy.vueblog.common.exception;

import com.zzy.vueblog.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @blog: blog.csdn.net/qq_43365046
 * @email zzz946041754@163.com
 * @description: 全局异常处理
 * @author: zzy
 * @data: 2021/1/31-14:20
 **/
@Slf4j
@RestControllerAdvice
public class ClobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行异常-------{}",e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e){
        log.error("访问异常-------{}",e);
        return Result.fail(401,e.getMessage(),null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体校验异常-------{}",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(error.getDefaultMessage());
    }
}
