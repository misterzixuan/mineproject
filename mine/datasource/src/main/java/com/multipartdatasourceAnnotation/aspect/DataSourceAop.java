package com.multipartdatasourceAnnotation.aspect;


import com.multipartdatasourceAnnotation.annotation.MineDataSource;
import com.multipartdatasourceAnnotation.config.DataSourceNames;
import com.multipartdatasourceAnnotation.config.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 *笔记：
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */


@Component
@Aspect
public class DataSourceAop implements Ordered {


    // @Pointcut("execution(* com.multipartdatasourceAnnotation.business..*.*(..))")   /*根据报名扫描business包下的所有子孙包，的所有类*/
    @Pointcut("within(@com.multipartdatasourceAnnotation.annotation.MineDataSource *)")      /*根据注解扫描*///||@annotation(mineDataSource)
    public void pointcut() {
    }

    @Before("pointcut()")
    public Object datasourceBefore(JoinPoint joinPoint) {
        System.out.println("前置通知");

        return null;
    }

    @Around("pointcut()")
    public Object datasourceAround(ProceedingJoinPoint joinPoint) {
        String value = getAnnotationValue(joinPoint);
        System.out.println("环绕通知");
        if (value != null && !value.isEmpty()) {
            DynamicDataSource.setDataSource(value);
        } else {
            DynamicDataSource.setDataSource(DataSourceNames.MINE);
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            DynamicDataSource.clearDataSource();
        }

        return null;
    }

    @After("pointcut()")
    public Object datasourceAfter(JoinPoint joinPoint) {
        System.out.println("后置通知。。。。。。");
        return null;
    }

    @AfterThrowing(throwing = "e", pointcut = "pointcut()")
    public Object datasourceAfterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("后置异常通知");
        return null;
    }

    @AfterReturning(returning = "res", pointcut = "pointcut()")
    public void datasourceAfterReturning(JoinPoint joinPoint, Object res) {
        System.out.println("后置返回结果通知");
    }


    @Override
    public int getOrder() {
        return 0;
    }


    /**
     * @Description 通过joinPoint 获取注解value值
     * @Author zhangzheng
     * @param
     * @Return
     * @Create: 2019-08-23 14:45
     * @Version:
     */
    public String getAnnotationValue(ProceedingJoinPoint joinPoint){

        if(joinPoint==null){
            return null;
        }

        //获取被代理对象
        Object target=joinPoint.getTarget();
        //获取署名信息对象
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        String annValue="";

        //先从方法上获取，如果方法上有，直接返回，
        if(targetMethod.isAnnotationPresent(MineDataSource.class)){
             annValue = targetMethod.getAnnotation(MineDataSource.class).value();
        }
        if(annValue!=null&&!annValue.isEmpty()){
            return annValue;
        }

        //当方法上的没有注解才会从类上获取注解信息
        if(target.getClass().isAnnotationPresent(MineDataSource.class)){
             annValue = target.getClass().getAnnotation(MineDataSource.class).value();
        }


        return annValue;
    }


    /**
     * @param annotation
     * @Description获取注解的{MineDataSource}内容
     * @Author zhangzheng
     * @Return Object
     * @Create: 2019-08-22 16:15
     * @Version:
     */
    private static Object getDataByAnnotation(Object annotation) {
        MineDataSource mineDataSource = (MineDataSource) annotation;
        String name = mineDataSource.value();
        return name;
    }

    /**
     * @param joinPoint
     * @Description 获取参数key：参数名，value：参数值
     * @Author zhangzheng
     * @Retun Map<String , Object>
     * @Create:2019-08-22 17:32
     * @Version:
     */
    public Map<String, Object> getArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] argNames = methodSignature.getParameterNames();
        if (args.length != argNames.length) {
            System.out.println("参数名称和参数值数量不等");
            return null;
        }
        Map<String, Object> params = new LinkedHashMap();
        for (int i = 0; i < argNames.length; i++) {
            params.put(argNames[i], args[i]);
        }
        return params;
    }
}
