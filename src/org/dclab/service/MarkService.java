package org.dclab.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dclab.mapping.PaperAnswerMapperI;
import org.dclab.mapping.PaperMapperI;
import org.dclab.mapping.PaperScoreMapperI;
import org.dclab.mapping.PaperScoreTaskMapperI;
import org.dclab.mapping.TempleMapperI;
import org.dclab.mapping.TopicMapperI;
import org.dclab.mapping.paperQueMapperI;
import org.dclab.model.Detail;
import org.dclab.model.Paper;
import org.dclab.model.Question;
import org.dclab.model.QuestionStore;
import org.dclab.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

@Service
public class MarkService {
	
	@Autowired
	private TopicMapperI topicMapperI;
	@Autowired
	private PaperMapperI paperMapperI;
	@Autowired
	private TempleMapperI templeMapperI;
	@Autowired 
	private paperQueMapperI paperQueMapperI;
	@Autowired
	private PaperAnswerMapperI paperAnswerMapperI;
	@Autowired
	private PaperScoreMapperI paperScoreMapperI;
	@Autowired
	private PaperScoreTaskMapperI paperScoreTaskMapperI;
	
	private static Map<String, Set<Integer>> idToReaded = new HashMap<>(); 
	private static Map<Integer, List<Topic>> topicPool = new HashMap<>();//试题池
	
	private static Map<Integer, List<Question>> questionPool = new HashMap<>();//新试题池
	private static Map<Integer, Set<Integer>> idToReaded1 = new HashMap<>();
/*	public MarkService() {
		List<Integer> list = topicMapperI.getTopicNumList();
		System.out.println("list的size:"+list.size());
		for(int i : list){
			List<Topic> list2 = topicMapperI.getTenTopic(i);
			System.out.println("list2的size:"+list2.size());
			topicPool.put(i, list2);//每一道大题填充10道题
		}
	}*/
	public int init1(){
		List<Integer> list = paperQueMapperI.getQuestionId();
		System.out.println("questionId的list大小："+list.size());
		for(int i : list){
			Question question = paperQueMapperI.getQueByQueId(i);//由questionId拿到question
			List<Detail> list2 = paperAnswerMapperI.getDetailByQueId(i);
			List<Question> list3 = topicMapperI.getQueByQueId(i);//拿十道题
			for(Question question2 : list3){
				question2.setDetails(list2);
				question2.setQuestionMark(question.getQuestionMark());
				question2.setQuestionName(question.getQuestionName());
			}
			System.out.println("list3的size："+list3.size());
			System.out.println("list3:"+list3.toString());
			questionPool.put(i, list3);
		}
		return questionPool.size();
		
	}
	public int init(){
		List<Integer> list = topicMapperI.getTopicNumList();
		System.out.println("list的size:"+list.size());
		for(int i : list){
			List<Topic> list2 = topicMapperI.getTenTopic(i);
			
			for(Topic topic : list2){
				int subjectId = paperMapperI.getSubjectIdByPaperId(topic.getPaperId());
				int topicNum = i;
				topic.setFullMark(templeMapperI.getFullMark(subjectId, topicNum));
				topic.setDetail(templeMapperI.getDetail(subjectId, topicNum));
			}
			
			System.out.println("list2的size:"+list2.size());
			topicPool.put(i, list2);//每一道大题填充10道题
		}
		return topicPool.size();
	}
	
