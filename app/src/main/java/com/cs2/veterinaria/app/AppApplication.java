package com.cs2.veterinaria.app;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.cs2.veterinaria.app.adapters.inputs.LoginInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@SpringBootApplication
public class AppApplication implements CommandLineRunner{
	
    @Autowired
    private ListableBeanFactory beanFactory;
    
    @Autowired
    private LoginInput loginInput;
	public void run(String... args) throws Exception {
        /*System.out.println("Beans registrados en la aplicación:");
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    	loginInput.menu();
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
