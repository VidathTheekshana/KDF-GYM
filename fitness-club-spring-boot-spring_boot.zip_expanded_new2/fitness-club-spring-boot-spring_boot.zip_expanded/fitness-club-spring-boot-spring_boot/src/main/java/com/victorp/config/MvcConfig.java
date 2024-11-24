package com.victorp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addClientToGroupWorkout").setViewName("addClientToGroupWorkout");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/blog").setViewName("blog");
        registry.addViewController("/classes").setViewName("classes");
        registry.addViewController("/classes-single").setViewName("classes-single");
        registry.addViewController("/event").setViewName("event");
        registry.addViewController("/schedule").setViewName("schedule");
        registry.addViewController("/administration").setViewName("administration");
        registry.addViewController("/trainerPage").setViewName("trainerPage");
        registry.addViewController("/createWorkoutPersonal").setViewName("createWorkoutPersonal");
        registry.addViewController("/update_user").setViewName("update_user");



    }
}
