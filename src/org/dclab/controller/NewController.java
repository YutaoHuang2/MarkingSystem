package org.dclab.controller;

import java.util.List;

import org.dclab.model.Candidate;
import org.dclab.model.Paper;
import org.dclab.model.PersonInfo;
import org.dclab.model.PersonQuality;
import org.dclab.model.Profession;
import org.dclab.model.QualityInfo;
import org.dclab.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class NewController {
	@Autowired
	private NewService newService;

	public void setNewService(NewService newService) {
		this.newService = newService;
	}
	
	@RequestMapping("/paperInfo")//试卷信息页面需要数据
	public List<Paper> getPaperInfo(){
		
		return newService.getPaperInfo();
		
	}
	
	@RequestMapping("/paperQuery")//试卷信息页面查询
	public List<Paper> paperQuery(@RequestParam(value="paperName")String paperName,
			@RequestParam(value="profession")String profession,
			@RequestParam(value="rank")String rank,
			@RequestParam(value="status")int status){
		
		return newService.paperInfoQuery(paperName, profession, rank, status);
		
	} 
	
	@RequestMapping("/canInfo")//考生信息页面需要数据
	public List<Candidate> getCanInfo(){
		return newService.getCandidateInfo();
	}
	
	@RequestMapping("/canQuery")//考生信息页面查询
	public List<Candidate> canQuery(@RequestParam(value="name")String name,
			@RequestParam(value="profession")String profession,
			@RequestParam(value="rank")String rank,
			@RequestParam(value="paperId")int paperId,
			@RequestParam(value="handleStatus")String handleStatus){
		return newService.canQuery(name, profession, rank, paperId, handleStatus);
	}
	
	@RequestMapping("/professionInfo")//职业信息页面数据
	public List<Profession> getPro()
	{
		return newService.getProfession();
	}
	
	@RequestMapping("/qualityInfo")//资质信息页面数据
	public List<PersonQuality> getQuaInfo(){
		return newService.getQuaInfo();
	}
	
	@RequestMapping("/personInfo")//用户信息界面数据
	public List<PersonInfo> getPersonInfo()
	{
		return newService.getPersonInfo();
	}
	
	@RequestMapping("/personQuery")//用户信息界面查询
	public List<PersonInfo> personQuery(@RequestParam(value="name")String name,
			@RequestParam(value="professionName")String professionName,
			@RequestParam(value="rankName")String rankName){
		return newService.personQuery(name, professionName, rankName);
	}
}
