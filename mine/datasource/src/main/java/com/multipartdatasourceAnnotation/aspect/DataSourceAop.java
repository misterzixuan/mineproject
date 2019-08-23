package com.multipartdatasourceAnnotation.aspect;


import com.multipartdatasourceAnnotation.annotation.MineDataSource;
import com.multipartdatasourceAnnotation.config.DataSourceNames;
import com.multipartdatasourceAnnotation.config.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

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
    @Pointcut("@annotation(mineDataSource)")      /*根据注解扫描*/
    public void pointcut(MineDataSource mineDataSource) {
    }

    @Before("pointcut(mineDataSource)")
    public Object datasourceBefore(JoinPoint joinPoint, MineDataSource mineDataSource) {
        System.out.println("前置通知");

        return null;
    }

    @Around("pointcut(mineDataSource)")
    public Object datasourceAround(ProceedingJoinPoint joinPoint, MineDataSource mineDataSource) {
        System.out.println("环绕通知");
        String value = (String) getDataByAnnotation(joinPoint);
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

    @After("pointcut(mineDataSource)")
    public Object datasourceAfter(JoinPoint joinPoint, MineDataSource mineDataSource) {
        System.out.println("后置通知");
        return null;
    }

    @AfterThrowing(throwing = "e", pointcut = "pointcut(mineDataSource)")
    public Object datasourceAfterThrowing(JoinPoint joinPoint, Exception e, MineDataSource mineDataSource) {
        System.out.println("后置异常通知");
        return null;
    }

    @AfterReturning(returning = "res", pointcut = "pointcut(mineDataSource)")
    public void datasourceAfterReturning(JoinPoint joinPoint, Object res, MineDataSource mineDataSource) {
        System.out.println("后置返回结果通知");
    }


    @Override
    public int getOrder() {
        return 0;
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
