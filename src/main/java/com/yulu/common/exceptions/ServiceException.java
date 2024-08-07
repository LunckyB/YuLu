package com.yulu.common.exceptions;

/**
 * 自定义业务异常
 */
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code; // 错误码
    private String message; // 错误提示

    // 空构造方法，避免反序列化问题
    public ServiceException() {
        super("系统异常");
        this.message = "系统异常";
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
