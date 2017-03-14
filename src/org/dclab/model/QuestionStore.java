package org.dclab.model;
//存储阅完的题目时用于接收的实体
public class QuestionStore {
	private int paperId;
	private int questionId;
	private int mark;//得分
	private String examineeNumber;//准考证号
	private int userId;//评阅人
	private String remarkType;//备注类型
	private String scoreResult;//评完的图片
	private String scoreRemark;//备注
	private int scoreTimes;//第几次评阅，传给前端的status
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getExamineeNumber() {
		return examineeNumber;
	}
	public void setExamineeNumber(String examineeNumber) {
		this.examineeNumber = examineeNumber;
	}
	public String getRemarkType() {
		return remarkType;
	}
	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}
	public String getScoreResult() {
		return scoreResult;
	}
	public void setScoreResult(String scoreResult) {
		this.scoreResult = scoreResult;
	}
	public String getScoreRemark() {
		return scoreRemark;
	}
	public void setScoreRemark(String scoreRemark) {
		this.scoreRemark = scoreRemark;
	}
	public int getScoreTimes() {
		return scoreTimes;
	}
	public void setScoreTimes(int scoreTimes) {
		this.scoreTimes = scoreTimes;
	}
	
	
}
