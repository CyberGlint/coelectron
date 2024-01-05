package com.cyberglint.common.response;
import lombok.Data;

import java.util.EnumSet;

/**
 * GlintRestResponse类 - 用于封装返回给客户端的统一 API 响应结构。
 *
 * @param <T> 响应数据的类型
 */
@Data
public class CyberGlintRestResponse<T> {
    
    // 返回的数据对象。
    private T data;
    
    // 返回的结果消息，通常用于错误信息的传递。
    private String resultMessage;
    
    // 返回的结果码，表示请求处理的状态。
    private ResultCode resultCode;
    
    // 响应生成的时间戳。
    private long timestamp = System.currentTimeMillis();
    
    /**
     * 创建表示操作成功的响应，不包含任何返回数据。
     *
     * @param <T> 响应数据类型
     * @return 表示成功的响应
     */
    public static <T> CyberGlintRestResponse<T> ok() {
        CyberGlintRestResponse<T> response = new CyberGlintRestResponse<>();
        response.setResultCode(ResultCode.SUCCESS);
        return response;
    }
    
    /**
     * 创建表示操作成功的响应，包含返回的数据。
     *
     * @param data 返回的数据
     * @param <T>  响应数据类型
     * @return 表示成功的响应，包含数据
     */
    public static <T> CyberGlintRestResponse<T> ok(T data) {
        CyberGlintRestResponse<T> response = new CyberGlintRestResponse<>();
        response.setData(data);
        response.setResultCode(ResultCode.SUCCESS);
        return response;
    }
    
    /**
     * 创建表示操作失败的响应，只包含错误消息。
     *
     * @param message 错误消息
     * @param <T>     响应数据类型
     * @return 表示错误的响应
     */
    public static <T> CyberGlintRestResponse<T> error(String message) {
        CyberGlintRestResponse<T> response = new CyberGlintRestResponse<>();
        response.setResultMessage(message);
        response.setResultCode(ResultCode.ERROR);
        return response;
    }
    
    /**
     * 创建表示操作失败的响应，只包含默认的错误消息。
     *
     * @param <T> 响应数据类型
     * @return 表示错误的响应
     */
    public static <T> CyberGlintRestResponse<T> error() {
        CyberGlintRestResponse<T> response = new CyberGlintRestResponse<>();
        response.setResultMessage("服务异常");
        response.setResultCode(ResultCode.ERROR);
        return response;
    }
    
    /**
     * ResultCode枚举 - 定义 API 响应的结果码。
     */
    public enum ResultCode {
        // 表示操作成功的结果码。
        SUCCESS(0),
        // 表示操作失败的结果码。
        ERROR(1);
        
        private final int code;
        
        ResultCode(int code) {
            this.code = code;
        }
        
        /**
         * 通过整数码获取对应的结果码枚举。
         *
         * @param code 整数码
         * @return 对应的结果码枚举
         * @throws IllegalArgumentException 如果没有找到对应的枚举
         */
        public static ResultCode valueOf(int code) {
            return EnumSet.allOf(ResultCode.class).stream()
                    .filter(rc -> rc.code == code)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}