package org.dclab.service;

import java.util.HashMap;
import java.util.Map;

import org.dclab.mapping.UserMapperI;
import org.dclab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
	private UserMapperI userMapperI;

	public void setUserMapperI(UserMapperI userMapperI) {
		this.userMapperI = userMapperI;
	}
	
	
	public Object login(String id,String password){
		User user = userMapperI.getUserById(id);
		if(user.getPassword().equals(password))
		{
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			System.out.println("role:"+user.getRole());
			map.put("role", user.getRole());
			return map;
		}
		else{
			System.out.println("here");
			return null;
		}
	}
}
