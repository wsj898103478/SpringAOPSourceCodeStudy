package com.wsjkk.springioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/SpringApplication.xml");
        Object person = context.getBean("person1");
        System.out.println(person);
    }


    /**
     * 根据bean类型从IOC容器中获取bean的实例
     */
    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/SpringApplication.xml");
        Person bean = context.getBean("person1",Person.class);
        System.out.println(bean);
    }

    @Test
    public void test03(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/SpringApplication.xml");
//        Car car = conte
    }

    @Test
    public void test04(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/SpringApplication.xml");
        context.getBean(AirPlane.class);
    }

    @Test
    public void test05(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/SpringApplication.xml");
        context.getBean("airPlane2");
    }

    @Test
    public void test06(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/SpringApplication.xml");
        Object myFactoryBeanImple = context.getBean("myFactoryBeanImple");
        System.out.println(myFactoryBeanImple);
    }

}
