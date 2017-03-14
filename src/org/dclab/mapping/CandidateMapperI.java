package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dclab.model.Candidate;

public interface CandidateMapperI {
	@Select("SELECT profession,rank,paperId,examineeNumber,`name`,gender,cardId,examSite,startTime,handleStatus FROM `candidate`;")
	public List<Candidate> getCandidateInfo();
	
	@Select("SELECT profession,rank,paperId,examineeNumber,`name`,gender,cardId,examSite,startTime,handleStatus FROM `candidate` WHERE `name` = #{name} AND profession=#{profession} AND rank=#{rank} AND paperId=#{paperID} AND handleStatus=#{handleStatus}")
	public List<Candidate> canQuery(@Param("name")String name,
			@Param("profession")String profession,
			@Param("rank")String rank,
			@Param("paperId")int paperId,
			@Param("handleStatus")String handleStatus);//考生信息界面查询
}
