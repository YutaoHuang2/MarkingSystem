package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.dclab.model.User;

public interface UserMapperI {
	@Select("SELECT * FROM `user` WHERE id=#{id}")
	public User getUserById(String id);
}
