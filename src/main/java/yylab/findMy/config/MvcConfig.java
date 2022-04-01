package yylab.findMy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("user");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        //registry.addViewController("/home").setViewName("home");
    }

}
