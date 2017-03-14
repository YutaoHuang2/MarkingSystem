package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dclab.model.Paper;

public interface PaperMapperI {
	
	@Select("SELECT subjectId FROM `paper` WHERE id=#{paperId}")
	public int getSubjectIdByPaperId(int paperId);
	
	
	@Select("SELECT paperId,paperName,profession,rank,module,project,totalPage,remark,`status` FROM `paper`")
	public List<Paper> getPaperInfo();
	
	@Select("SELECT paperId,paperName,profession,rank,module,project,totalPage,remark,`status` FROM `paper` WHERE paperName=#{paperName} AND profession=#{profession} AND rank=#{rank} AND status=#{status}")
	public List<Paper> paperQuery(@Param("paperName")String paperName,@Param("profession")String profession,
			@Param("rank")String rank,@Param("status")int status);//试卷信息界面查询
}
