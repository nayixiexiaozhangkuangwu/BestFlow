package bestflow.api.log;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Aspect
@Configuration
@Slf4j
public class WebLogAspect {

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 定义切点 切点为controller下所有的类
     * 其中类里的所有方法为连接点
     */
    @Pointcut("execution(* bestflow.api.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "webLog()")
    public Object webLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestId = UUID.randomUUID().toString().concat(":");
        String className = joinPoint.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = className.replaceFirst("bestflow.", "") +
                "." +
                signature.getMethod().getName() +
                "():";
        log.info("{} ===> method:{} request:{}", requestId, methodName, objectMapper.writeValueAsString(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        log.info("{} ===> method:{} response:{}", requestId, methodName, objectMapper.writeValueAsString(proceed));
        return proceed;
    }

}
