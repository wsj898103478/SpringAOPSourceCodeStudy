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
 * AnnotationAwareAspectJAutoProxyCreator
 * 1）、每一个bean创建之前，调用postProcessBeforeInstantiation()；
 *      关心MathCalculator和LogAspect的创建
 *      1）、判断当前bean是否在advisedBeans中（保存了所有需要增强bean）Object cacheKey = getCacheKey(beanClass, beanName);this.advisedBeans.containsKey(cacheKey)
 *      2）、判断当前bean是否是基础类型的isInfrastructureClass(beanClass) Advice、Pointcut、Advisor、AopInfrastructureBean 或者是否跳过切面shouldSkip(beanClass, beanName)
 *      3）、是否需要跳过
 *          1）、获取候选的增强器（切面里面的通知方法）List<Advisor> candidateAdvisors
 *          每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor；
 *          判断每一个增强器是否是AspectJPointcutAdvisor 类型的；返回true
 *          2）、永远返回false
 * 2）、创建对象
 *  postProcessAfterInitialization：
 *      return wrapIfNecessary(bean, beanName, cacheKey); //包装如果需要的情况下
 *      1）、获取当前bean的所有增强器（通知方法）Object[] specificInterceptors
 *          1、找到候选的所有的增强器（找哪些通知方法是需要切入当前bean方法的）
 * 3）、目标方法执行：
 *      容器保存了组建的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象等等）
 *      1）、CglibAopProxy.intercept();拦截目标方法的执行
 *      2）、根据ProxyFactory对象获取将要执行的目标方法拦截器链；
 *          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          1）、List<Object> interceptorList保存所有拦截器 （5）
 *              一个默认的org.springframework.aop.interceptor.ExposeInvocationInterceptor.ADVISOR
 *              四个增强器
 *          2）、遍历所有的增强器，将其转为
 *                  registry.getInterceptors(advisor);
 *                  Interceptor[] interceptors;
 *          3）、将增强器转为List<MethodInterceptor> interceptors；
 *              如果是MethodInterceptor【advice instanceof MethodInterceptor】，直接加入到集合中
 *              如果不是，使用AdvisorAdapter adapter.supportsAdvice(advice)然后加入到集合adapter.getInterceptor(advisor)中
 *              返回转换完成的MethodInterceptor
 *              拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *
 *      3）、如果没有拦截器链，直接执行目标方法
 *      4）、如果有拦截器链，把需要执行的目标对象，目标方法，拦截器链等信息传入创建一个new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 *      retVal = processReturnType(proxy, target, method, retVal);
 *      5）、拦截器链的触发过程；
 *          1）、如果没有拦截器执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（执行到了最后一个拦截器）执行目标方法
 *      总结：
 *      1）： @EnableAspectJAutoProxy 开启AOP功能
 *      2）： @EnableAspectJAutoProxy 会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *      3）：AnnotationAwareAspectJAutoProxyCreator是一个后置处理器；
 *      4）：容器的创建过程：
 *          1）、registerBeanPostProcessors()注册后置处理器；
 *          2）、finishBeanFactoryInitialization()初始化剩下的单实例bean
 *              1）、创建业务逻辑组件和切面组件
 *              2）、拦截组件的创建过程
 *              3）、组件创建完之后，判断组件是否需要增强
 *                  是：切面的通知方法，包装成增强器（Advisor）；给业务逻辑组件创建一个代理对象
 *      5）、执行目标方法：、
 *          1）、代理对象执行目标方法
 *          2）、CglibAopProxy.intercept();
 *              1）、得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *              2）、利用拦截器的链式机制，依次进入每个拦截器进行执行；
 *              3）、效果：
 *                      正常执行：前置通知--》目标方法--》后置通知--》返回通知
 *                      出现异常：前置通知--》目标方法--》后置通知--》异常通知
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
