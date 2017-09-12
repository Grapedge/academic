package me.maxct.academic.aop

import me.maxct.academic.entity.User
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Created by imaxct on 17-9-1.
 */
@Aspect
@Component
class ServiceLog {
    private val logger: Logger = LoggerFactory.getLogger(ServiceLog::class.java)

    @Pointcut("within(me.maxct.academic.service.AdminService.*)")
    fun adminService() {
    }

    @Pointcut("within(me.maxct.academic.service.SystemService.*)")
    fun systemService(){
    }
    @Before("adminService() && systemService() && args(operator,..)", argNames = "operator")
    fun beforeService(joinPoint: JoinPoint, operator: User){
        logger.info("${operator.username} -- ${joinPoint.signature.name}")
    }
}