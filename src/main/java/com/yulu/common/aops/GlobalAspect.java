package com.yulu.common.aops;

import com.yulu.common.annotations.AutoPermission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
// 全局切面类
@Aspect //  表明是一个切面类
@Component // 将该类标记为spring管理的组件
public class GlobalAspect {
    /**
     * @Before: 前置通知,在目标方法执行之前执行。如果在此回调方法中抛出异常，则目标方法不会再执行，
     *          但会继续执行后置通知和异常通知。它可以用于在目标方法执行前进行一些准备工作，如参数校验、日志记录等。
     * @After: 后置通知, 在目标方法（切入点）执行之后执行，无论目标方法是否正常返回或抛出异常都会执行。通常用于进行资源清理、
     *          记录方法结束等操作。
     * @AfterReturning: 返回通知, 在目标方法（切入点）返回结果之后执行。需要注意的是，如果目标方法抛出了异常，将不会执行返回通知，
     *          而是直接跳转到异常通知（`@AfterThrowing`）。它可用于对目标方法的返回结果进行处理或后续的操作。
     * @Around: 环绕通知, 目标方法执行前后分别执行一些代码，类似拦截器，可以控制目标方法是否继续执行。
     *          它能够在目标方法执行前后织入增强动作，可以决定目标方法在什么时候执行、如何执行，甚至完全阻止目标方法的执行；
     *          也可以改变执行目标方法的参数值和返回值。环绕通知常用于统计方法耗时、进行参数校验、处理事务等操作。
     *
     */

    // @annotation(AutoPermission)：这是一个切点表达式，表示匹配被 @AutoPermission 注解标注的方法。只有当执行带有 @AutoPermission 注解的方法时，环绕通知才会生效
    @Around("@annotation(com.yulu.common.annotations.AutoPermission)")
    public Object authPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("您使用@AutoPermission注解");
        // 获取注解的配置值
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoPermission annotation = signature.getMethod().getAnnotation(AutoPermission.class);
        String permissionString = annotation.value();
        if (permissionString.length() > 0) {
            System.out.println("配置了权限, 进行权限校验");
        }
        System.out.println(annotation.value());

        return joinPoint.proceed();
    }
}
