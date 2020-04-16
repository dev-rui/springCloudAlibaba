package com.example.discoveryclient.retryer;


import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;



@Log4j2
@Aspect
@Service
public class TechlogRetryerAspect {


    @Around(value = "@annotation(TechlogRetryer)")
    public Object monitorAround(ProceedingJoinPoint pjp) throws Throwable {
        Method method;
        if (pjp.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            method = signature.getMethod();
        } else {
            log.error("Monitor Annotation not at a method {}", pjp);
            return null;
        }
        TechlogRetryer retryerAnnotation = method.getDeclaredAnnotation(TechlogRetryer.class);
        if (retryerAnnotation.maxDelayMsec() <= 0 && retryerAnnotation.maxAttempt() <= 1) {
            return pjp.proceed();
        }
        RetryerBuilder retryer = RetryerBuilder.newBuilder();
        if (retryerAnnotation.waitMsec() > 0) {
            retryer.withWaitStrategy(WaitStrategies.fixedWait(retryerAnnotation.waitMsec(), TimeUnit.MILLISECONDS));
        }
        if (retryerAnnotation.retryThrowable().length > 0) {
            for (Class retryThrowable : retryerAnnotation.retryThrowable()) {
                if (retryThrowable != null && Throwable.class.isAssignableFrom(retryThrowable)) {
                    retryer.retryIfExceptionOfType(retryThrowable);
                }
            }
        }
        if (retryerAnnotation.maxDelayMsec() > 0) {
            retryer.withStopStrategy(StopStrategies.stopAfterDelay(retryerAnnotation.maxDelayMsec(), TimeUnit.MILLISECONDS));
        }
        if (retryerAnnotation.maxAttempt() > 0) {
            retryer.withStopStrategy(StopStrategies.stopAfterAttempt(retryerAnnotation.maxAttempt()));
        }
        String retrylog = pjp.getTarget().getClass().getCanonicalName() + "." + method.getName();
        return retryer.build().call(() -> {
            try {
                log.info("<TECHLOG_RETRYER>" + retrylog);
                return pjp.proceed();
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            }
        });
    }
}
