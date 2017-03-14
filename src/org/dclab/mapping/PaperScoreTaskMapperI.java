package org.dclab.mapping;

import org.apache.ibatis.annotations.Select;

public interface PaperScoreTaskMapperI {
		
	@Select("SELECT questionId FROM `paperid_scoretask` WHERE userId = #{userId}")
	public int getIdByUserId(int userId);
}
