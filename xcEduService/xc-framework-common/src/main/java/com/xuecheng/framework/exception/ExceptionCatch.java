package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author
 * @date 2019-10-22
 */
@ControllerAdvice //控制器增强
public class ExceptionCatch {

    private static final Logger LOGGER= LoggerFactory.getLogger(ExceptionCatch.class);

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException c){

        LOGGER.error("catch exception:{}",c.getMessage());
        return new ResponseResult(c.getResultCode());
    }
}
