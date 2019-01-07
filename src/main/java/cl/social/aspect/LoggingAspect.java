package cl.social.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect  {

    @Around("execution(* *(..)) && @annotation(cl.social.annotation.Loggable)")
    public Object aroundCreation(ProceedingJoinPoint joinPoint) throws Throwable {
        
        for(Object arg : joinPoint.getArgs()) {
            log.info("{} [{}] param: {}",
                    joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(),
                    arg);
        }
                
        Object result = joinPoint.proceed();
        
        log.info("{} [{}] resultado: {}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                result);
        
        return result;
    }
}
