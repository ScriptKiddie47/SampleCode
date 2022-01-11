package com.scripter.springbootrestfulwebservice.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringControllerImpl implements FilteringController {
	
	@GetMapping("/getFiltering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value 1","value 2","value 3");
		
		MappingJacksonValue mappValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("someBeanFiler", filter);
		
		mappValue.setFilters(filters);
		return mappValue;
	}
	
	@GetMapping("/getFiltering-list")
	public MappingJacksonValue retrieveSomeBeanList(){
		List<SomeBean> someBeanList= Arrays.asList(new SomeBean("value 1","value 2","value 3"),new SomeBean("value 4","value 5","value 6"));
		MappingJacksonValue mappValue = new MappingJacksonValue(someBeanList);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field2","field3");
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("someBeanFiler", filter);
		
		mappValue.setFilters(filters);
		return mappValue;
	}
}
