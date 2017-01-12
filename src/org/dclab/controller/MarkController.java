package org.dclab.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.xml.stream.events.StartDocument;

import org.dclab.mapping.MarkMapperI;
import org.dclab.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import junit.framework.Test;
import sun.misc.BASE64Decoder;

@RestController
@RequestMapping("/mark")
public class MarkController {
	
	@Autowired
	private MarkService markService;
	@Autowired
	private MarkMapperI markMapperI;
	
	public void setMarkService(MarkService markService) {
		this.markService = markService;
	}
	
	
	@RequestMapping("/start")
	public Object initStart(){
		return markService.init();
	}
	
	@RequestMapping("/test")
	public int test1(){
		return 7;
	}
	@RequestMapping("/getTopic")//获取题目
	public Object markStart(@RequestParam(value="userId")String userId,@RequestParam(value="topicNum")int topicNum){
		
		return markService.getTopic(userId, topicNum);
		
	}
	
	@RequestMapping("/store")
	public Object getNext(@RequestParam(value="image")String image,
			@RequestParam(value="point")int point,
			@RequestParam(value="time")int time,
			@RequestParam(value="userId")String userId,
			@RequestParam(value="paperId")int paperId,
			@RequestParam(value="topicId")int topicId){
		
		image = image.replace(' ', '+');
		System.out.println(image);
		//首先将传来的数据存入数据库
		BASE64Decoder decoder = new BASE64Decoder();
		String pic = paperId+"-"+topicId+"-"+userId+".png";
		String dir = System.getProperty("project.root")+"marked"+File.separator;//图片文件夹位置
		try {
			File picFile = new File(dir+pic);
			FileOutputStream outputStream = new FileOutputStream(picFile);
			outputStream.write(decoder.decodeBuffer(image));//将图片存入文件夹
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int batch = 1 ;
		markMapperI.add(paperId, topicId, userId, point, time, batch, pic);
		
		return "存储成功";
		
	}
}
