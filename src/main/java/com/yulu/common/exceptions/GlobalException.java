package com.yulu.common.exceptions;

import com.yulu.common.result.ResultMessage;
import com.yulu.common.tools.VerifyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResultMessage handlerException(Exception e) {
        System.out.println("*******全局捕获所有异常: " + e.getMessage());
        String errMessage = e.getMessage();
        if (VerifyUtils.isNotEmpty(errMessage)) {
            return ResultMessage.error(errMessage);
        }
        return ResultMessage.success();
    }
}
