package com.naneun.mall.config;

import com.naneun.mall.auth.AccessTokenResolver;
import com.naneun.mall.auth.RefreshTokenResolver;
import com.naneun.mall.interceptor.JwtTokenInterceptor;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.netty.http.client.HttpClient;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenInterceptor jwtTokenInterceptor;

    private final AccessTokenResolver accessTokenResolver;
    private final RefreshTokenResolver refreshTokenResolver;

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE);
    }

    @Bean
    public WebClient webClient(HttpClient httpClient) {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/api/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(accessTokenResolver);
        argumentResolvers.add(refreshTokenResolver);
    }
}
