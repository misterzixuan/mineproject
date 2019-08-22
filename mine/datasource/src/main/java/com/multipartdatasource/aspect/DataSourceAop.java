package com.multipartdatasource.aspect;


import com.multipartdatasource.annotation.MineDataSource;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class DataSourceAop implements Ordered {


    // @Pointcut("execution(* com.multipartdatasource.business..*.*(..))")   /*根据报名扫描business包下的所有子孙包，的所有类*/
    @Pointcut("@annotation(mineDataSource)")      /*根据注解扫描*/
    public void pointcut(MineDataSource mineDataSource) {
    }


    @Before("pointcut(mineDataSource)")
    public Object datasourceBefore(JoinPoint joinPoint,MineDataSource mineDataSource) {
        System.out.println("前置通知");
        System.out.println("before"+getDataByAnnotation(mineDataSource));
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        String[] parameters = methodSignature.getParameterNames();
        return null;
    }

    @Around("pointcut(mineDataSource)")
    public Object datasourceAround(ProceedingJoinPoint joinPoint,MineDataSource mineDataSource) {
        System.out.println("环绕通知");
        try {
            //Object[] o={"a","b"};
           // joinPoint.proceed(o);
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @After("pointcut(mineDataSource)")
    public Object datasourceAfter(JoinPoint joinPoint,MineDataSource mineDataSource) {
        System.out.println("后置通知");
        return null;
    }

    @AfterThrowing(throwing = "e",pointcut = "pointcut(mineDataSource)")
    public Object datasourceAfterThrowing(JoinPoint joinPoint,Exception e,MineDataSource mineDataSource) {
        System.out.println("后置异常通知");
        return null;
    }

    @AfterReturning(returning = "res",pointcut = "pointcut(mineDataSource)")
    public void datasourceAfterReturning(JoinPoint joinPoint,Object res,MineDataSource mineDataSource) {
        System.out.println("后置返回结果通知");
    }


    @Override
    public int getOrder() {
        return 0;
    }


    /**
     * @Description获取注解的{MineDataSource}内容
     * @Author zhangzheng
     * @param
     * @Return
     * @Create: 2019-08-22 16:15
     * @Version:
     */
    private static Object getDataByAnnotation(Object annotation){
        MineDataSource mineDataSource= (MineDataSource) annotation;
        String name = mineDataSource.name();
        return name;
    }
}
