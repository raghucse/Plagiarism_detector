package edu.neu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PlagarismDetectorApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		Log.info("Starting the plagiarism detection application");
		SpringApplication.run(PlagarismDetectorApplication.class, args);
		Log.info("Done starting the plagiarism detection application");
	}
}
