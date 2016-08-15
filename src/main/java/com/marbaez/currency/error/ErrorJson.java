package com.marbaez.currency.error;

import java.util.Map;

public class ErrorJson {

    public Integer status;
    public String error;
    public String message;

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.message = (String) errorAttributes.get("message");
    }

}
