package pl.training.shop.common.profiler;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;

@Log
public class Profiler {

    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = proceedingJoinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info(String.format("%s executed in %d ns", proceedingJoinPoint.getSignature(), totalTime));
        return result;
    }

}
