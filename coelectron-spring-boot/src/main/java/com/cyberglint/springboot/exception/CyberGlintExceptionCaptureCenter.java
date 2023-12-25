package com.cyberglint.springboot.exception;




import com.cyberglint.dataobject.response.CyberGlintRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CyberGlintExceptionCaptureCenter {
    
    /**
     * 处理方法参数无效异常
     *
     * @param e  方法参数无效异常
     * @return   包含错误信息的GlintRestResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CyberGlintRestResponse<String> handleInvalidMethodArgumentException(MethodArgumentNotValidException e) {
        return createErrorResponse("方法参数异常:", e, extractErrorMessage(e));
    }
    
    /**
     * 从方法参数无效异常中提取错误信息
     *
     * @param e 方法参数无效异常
     * @return 错误信息
     */
    private String extractErrorMessage(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if (!errorMessage.isEmpty()) {
                errorMessage.append("; ");
            }
            errorMessage.append(fieldError.getDefaultMessage());
        }
        return errorMessage.toString();
    }
    
    /**
     * 处理业务异常
     *
     * @param e 业务异常
     * @return 包含错误信息的GlintRestResponse
     */
    @ExceptionHandler(CyberGlintBizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CyberGlintRestResponse<String> handleBusinessException(CyberGlintBizException e) {
        return createErrorResponse("业务异常:", e, e.getMessage());
    }
    
    /**
     * 处理其他未知异常
     *
     * @param e 未知异常
     * @return 包含错误信息的GlintRestResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CyberGlintRestResponse<String> handleUnknownException(Exception e) {
        return createErrorResponse("exception:", e, "INTERNAL_SERVER_ERROR");
    }
    
    /**
     * 创建错误响应
     *
     * @param logMessage 日志信息
     * @param e 异常
     * @param responseMessage 响应消息
     * @return 包含错误信息的GlintRestResponse
     */
    private CyberGlintRestResponse<String> createErrorResponse(String logMessage, Exception e, String responseMessage) {
        return CyberGlintRestResponse.error(responseMessage);
    }
}