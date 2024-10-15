package com.kopring.boilerplate.common.config


import com.kopring.boilerplate.common.config.interceptors.HavitInterceptor
import com.kopring.boilerplate.common.config.interceptors.LoggingInterceptor
import com.kopring.boilerplate.common.config.interceptors.PublicInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val loggingInterceptor: LoggingInterceptor, private val publicInterceptor: PublicInterceptor,  private val havitInterceptor: HavitInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loggingInterceptor)
        registry.addInterceptor(havitInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/swagger",
                "/api/**"
            )
        registry.addInterceptor(publicInterceptor)
            .addPathPatterns("/**")

    }
}