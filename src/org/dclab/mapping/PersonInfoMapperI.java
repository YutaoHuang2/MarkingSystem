package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dclab.model.PersonInfo;
import org.dclab.model.QualityInfo;

public interface PersonInfoMapperI {
	@Select("SELECT `name`,company FROM `personinfo` WHERE userId=#{id}")
	public QualityInfo getNameComById(int id);//得到名字和单位
	
	
	@Select("SELECT userId,userName,`name`,company,phoneNumber,roleId FROM `personinfo`;")
	public List<PersonInfo> getPersonInfo();
	
	
	//用户信息界面的查询
	@Select("SELECT personinfo.userId,userName,`name`,company,phoneNumber,personinfo.roleId FROM `personinfo` INNER JOIN personquality ON personinfo.userId=personquality.userId WHERE `name`=#{name} OR professionId=#{professionId} OR rankId=#{rankId};")
	public List<PersonInfo> personQuery(@Param("name")String name,@Param("professionId")String professionId,@Param("rankId")String rankId);
}
