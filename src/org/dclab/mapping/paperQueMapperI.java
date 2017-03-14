package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.dclab.model.Question;

//paperId_question表相关操作
public interface paperQueMapperI {
	@Select("SELECT DISTINCT questionNum FROM `paperid_question`")//获取一份试卷的试题序号
	public List<Integer> getQuestionNum();
	
	@Select("SELECT DISTINCT questionId FROM `paperid_question`")//获取试题id的list
	public List<Integer> getQuestionId();
	
	@Select("SELECT paperId,questionName,questionMark FROM `paperid_question` WHERE questionId=#{questionId}")
	public Question getQueByQueId(int questionId);
}
