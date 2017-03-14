package org.dclab.mapping;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface PaperScoreMapperI {
	@Update("INSERT INTO paperid_score(paperId,questionId,mark,examineeNumber,reviewer,remarkType,scoreResult,scoreRemark,scoreTimes) VALUES (#{paperId},#{questionId},#{mark},#{examineeNumber},#{userId},#{remarkType},#{scoreResult},#{scoreRemark},#{scoreTimes})")
	public int storeQue(@Param("paperId")int paperId,@Param("questionId")int questionId,
			@Param("mark")int mark,@Param("examineeNumber")String examineeNumber,
			@Param("userId")int userId,@Param("remarkType")String remarkType,
			@Param("scoreResult")String scoreResult,@Param("scoreRemark")String scoreRemark,
			@Param("scoreTimes")int scoreTimes);
}
