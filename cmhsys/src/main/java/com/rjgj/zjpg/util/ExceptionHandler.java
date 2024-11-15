package com.rjgj.zjpg.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public Map JsonError(Exception e){
        Map map = new HashMap();
        map.put("isOk",false);
//
        if(e instanceof MyException){
            map.put("msg",e.getMessage());
        }else {
            map.put("msg",e.getMessage());
        }

        return map;
    }
}
