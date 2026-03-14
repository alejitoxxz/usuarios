package co.edu.uco.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"co.edu.uco.login", "com.mycompany.user", "com.co.eatupapi"})
public class LoginEatupApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginEatupApplication.class, args);
	}

}
