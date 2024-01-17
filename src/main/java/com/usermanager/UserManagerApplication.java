package com.usermanager;

import com.usermanager.models.UserModel;
import com.usermanager.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class UserManagerApplication implements CommandLineRunner {

	public UserRepository userRepository;

	public UserManagerApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

	}
}
