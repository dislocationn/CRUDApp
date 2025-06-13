package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public SpringResourceTemplateResolver getTempRes () {
        SpringResourceTemplateResolver tempRes = new SpringResourceTemplateResolver();
        tempRes.setPrefix("/WEB-INF/pages/");
        tempRes.setSuffix(".html");
        tempRes.setTemplateMode("HTML");
        return tempRes;
    }

    @Bean
    public SpringTemplateEngine getSprEngine (SpringResourceTemplateResolver tempRes) {
        SpringTemplateEngine tempEng = new SpringTemplateEngine();
        tempEng.setTemplateResolver(tempRes);
        return tempEng;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
}

