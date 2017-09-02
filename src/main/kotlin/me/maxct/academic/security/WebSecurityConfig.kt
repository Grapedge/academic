package me.maxct.academic.security

import me.maxct.academic.security.bean.RestAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Created by imaxct on 17-9-2.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired lateinit var restAuthenticationEntryPoint: RestAuthenticationEntryPoint
    @Autowired lateinit var jwtFilter: JwtAuthenticationFilter

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/auth/**")
    }

    override fun configure(http: HttpSecurity?) {
        http!!
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers(
                "/auth/**"
            ).permitAll()
            .anyRequest().authenticated().and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}