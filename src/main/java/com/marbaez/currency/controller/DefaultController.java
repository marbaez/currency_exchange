package com.marbaez.currency.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marbaez.currency.error.NotFoundResourceException;

@Controller
public class DefaultController {
	
	@RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request)  throws NotFoundResourceException{
        String uri = request.getRequestURI();
        throw new NotFoundResourceException("There is no resource for path " + uri);
    }	
}