	//返回对应questionNum的一道题
	public Object getQuestion(int userId) {
		
		if(questionPool.isEmpty()){
			init1();
		}
		
		if(!idToReaded1.containsKey(userId)){
			idToReaded1.put(userId, new HashSet<>());
		}
		
		//通过userId拿需要阅的questionId
		int questionId = paperScoreTaskMapperI.getIdByUserId(userId);
		
		for(Question question : questionPool.get(questionId)){
			System.out.println("id:"+question.getId());
			if(!idToReaded1.get(userId).contains(question.getId())){
				question.setStatus(question.getStatus()+1);
				if(question.getStatus()==2){
					topicMapperI.updateStatus1(question.getId());
					questionPool.get(questionId).remove(question);
					if(questionPool.get(questionId).size()<1){
						Question question1 = paperQueMapperI.getQueByQueId(questionId);
						List<Detail> list2 = paperAnswerMapperI.getDetailByQueId(questionId);
						List<Question> list3 = topicMapperI.getQueByQueId(questionId);//拿十道题
						if(list3.size()==0){
							return "没题了";
						}
						for(Question question2 : list3){
							question2.setDetails(list2);
							question2.setQuestionMark(question1.getQuestionMark());
							question2.setQuestionName(question1.getQuestionName());
						}
						System.out.println("list3的size："+list3.size());
						questionPool.put(questionId, list3);
					}
				}
				
				Question question2 = new Question();
				question2.setId(question.getId());
				question2.setDetails(question.getDetails());
				question2.setExamineeNumber(question.getExamineeNumber());
				question2.setPaperId(question.getPaperId());
				question2.setQuestionId(question.getQuestionId());
				question2.setQuestionMark(question.getQuestionMark());
				question2.setQuestionName(question.getQuestionName());
				question2.setStatus(question.getStatus());
				question2.setDir(dirToPic(question.getDir()));
				question2.setUserId(userId);
				return question2;
			}
		}
		return "没题了";
	}
	
	//返回对应topicNum的一道题
	public Object getTopic(String userId,int topicNum){
		if(!idToReaded.containsKey(userId)){//如果idtoreaded中没有该阅卷人的set，就给他新建一个。
			idToReaded.put(userId, new HashSet<>());
		}
		for(Topic topic : topicPool.get(topicNum)){
			if(!idToReaded.get(userId).contains(topic.getId())){//如果阅卷人的set中没有该题的id，就将该题传给他
				topic.setStatus(topic.getStatus()+1);//0评改为1评，1评改为2评
				if(topic.getStatus()==2)//如果已经是2评,，
				{
					topicMapperI.updateStatus(topic.getId());//就将他数据库中的状态改变
					topicPool.get(topicNum).remove(topic);//并从试题池中删除
					if(topicPool.get(topicNum).size()<1)//如果对应topicnum的试题池没题了
					{
						List<Topic> list = topicMapperI.getTenTopic(topicNum);//从数据库拿十道题
						if(list.size()==0){
							return "没题了";
						}
						topicPool.get(topicNum).addAll(list);//还有题的话加入试题池
					}
				}
				Topic newTopic = new Topic();
				newTopic.setId(topic.getId());
				newTopic.setPaperId(topic.getPaperId());
				newTopic.setTopicNum(topic.getTopicNum());
				newTopic.setDetail(topic.getDetail());
				newTopic.setFullMark(topic.getFullMark());
				newTopic.setStatus(topic.getStatus());
				newTopic.setDir(dirToPic(topic.getDir()));//把保存的图片名转为二进制流
				return newTopic;
			}	
		}
		return "没题了";
		
	}
	//图片地址转为base64编码的流
	public String dirToPic(String dir){
		String path = System.getProperty("project.root")+"mark"+File.separator+dir;//图片位置
		System.out.println("图片位置："+path);
		InputStream in=null;
		byte[] data=null;
		try{
			in=new FileInputStream(path);
			data=new byte[in.available()];
			in.read(data);
			in.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		BASE64Encoder encoder=new BASE64Encoder();
		String picture = encoder.encode(data);
		return picture;
	}
	
	public String storeQuestion(QuestionStore questionStore) {
		paperScoreMapperI.storeQue(questionStore.getPaperId(), questionStore.getQuestionId(), questionStore.getMark(), questionStore.getExamineeNumber(), questionStore.getUserId(), questionStore.getRemarkType(),questionStore.getScoreResult() , questionStore.getScoreRemark(), questionStore.getScoreTimes());
		return "保存成功";
	}
	

}