package com.scripter.springbootrestfulwebservice.filter;

import org.springframework.http.converter.json.MappingJacksonValue;

public interface FilteringController {
	public MappingJacksonValue retrieveSomeBean();

	public MappingJacksonValue retrieveSomeBeanList();
}
