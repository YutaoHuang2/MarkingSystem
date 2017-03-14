package org.dclab.model;

import java.sql.Timestamp;

public class Candidate {
	private String profession;
	private String rank;
	private String paperId;
	private String examineeNumber;
	private String name;
	private String gender;
	private String cardId;
	private String examSite;
	private Timestamp startTime;
	private String handleStatus;
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getExamineeNumber() {
		return examineeNumber;
	}
	public void setExamineeNumber(String examineeNumber) {
		this.examineeNumber = examineeNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getExamSite() {
		return examSite;
	}
	public void setExamSite(String examSite) {
		this.examSite = examSite;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public String getHandleStatus() {
		return handleStatus;
	}
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	
	
}
