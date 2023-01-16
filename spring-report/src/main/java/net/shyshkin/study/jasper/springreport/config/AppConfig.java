package net.shyshkin.study.jasper.springreport.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfig {

    @Bean
    Faker faker() {
        return Faker.instance(Locale.ENGLISH);
    }
}
