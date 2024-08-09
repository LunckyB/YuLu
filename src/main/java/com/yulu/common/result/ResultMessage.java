package com.yulu.common.result;

import com.yulu.common.enums.Constants;
import com.yulu.common.tools.VerifyTool;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应信息主体
 */
@Getter
@Setter
// @NoArgsConstructor // 生成无参的构造方法
public class ResultMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code; // 状态码
    private boolean success; // 响应结果
    private T data; // 承载数据
    private String msg; // 消息体
    private String interiorMsg; // 内部消息体

    // 空构造方法
    public ResultMessage() {
        this.code = Constants.REQUEST_SUCCESS;
        this.success = true;
        this.msg = "操作成功";
    }
    // 构造成功数据返回
    public ResultMessage(T data) {
        this.code = Constants.REQUEST_SUCCESS;
        this.success = true;
        this.msg = "操作成功";

        if (VerifyTool.isNotNull(data)) {
            this.data = data;
        }
    }
    // 构造标识和消息体
    public ResultMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = Constants.REQUEST_SUCCESS == code;
    }
    // 构造标识和消息体和数据
    public ResultMessage(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.success = Constants.REQUEST_SUCCESS == code;
        if (VerifyTool.isNotNull(data)) {
            this.data = data;
        }
    }
    // 构造消息体和内部消息体
    public ResultMessage(String msg, String interiorMsg) {
        this.code = Constants.REQUEST_ERROR;
        this.success = false;
        this.msg = msg;
        this.interiorMsg = interiorMsg;
    }

    // 返回成功消息
    public static <T> ResultMessage<T> success() {
        return new ResultMessage<>();
    }

    // 返回成功数据
    public static <T> ResultMessage<T> success(T data) {
        return new ResultMessage<>(data);
    }

    // 返回成功消息
    public static <T> ResultMessage<T> success(String msg) {
        return new ResultMessage<>(Constants.REQUEST_SUCCESS, msg);
    }

    public static <T> ResultMessage<T> token(T data) {
        return new ResultMessage<>(data);
    }

    // 返回错误消息
    public static <T> ResultMessage<T> error() {
        return new ResultMessage<>(Constants.REQUEST_ERROR, "操作失败");
    }

    // 返回错误消息
    public static <T> ResultMessage<T> error(String msg) {
        return new ResultMessage<>(Constants.REQUEST_ERROR, msg);
    }

    // 返回错误消息
    public static <T> ResultMessage<T> error(String msg, String interiorMsg) {
        return new ResultMessage<>(msg, interiorMsg);
    }
}

