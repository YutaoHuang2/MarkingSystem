package org.dclab.mapping;
//评分细则表相关操作

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.dclab.model.Detail;

public interface PaperAnswerMapperI {
	@Select("SELECT ruleId,questionRuleNum,ruleMark,ruleDescription FROM `paperid_answer` WHERE questionId=#{id}")
	public List<Detail> getDetailByQueId(int id);
}
