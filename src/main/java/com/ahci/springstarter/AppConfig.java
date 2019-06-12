package com.ahci.springstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
//@ImportResource( {"classpath*:/rest_config.xml" } )
//@ComponentScan( basePackages = "com.ahci.spring-starter" )
//@PropertySource({ "classpath:rest.properties", "classpath:web.properties" })
public class AppConfig{
// @Bean
// public static PropertySourcesPlaceholderConfigurer properties() {
// return new PropertySourcesPlaceholderConfigurer();
// }
	
	@Bean
    public ResourceBundleMessageSource messageSource() {

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("resourcebundle/resources");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}