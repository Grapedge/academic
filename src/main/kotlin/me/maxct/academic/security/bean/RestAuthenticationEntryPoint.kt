package me.maxct.academic.security.bean

import me.maxct.academic.bean.Msg
import me.maxct.academic.util.StringUtil
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by imaxct on 17-9-2.
 */
@Component
class RestAuthenticationEntryPoint : AuthenticationEntryPoint{
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        response!!.writer.write(StringUtil.toJson(Msg.err(authException?.message ?: "认证错误,重新登录")))
    }
}