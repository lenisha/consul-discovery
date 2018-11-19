package com.microsoft.consulservice.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConditionalOnConsulEnabled
@ConditionalOnMissingBean(type= "org.springframework.cloud.consul.discovery.ConsulLifecycle")
//@AutoConfigurationAfter(ConsulAutoServiceRegistrationAutoConfiguration.class)
public class ConsulConfig implements ApplicationContextAware {

    @Autowired(required=false)
    private ConsulAutoServiceRegistration registration;

    @Autowired
    private Environment environment;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (registration !=null){
            String portNumber = environment.getProperty("spring.cloud.consul.discovery.port");
            registration.setPort(Integer.parseInt(portNumber));
            registration.start();
        }
    }
}