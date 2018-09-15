/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package br.com.rendti.livroapi;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author rafael
 */
@SpringBootApplication
//@Controller
public class Configuracao  {
    // implements WebMvcConfigurer
    //@RequestMapping("/")
    //@ResponseBody
    public String ola(){
        return "Ola, Bem vindo ao sistema Lista VIPs";
    }
    
   
    
     public static void main(String[] args){
         SpringApplication.run(Configuracao.class, args);
     }   
     
     /*
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/listavip");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    */
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//          .addResourceHandler("/static/**")
//          .addResourceLocations("/static/"); 
//    }
  
   //  spring.mvc.static-path-pattern=/resources/**
}
