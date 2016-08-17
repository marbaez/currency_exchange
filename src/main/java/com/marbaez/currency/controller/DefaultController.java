package com.marbaez.currency.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marbaez.currency.error.NotFoundResourceException;
import com.marbaez.currency.util.Messages;

@Controller
public class DefaultController {
	
	@Autowired
	private Messages messages;
	
	@RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request)  throws NotFoundResourceException{
        String uri = request.getRequestURI();
        
        throw new NotFoundResourceException(messages.getString("default.path.exception", uri));
    }	
}
