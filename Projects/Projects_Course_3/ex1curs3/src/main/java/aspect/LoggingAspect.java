package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect   //step 2 - create a class that defines the aspect
@Component
public class LoggingAspect {

    @Around("execution(* service.*.*(..))")   //step 3 - define the advice
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        //step 4 - implement the aspect logic
        System.out.println("Method " + joinPoint.getSignature().getName() +
                " from " + joinPoint.getTarget().getClass() +
                " will be executed. Timestamp: " + LocalDateTime.now());

        joinPoint.proceed();    //invokes the target object's method
        //allows the execution to continue to call the real method which is aspected

        System.out.println("Method " + joinPoint.getSignature().getName() +
                " from " + joinPoint.getTarget().getClass() +
                " finished the execution. Timestamp: " + LocalDateTime.now());
    }
}
