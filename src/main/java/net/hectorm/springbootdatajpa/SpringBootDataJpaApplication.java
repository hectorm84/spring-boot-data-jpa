package net.hectorm.springbootdatajpa;

import net.hectorm.springbootdatajpa.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

    @Autowired
    IFileUploadService fileUploadService;

    public static void main(String[] args){
        SpringApplication.run(SpringBootDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileUploadService.deleteAll();
        fileUploadService.init();
    }
}
