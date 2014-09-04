package aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Administrator 通过aop拦截后执行具体操作
 */
@Aspect
@Component
public class LogIntercept {

	// @Pointcut("execution(* aop.*.*(..))")
	// public void recordLog(){
	//
	// }

	// @Before("execution(* aop.*.*(..))")
	public void before() {
		this.printLog("已经记录下操作日志@Before 方法执行前");
	}
	/**
	 * aop功能的反射获取注解的配置
	 * @param pjp
	 * @throws Throwable
	 */
	@Around("@annotation(MethodIntercept)")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		this.printLog("已经记录下操作日志@Around 方法执行前");

		String targetName = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		Object[] arguments = pjp.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Class<?>[] argClass = new Class[arguments.length];
		for (int i = 0; i < argClass.length; i++) {
			argClass[i] = arguments[i].getClass();
		}
		Method m = targetClass.getMethod(methodName, argClass);
		MethodIntercept mi = m.getAnnotation(MethodIntercept.class);
		System.out.println(mi.second());
		pjp.proceed();
		this.printLog("已经记录下操作日志@Around 方法执行后");
	}

	// @After("execution(* aop.*.*(..))")
	public void after() {
		this.printLog("已经记录下操作日志@After 方法执行后");
	}

	// @AfterThrowing("execution(* aop.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) {

		this.printLog("已经记录下操作日志@AfterThrowing 方法执行后"
				+ joinPoint.getSignature().getName());
	}

	private void printLog(String str) {
		System.out.println(str);
	}
}