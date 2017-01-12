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

import org.dclab.mapping.PaperMapperI;
import org.dclab.mapping.TempleMapperI;
import org.dclab.mapping.TopicMapperI;
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
	
	private static Map<String, Set<Integer>> idToReaded = new HashMap<>(); 
	private static Map<Integer, List<Topic>> topicPool = new HashMap<>();//试题池
	
/*	public MarkService() {
		List<Integer> list = topicMapperI.getTopicNumList();
		System.out.println("list的size:"+list.size());
		for(int i : list){
			List<Topic> list2 = topicMapperI.getTenTopic(i);
			System.out.println("list2的size:"+list2.size());
			topicPool.put(i, list2);//每一道大题填充10道题
		}
	}*/
	
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
				topic.setDir(dirToPic(topic.getDir()));//把保存的图片名转为二进制流
				return topic;
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
		System.out.println("picture:"+picture);
		return picture;
	}

}
