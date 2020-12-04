package ru.vmmb.java.examples.exampleweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExampleWebApplication.class, args);
	System.out.println("==== Start project ================");
    }

}
