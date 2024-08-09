package com.yulu.common.exceptions;

import com.yulu.common.result.ResultMessage;
import com.yulu.common.tools.VerifyTool;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalException {

    // Validated 校验工具类报错 关键字符串
    private String validationFailedStr = "Validation failed for argument";
    private String validationFailedMessage = "default message [";

    @ExceptionHandler(Exception.class)
    public ResultMessage handlerException(Exception e) {
        System.out.println("*******全局捕获所有异常: " + e.getMessage());
        String errMessage = e.getMessage();
        if (VerifyTool.isNotEmpty(errMessage)) {
            // Validated 校验工具类报错拦截处理
            if (errMessage.indexOf(validationFailedStr) == 0) {
                int lastIndexOf = errMessage.lastIndexOf(validationFailedMessage);
                String message = errMessage.substring(lastIndexOf + validationFailedMessage.length(), errMessage.length());
                message = message.substring(0, message.indexOf("]"));
                return ResultMessage.error(message);
            }
            return ResultMessage.error(errMessage);
        }
        return ResultMessage.success();
    }
}
