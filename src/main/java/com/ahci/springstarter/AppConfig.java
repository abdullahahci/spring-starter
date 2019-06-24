package com.ahci.springstarter;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.ahci.springstarter.core.util.AuditorAwareImpl;

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
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	
	
	@Bean
    public ResourceBundleMessageSource messageSource() {

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("bundles/resources", "bundles/messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
	
	@Value("${locale}")
	private String defaultLocale;
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(new Locale(defaultLocale));
	    return slr;
	}
	
	@Value("${maxFileSize}")
	private Integer maxFileSize;
	
//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver multipartResolver() {
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(maxFileSize);
//	    return multipartResolver;
//	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	
	
	/* For audit */
	@Configuration
	@EnableJpaAuditing(auditorAwareRef = "auditorAware")
	public class JpaConfig {
	    @Bean
	    public AuditorAware<String> auditorAware() {
	        return new AuditorAwareImpl();
	    }
	}
}