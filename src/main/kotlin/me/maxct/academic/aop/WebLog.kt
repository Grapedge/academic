package me.maxct.academic.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * 日志记录
 * Created by imaxct on 17-9-1.
 */
@Aspect
@Component
class WebLog {

    val startTime: ThreadLocal<Long> = ThreadLocal()
    private val logger: Logger = LoggerFactory.getLogger(WebLog::class.java)

    @Pointcut("execution(* me.maxct.academic.controller.*.*(..))")
    fun webLog() {
    }

    @Before("webLog()")
    fun beforeExec(joinPoint: JoinPoint) {
        startTime.set(System.currentTimeMillis())

        val attrs: ServletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val req = attrs.request

        logger.info("\nURL: ${req.requestURI}\nMETHOD: ${req.method}\nIP: ${req.remoteAddr}\n" +
            "CLASS_METHOD: ${joinPoint.signature.declaringTypeName}.${joinPoint.signature.name}\n" +
            "ARGS: ${joinPoint.args.joinToString()}")
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    fun afterExec(ret: Any?) {
        logger.info("\nRESPONSE: $ret\nTIME_SPENT: ${System.currentTimeMillis() - startTime.get()} ms")
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    fun afterThrow(ex: Exception){
        logger.error("Exception", ex)
    }
}