package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.dclab.model.Profession;

public interface ProfessionMapperI {
	@Select("SELECT * FROM `profession`")
	public List<Profession> getProfession();
	
	
	@Select("SELECT professionName FROM `profession` WHERE professionId = #{id}")
	public String getNameById(String id);
	
	@Select("SELECT professionId FROM `profession` WHERE professionName=#{professionName}")
	public String getIdByName(String professionName);
}
