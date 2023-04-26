package com.springboot.restapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserDetailsRepository repository;

    public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new UserDetails("pran", "Admin"));
        repository.save(new UserDetails("rob", "Admin"));
        repository.save(new UserDetails("tyson", "Admin"));

        List<UserDetails> users = repository.findAll();

        users.forEach(user -> logger.info(user.toString()));

    }
}
