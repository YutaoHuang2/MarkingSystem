package org.dclab.mapping;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TempleMapperI {
	
	@Select("SELECT fullMark FROM `temple` WHERE subjectId=#{subjectId} AND topicNum=#{topicNum}")
	public int getFullMark(@Param("subjectId")int subjectId,@Param("topicNum")int topicNum);
	
	@Select("SELECT detail FROM `temple` WHERE subjectId=#{subjectId} AND topicNum=#{topicNum}")
	public String getDetail(@Param("subjectId")int subjectId,@Param("topicNum")int topicNum);
}
