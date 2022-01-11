package com.scripter.springbootrestfulwebservice.HelloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/helloWorld-Internationalized")
	public String helloWordInternationalized(@RequestHeader(name = "Accept-Language",required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/helloWorldBeanFromABean/path-variables/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("Hello World " + name);
	}
	
	@GetMapping("/helloWorld-Internationalizedv2")
	public String helloWordInternationalized2() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
