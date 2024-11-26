package com.crm.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configClass {

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
