package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dclab.model.Question;
import org.dclab.model.Topic;

public interface TopicMapperI {
	@Select("SELECT * FROM `topic` WHERE `status` !=2 AND topicNum=#{topicNum} LIMIT 10")
	public List<Topic> getTenTopic(int topicNum);
	
	@Select("SELECT DISTINCT topicNum  FROM `topic` ;")
	public List<Integer> getTopicNumList();
	
	@Update("UPDATE topic SET `status`=2 WHERE id=#{id}")
	public int updateStatus(int id);
	
	
	//******************
	@Select("SELECT id,examineeNumber,paperId,questionId,dir FROM `topic` WHERE `status`!=2 AND questionId=#{questionId} LIMIT 10;")
	public List<Question> getQueByQueId(int questionId);
	
	@Update("UPDATE topic SET `status`=2 WHERE id=#{id}")
	public int updateStatus1(int id);
}
