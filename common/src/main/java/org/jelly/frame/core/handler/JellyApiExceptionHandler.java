package org.jelly.frame.core.handler;

import org.jelly.frame.core.api.Result;
import org.jelly.frame.core.api.ResultsHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义全局异常拦截
 *
 * @author zhangxb
 */
@RestControllerAdvice
public class JellyApiExceptionHandler {
    /**
     * 手动抛出异常
     *
     * @param e {@link JellyCheckedException}
     * @return {@link ResultsHelper}
     */
    @ExceptionHandler(JellyCheckedException.class)
    public ResultsHelper<Object> manual(JellyCheckedException e) {
        return ResultsHelper.builder().customize(e.getCode(), e.getMessage(), null).build();
    }

    /**
     * 手动抛出异常
     *
     * @param e {@link JellyRuntimeException}
     * @return {@link ResultsHelper}
     */
    @ExceptionHandler(JellyRuntimeException.class)
    public ResultsHelper<Object> manual1(JellyRuntimeException e) {
        return ResultsHelper.builder().customize(Result.SYSTEM_ERROR.getCode(), e.getMessage(), null).build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultsHelper<Object> notFind(HttpServletRequest request) {
        return ResultsHelper.builder().customize(Result.REQUEST_ERROR, request.getServletPath()).build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultsHelper<Object> requestMethod(HttpServletRequest request) {
        return ResultsHelper.builder().customize(Result.REQUEST_ERROR, request.getServletPath()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultsHelper<Object> server(Exception e) {
        return ResultsHelper.builder().customize(Result.SYSTEM_ERROR, e.getMessage()).build();
    }
}
