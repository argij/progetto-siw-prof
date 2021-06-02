package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"it.uniroma3.siw"})
public class SiwSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwSpringSecurityApplication.class, args);
	}

}
