package org.dclab.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MarkMapperI {
	
	@Insert("INSERT INTO mark(paperId,topicId,reviewer,mark,time,batch,pic) VALUES (#{paperId},#{topicId},#{userId},#{point},#{time},#{batch},#{pic})")
	public int add(@Param("paperId")int paperId,@Param("topicId")int topicId,@Param("userId")String userId,
			@Param("point")int mark,@Param("time")int time,@Param("batch")int batch,@Param("pic")String pic);
}
