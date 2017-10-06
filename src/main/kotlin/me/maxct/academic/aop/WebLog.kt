package me.maxct.academic.aop

import me.maxct.academic.util.StringUtil
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.security.Principal

/**
 * 日志记录
 * Created by imaxct on 17-9-1.
 */
@Aspect
@Component
class WebLog {

    private val logger: Logger = LoggerFactory.getLogger(WebLog::class.java)

    @Pointcut("execution(* me.maxct.academic.controller.*.*(..))")
    fun webLog() {
    }

    @Before("webLog()  && args(..,principal)", argNames = "principal")
    fun beforeExec(joinPoint: JoinPoint, principal: Principal) {

        val attrs: ServletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val req = attrs.request
        val jsonArg = StringUtil.toJson(joinPoint.args)
        logger.info("USERNAME: ${principal.name}\nURL: ${req.requestURI}\nMETHOD: ${req.method}\nIP: ${req.remoteAddr}\n" +
            "CLASS_METHOD: ${joinPoint.signature.declaringTypeName}.${joinPoint.signature.name}\n" +
            "ARGS: $jsonArg")
    }

}