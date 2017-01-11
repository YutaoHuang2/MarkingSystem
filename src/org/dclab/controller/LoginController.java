package org.dclab.controller;

import org.dclab.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping("/login")
	public Object login(@RequestParam(value="name")String name,@RequestParam(value="password")String password){
		return loginService.login(name, password);
	}
}
