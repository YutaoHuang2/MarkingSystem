package org.dclab.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.dclab.model.PersonQuality;
import org.dclab.model.QualityInfo;

public interface QualityMapperI {
	@Select("SELECT * FROM `personquality`;")
	public List<PersonQuality> getQuaInfo();
}
