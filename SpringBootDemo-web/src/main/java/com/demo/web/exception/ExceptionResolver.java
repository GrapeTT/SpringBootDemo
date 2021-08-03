package com.demo.web.exception;

import com.demo.common.api.Message;
import com.demo.common.exception.AppException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Name：AppExceptionResolver
 * @Package：com.demo.exception
 * @Descripition：全局异常处理器
 * @Author：涛
 * @Date：2018/4/7 14:16
 * @Version：1.0
 */
@ControllerAdvice
public class ExceptionResolver {
    /**
     * @Description：404
     * @Author：涛哥
     * @Time：2019/3/6 16:54
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public String resolve404() {
        return "errorPage/404";
    }
    
    /**
     * @Description：405
     * @Author：涛哥
     * @Time：2019/3/6 16:54
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public String resolve405() {
        return "errorPage/405";
    }
    
    /**
     * @Description：500
     * @Author：涛哥
     * @Time：2019/3/6 16:54
     */
    @ExceptionHandler(value = Exception.class)
    public String resolve500(Exception e, Model view) {
        AppException appException;
        if(e instanceof AppException){
            appException = (AppException)e;
        } else {
            appException = new AppException("对不起，服务器发生未知错误");
        }
        view.addAttribute("error", appException.getMessage());
        return "errorPage/error";
    }
}
