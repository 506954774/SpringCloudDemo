/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meettingfilm.backend_test_provider;




import com.meettingfilm.backend_common.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ServerExceptionHandler  {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity exception(Exception exception, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        if (exception instanceof AdminException) {
            AdminException e= (AdminException) exception;
            return new ResponseEntity(e.getErrorCode(), false, e.getMessage());
        }

        return new ResponseEntity("500", false, "服务器错误，请联系管理员！");
    }
}