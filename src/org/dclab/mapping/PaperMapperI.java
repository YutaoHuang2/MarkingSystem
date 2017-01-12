package org.dclab.mapping;

import org.apache.ibatis.annotations.Select;

public interface PaperMapperI {
	
	@Select("SELECT subjectId FROM `paper` WHERE id=#{paperId}")
	public int getSubjectIdByPaperId(int paperId);
}
