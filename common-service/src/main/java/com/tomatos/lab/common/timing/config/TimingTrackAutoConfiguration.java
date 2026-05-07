package com.tomatos.lab.common.timing.config;

import com.tomatos.lab.common.timing.aspect.TimingTrackAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ConditionalOnClass(Aspect.class)
public class TimingTrackAutoConfiguration {

    @Bean
    public TimingTrackAspect timingTrackAspect() {
        return new TimingTrackAspect();
    }
}
