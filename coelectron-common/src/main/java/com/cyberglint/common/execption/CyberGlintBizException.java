package com.cyberglint.common.execption;




/**
 * 通用业务异常类,扩展于 RuntimeException 支持多元构造函数
 */
public class CyberGlintBizException extends RuntimeException {
    
    /**
     * 错误码属性，增强异常处理能力
     */
    private final String errorCode;
    
    /**
     * 创建业务异常,只含有错误消息
     *
     * @param errorMsg 错误消息
     */
    private CyberGlintBizException(String errorMsg) {
        super(errorMsg);
        this.errorCode = null;
    }
    
    /**
     * 创建业务异常,含有错误消息与错误码
     *
     * @param errorCode 错误码
     * @param errorMsg  错误消息
     */
    private CyberGlintBizException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
    
    /**
     * 根据错误消息直接抛出业务异常
     *
     * @param msg 错误消息
     */
    public static void throwErrorWithMessage(String msg) {
        throw new CyberGlintBizException(msg);
    }
    
    /**
     * 根据错误码和错误消息抛出业务异常
     *
     * @param errorCode 错误码
     * @param msg 错误消息
     */
    public static void throwErrorWithCodeAndMessage(String errorCode, String msg) {
        throw new CyberGlintBizException(errorCode, msg);
    }
}