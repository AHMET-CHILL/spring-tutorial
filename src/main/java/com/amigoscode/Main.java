package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/greet")
    public GreetResponse great(){
         GreetResponse  response= new GreetResponse(
                "Hello!",
                List.of("Java","Golang","Javascript"),
                new Person("Alex",22,615)

        );
         return response;
    }

    record Person(String name,int age,double savings){}

    record GreetResponse(
            String greet, List<String> favProgrammingLanguages,
            Person person
            ){ }

    /*class GreetResponse{
        private final String greet;
        public GreetResponse(String greet) {
            this.greet = greet;
        }
        public String getGreet() {
            return greet;
        }

        @Override
        public String toString() {
            return "GreetResponse{"+
                    "greet="+greet+'\''+
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GreetResponse that = (GreetResponse) o;
            return Objects.equals(greet, that.greet);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(greet);
        }

     */
    }

