package com.ry.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.otherTest.BeanTest;

public class ApplicationTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/spring-config.xml" });
//		context.start();
		BeanTest bean = (BeanTest) context.getBean("test");
		System.out.println(bean.getPro1());
	}
}
