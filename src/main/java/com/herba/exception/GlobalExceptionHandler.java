package com.herba.exception;

import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice("com.herba.controller")
@RestController
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        logger.error("", e);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage(e.getMessage());
        if (e instanceof Exception) {
            dataResponse.setCode(ResponseCode.FAIL.getCode());
        }
        dataResponse.setData("");
        return dataResponse;
    }
}
