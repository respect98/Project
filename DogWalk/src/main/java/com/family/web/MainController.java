package com.family.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MainController {
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public void mainPageGET() {
		log.info("메인 페이지 진입");
	}
}
