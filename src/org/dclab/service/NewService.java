package org.dclab.service;

import java.util.List;

import org.dclab.mapping.CandidateMapperI;
import org.dclab.mapping.PaperMapperI;
import org.dclab.mapping.PersonInfoMapperI;
import org.dclab.mapping.ProfessionMapperI;
import org.dclab.mapping.QualityMapperI;
import org.dclab.mapping.RankMapperI;
import org.dclab.model.Candidate;
import org.dclab.model.Paper;
import org.dclab.model.PersonInfo;
import org.dclab.model.PersonQuality;
import org.dclab.model.Profession;
import org.dclab.model.QualityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewService {
	
	@Autowired 
	private PaperMapperI paperMapperI;
	@Autowired
	private CandidateMapperI candidateMapperI;
	@Autowired
	private ProfessionMapperI professionMapperI;
	@Autowired
	private QualityMapperI qualityMapperI;
	@Autowired
	private PersonInfoMapperI personInfoMapperI;
	@Autowired
	private RankMapperI rankMapperI;
	
	public List<Paper> getPaperInfo()//试卷信息页面需要的数据
	{
		return paperMapperI.getPaperInfo();
	}
	
	public List<Paper> paperInfoQuery(String paperName,String profession,String rank,int status)//试卷信息页面查询
	{
		return paperMapperI.paperQuery(paperName, profession, rank, status);
	}
	
	public List<Candidate> getCandidateInfo(){//考生信息页面需要的数据
		return candidateMapperI.getCandidateInfo();
	}
	
	public List<Candidate> canQuery(String name,String profession,String rank,int paperId,String handleStatus){//考生信息页面查询
		return candidateMapperI.canQuery(name, profession, rank, paperId, handleStatus);
	}
	
	public List<Profession> getProfession(){//职业信息页面需要的数据
		return professionMapperI.getProfession();
	}
	
	public List<PersonQuality> getQuaInfo(){//资质信息页面数据
		return qualityMapperI.getQuaInfo();
	}
	
	public List<PersonInfo> getPersonInfo(){//用户信息界面数据
		return personInfoMapperI.getPersonInfo();
	}
	
	public List<PersonInfo> personQuery(String name,String professionName,String rankName){//用户信息界面查询
		String rankId = rankMapperI.getIdByName(rankName);
		String professionId = professionMapperI.getIdByName(professionName);
		return personInfoMapperI.personQuery(name, professionId, rankId);
	}
}
