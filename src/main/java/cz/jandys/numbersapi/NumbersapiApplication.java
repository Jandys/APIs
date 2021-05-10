package cz.jandys.numbersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class NumbersapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumbersapiApplication.class, args);
    }

}
