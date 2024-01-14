package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
	INSTANCE;

	private ModelMapper modelMapper;

	// ModelMapper를 설정 변경 하려면 getConfiguration()을 이용해서 private으로 선언된 필드도 접근 가능하도록 설정을 변경하고 
	// get()을 이용해서 ModelMapper를 사용 할 수 있도록 구성한다.
	MapperUtil() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	public ModelMapper get() {
		return modelMapper;
	}

}
