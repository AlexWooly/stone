package com.stone.demo.conf;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stone.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Resource
//    private MyUserDetailsServiceImpl userDetailsService;

//    @Autowired
//    VerifyCodeFilter verifyCodeFilter;

//    @Autowired
//    FindByIndexNameSessionRepository sessionRepository;

    @Override
    public void configure(WebSecurity web) {
        // 忽略前端静态资源 css js 等
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
    }

    //监听session的销毁
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 设置密码加密方式，验证密码的在这里
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    JwtUtils jwtUtils;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许无授权访问 "/login"、"/register" "/register-save"
        // 其他地址的访问均需验证权限
        http.authorizeRequests()
                .antMatchers("/login", "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 用户名和用户密码参数名称
                .passwordParameter("password")
                .usernameParameter("username")
                // 指定登录页面
                .loginPage("/login")
                .successHandler((req,resp,authentication)->{
                    resp.setContentType("application/json;charset=utf-8");
                    OutputStream outputStream = resp.getOutputStream();
                    String json = new ObjectMapper().writeValueAsString(new JsonData(authentication.getPrincipal()));
                    outputStream.write(json.getBytes());
                    outputStream.flush();
                    outputStream.close();
                })
                //.failureForwardUrl("/login.html") //失败
                .failureHandler((rep,resp,exception)->{
                    resp.setContentType("application/json;charset=utf-8");
                    OutputStream outputStream = resp.getOutputStream();
                    String json = new ObjectMapper().writeValueAsString(new JsonData(exception.getMessage()));
                    outputStream.write(json.getBytes());
                    outputStream.flush();
                    outputStream.close();
                })
                .permitAll()
                .and()
                //开启csrf防护
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .and()
                //防止固话攻击
                .sessionFixation()
                .migrateSession()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功");
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("尚未登录，请先登录");
                            out.flush();
                            out.close();
                        }
                );

//        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

