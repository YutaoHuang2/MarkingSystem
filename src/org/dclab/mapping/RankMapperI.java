package org.dclab.mapping;

import org.apache.ibatis.annotations.Select;

public interface RankMapperI {
	@Select("SELECT rankId FROM `rank` WHERE rankName=#{rankName}")
	public String getIdByName(String rankName);
}
