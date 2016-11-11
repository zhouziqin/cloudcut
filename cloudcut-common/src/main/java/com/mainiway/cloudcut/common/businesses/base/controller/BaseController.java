package com.mainiway.cloudcut.common.businesses.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.common.businesses.base.service.BaseService;

@RestController
@RequestMapping(value = "/base")
public class BaseController {

	@Autowired
	private BaseService baseService;


}
