package ru.vsu.cs.volchenko.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public static final File RESOURCE_FOLDER = new File(MvcConfig.class.getClassLoader().getResource("").getFile());
    public static final File SAVES_FOLDER = new File(RESOURCE_FOLDER, "static/photos/");
    public static final File SCRIPTS_FOLDER = new File(RESOURCE_FOLDER, "static/scripts/");
    public static final File STYLESHEETS_FOLDER = new File(RESOURCE_FOLDER, "static/stylesheets/");

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/photo/**")
                .addResourceLocations("file://" + SAVES_FOLDER);

        registry.addResourceHandler("*/script/**")
                .addResourceLocations("file://" + SCRIPTS_FOLDER);

        registry.addResourceHandler("*/stylesheets/**")
                .addResourceLocations("file://" + STYLESHEETS_FOLDER);
    }

}
