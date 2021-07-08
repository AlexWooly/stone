package com.stone.demo.conf;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.stone.demo.util.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = com.stone.demo.redis.RateLimiterException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonData runTimeExceptionHandler(com.stone.demo.redis.RateLimiterException e){
        logger.error("系统错误",e);
        return JsonData.buildError(StringUtils.isNoneBlank(e.getMessage())?e.getMessage():"处理失败");

    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonData runtimeExceptionHandler(RuntimeException e) {
        Throwable cause = e.getCause();
        logger.error("系统错误:", e);
        logger.error(e.getMessage());
        if (cause instanceof JsonMappingException) {
            return JsonData.buildError("参数错误");
        }
        return JsonData.buildError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "处理失败");
    }


}
