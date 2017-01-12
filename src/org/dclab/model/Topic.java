package org.dclab.model;

import java.sql.Blob;

public class Topic {
	private int id;//数据库id
	private int paperId;//试卷号
	private int topicNum;//题号
	private String dir;//图片位置
	private int status;//状态（一评还是二评）1是1评，2是2评
	private int fullMark;//满分
	private String detail;//评分细则
	
	public int getFullMark() {
		return fullMark;
	}
	public void setFullMark(int fullMark) {
		this.fullMark = fullMark;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
