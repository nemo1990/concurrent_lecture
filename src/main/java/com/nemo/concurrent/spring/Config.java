package com.nemo.concurrent.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("com.nemo.concurrent.spring")
@EnableAsync
public class Config {

}
