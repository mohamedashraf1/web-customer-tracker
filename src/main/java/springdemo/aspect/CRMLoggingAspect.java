package springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	// match on any class any method in controller package
	@Pointcut("execution(* springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	// do the same for service and dao
	@Pointcut("execution(* springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		// display method name
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " + method);
		
		// display arguments
		Object[] args = theJoinPoint.getArgs();
		for(Object tempArg: args) {
			myLogger.info("=====>> argument: " + tempArg);
		}
	}
	
	
	// add @AfterReturning
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		// display method we are returning from
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @AfterReturning: calling method: " + method);
		
		// display data returned
		myLogger.info("=====>> result: " + theResult);
		
	}
	
}
