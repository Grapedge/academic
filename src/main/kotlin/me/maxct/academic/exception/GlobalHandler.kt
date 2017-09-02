package me.maxct.academic.exception

import me.maxct.academic.bean.Msg
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * Created by imaxct on 17-9-1.
 */
@ControllerAdvice
class GlobalHandler {

    @ResponseBody
    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandler(req: HttpServletRequest, ex: ServiceException): Msg<*> =
        Msg.err(ex.message ?: "请求失败,稍候再试", req.requestURI)

    @ResponseBody
    @ExceptionHandler(JwtAuthException::class)
    fun authenticationExceptionHandler(req: HttpServletRequest, ex: JwtAuthException): Msg<*> =
        Msg.err(ex.message ?: "认证过期,重新登录")

    @ResponseBody
    @ExceptionHandler(Exception::class)
    fun otherExceptionHandler(req: HttpServletRequest, ex: Exception): Msg<*> =
        Msg.err("请求失败,稍候再试")
}