package com.wsjkk.springaop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/***
 * AOP:【动态代理】
 *  指程序运行期间动态的将某段代码切入到指定位置进行运行的编程方式；
 *  1、导入aop模块
 *  2、定义一个业务逻辑类（）
 *  3、定义一个日志切面类（LogAspects）：切面类里面的方法需要动态感知
 *      通知方法：
 *              前置通知(@Before)：
 *              后置通知（@After）：在目标方法（div）运行结束之后运行（无论方法正常结束还是异常结束）
 *              返回通知（@AfterReturning）：
 *              异常通知(@AfterThrowing)：
 *              环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.proceed()）
 *   4、给切面类的目标方法标注何时何地运行（通知注解）
 *   5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 *   6、必须告诉Spring容器哪个类是切面类（给切面类加个注解：@Aspect）
 *   7、给配置类中@EnableAspectJAutoProxy【开启基于注解的AOP模式】
 *
 *   AOP原理：【看容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
 *      @EnableAspectJAutoProxy:
 *   1、@EnableAspectJAutoProxy是什么？ @Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
 *                                      利用AspectJAutoProxyRegistrar自定义给容器中注册bean；registerAspectJAnnotationAutoProxyCreatorIfNecessary
 *                                      org.springframework.aop.config.internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *                                      给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *   2、AnnotationAwareAspectJAutoProxyCreator：
 *                                              AbstractAdvisorAutoProxyCreator-->
 *                                              AbstractAutoProxyCreator-->
 *                                              extends
 *                                              ProxyProcessorSupport
 *                                              implements
 *                                              SmartInstantiationAwareBeanPostProcessor -->InstantiationAwareBeanPostProcessor
 *                                              BeanFactoryAware-->Aware
 *                                              关注后置处理器（在bean初始化完成前后做事情）
 *
 *
 *    AbstractAutoProxyCreator.setBeanFactory
 *
 *    AbstractAutoProxyCreator.postProcessBeforeInstantiation
 *
 *    AbstractAutoProxyCreator.postProcessAfterInitialization
 *
 *    AbstractAdvisorAutoProxyCreator.setBeanFactory
 *
 *    AbstractAdvisorAutoProxyCreator.initBeanFactory
 *
 *
 *    AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 *    流程：
 *          3.1 beanFactory.getBeanNamesForType先获取ioc容器已经定义了的需要创建对象的所有postProcessorNames
 *          3.2 addBeanPostProcessor再添加自定义的post处理器
 *          3.3 priorityOrderedPostProcessors
 *          3.4 internalPostProcessors
 *          3.5 orderedPostProcessorNames
 *          3.6 nonOrderedPostProcessorNames
 *          3.7 registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors) 注册优先级高的postProcessors
 *          3.8 registerBeanPostProcessors(beanFactory, orderedPostProcessors) 注册排序的postProcessors
 *          3.9 registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors); 注册未排序的postProcessors
 *          3.10 registerBeanPostProcessors(beanFactory, internalPostProcessors); 注册内部的postprocessors
 *          3.11 注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中；
 *     4）、finishBeanFactoryInitialization(beanFactory)；完成BeanFactory初始化工作；
 *          创建剩下的单实例Bean
 *          1）、createBean()；创建bean
 *              【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *              【InstantiationAwareBeanPostProcessor是在创建Bean实力之前先尝试用后置处理器返回对象的】（从中可以看出是如何解决循环遍历问题）
 *          2）、先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建；
 *               只要创建好的Bean都会被缓存起来
 *               1）、resolveBeforeInstantiation(beanName, mbdToUse)希望后置处理器在此能返回一个代理对象；如果能返回代理对象就使用，如果不能，则创建
 *
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {
    /**
     * 业务逻辑类加入容器中
     * @return
     */
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    /**
     * 切面类加入到容器中
     * @return
     */
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
