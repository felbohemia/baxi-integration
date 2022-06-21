package com.atamie.baxiservices.configuration;

//import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        /*
          Ensures that a script in any origin
          can call or load a resource in this service

          CORS is an accronym for cross origin resource sharing,
          and is actually a means of allowing the browser to permit
          a resource loaded from one origin to access or load or request a
          resource in another origin. Usually, cors is enabled on the server
         */
        registry.addMapping("/**").allowedOrigins("/**");
    }
}
