package web.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import web.mvc.service.UserAuthenticationService;

import javax.annotation.PostConstruct;

@EnableWebMvc
@Configuration
@ComponentScan("web.mvc")
@PropertySource("classpath:application.properties")

public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Bean
    public MappedInterceptor myInterceptor()
    {
        String[] exclude = new String[6];
        exclude[0] = "/";
        exclude[1] = "/loginPage";
        exclude[2] = "/login";
        exclude[3] = "/registrationPage";
        exclude[4] = "/addUser";
        exclude[5] = "/static/**";
        return new MappedInterceptor(null,exclude, new LoginInterceptor(userAuthenticationService));
    }

    @Bean
    public MappedInterceptor myFirmInterceptor()
    {
        String[] exclude = new String[9];
        exclude[0] = "/";
        exclude[1] = "/loginPage";
        exclude[2] = "/login";
        exclude[3] = "/registrationPage";
        exclude[4] = "/addUser";
        exclude[5] = "/static/**";
        exclude[6] = "/chooseFirm/**";
        exclude[7] = "/chooseFirm/**/**";
        exclude[8] = "/firms/**";
        return new MappedInterceptor(null,exclude, new FirmInterceptor(userAuthenticationService));
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Bean
    public RestTemplate restTemplateHCCHRF() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }


}
