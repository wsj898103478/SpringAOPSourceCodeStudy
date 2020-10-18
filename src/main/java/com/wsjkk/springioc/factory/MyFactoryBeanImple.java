package com.wsjkk.springioc.factory;

import com.wsjkk.springioc.Car;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

/**
 * 实现了FactoryBean接口的类是Spring可以认识的工厂类；
 * Spring会自动的调用工厂方法创建实例
 *
 */
public class MyFactoryBeanImple implements FactoryBean<Car> {
    /**
     * getObject()：工厂方法；返回创建的对象
     * @return
     * @throws Exception
     */
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setCarName(UUID.randomUUID().toString());
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
