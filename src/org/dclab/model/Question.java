package org.dclab.model;
//传给阅卷页面的实体

import java.util.List;

public class Question {
	private int userId;//评阅老师id
	private int id;
	private int paperId;
	private int questionId;
	private String questionName;
	private String examineeNumber;
	private int questionMark;
	private List<Detail> details;
	private String dir;
	private int status;//1评还是2评
	private int completedNum;//已完成数量
	private	int needComNum;//需完成数量
	
	
	
	public int getCompletedNum() {
		return completedNum;
	}
	public void setCompletedNum(int completedNum) {
		this.completedNum = completedNum;
	}
	public int getNeedComNum() {
		return needComNum;
	}
	public void setNeedComNum(int needComNum) {
		this.needComNum = needComNum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getExamineeNumber() {
		return examineeNumber;
	}
	public void setExamineeNumber(String examineeNumber) {
		this.examineeNumber = examineeNumber;
	}
	public int getQuestionMark() {
		return questionMark;
	}
	public void setQuestionMark(int questionMark) {
		this.questionMark = questionMark;
	}
	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
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
