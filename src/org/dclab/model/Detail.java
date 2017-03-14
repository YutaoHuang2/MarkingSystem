package org.dclab.model;
//评分细则
public class Detail {
	private int ruleId;
	private int questionRuleNum;
	private int ruleMark;
	private String ruleDescription;
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public int getQuestionRuleId() {
		return questionRuleNum;
	}
	public void setQuestionRuleId(int questionRuleId) {
		this.questionRuleNum = questionRuleId;
	}
	public int getRuleMark() {
		return ruleMark;
	}
	public void setRuleMark(int ruleMark) {
		this.ruleMark = ruleMark;
	}
	public String getRuleDescription() {
		return ruleDescription;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	
	
}
