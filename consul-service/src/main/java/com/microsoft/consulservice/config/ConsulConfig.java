package com.microsoft.consulservice.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoRegistration;
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

    private static Log log = LogFactory.getLog(ConsulConfig.class);

    @Autowired(required=false)
    private ConsulAutoServiceRegistration registration;

    @Autowired
    private ConsulAutoRegistration consulAutoRegistration;

    @Autowired
    private Environment environment;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (registration !=null){
            String portNumber = environment.getProperty("spring.cloud.consul.discovery.port");
            registration.setPort(Integer.parseInt(portNumber));
            consulAutoRegistration.getService().setAddress(environment.getProperty("spring.cloud.consul.discovery.hostname"));
            log.info("Registering service to:" + consulAutoRegistration.getHost() + " " + consulAutoRegistration.getPort());

            registration.start();
        }
    }
}