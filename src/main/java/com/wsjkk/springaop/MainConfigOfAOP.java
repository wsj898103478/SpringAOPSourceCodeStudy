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
 *                                      AnnotationAwareAspectJAutoProxyCreator
 *                                      给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *   2、AnnotationAwareAspectJAutoProxyCreator：AbstractAdvisorAutoProxyCreator
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
