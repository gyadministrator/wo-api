package com.gy.wo.api.exception;

public class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super("服务器内部错误:" + message);
    }
}
