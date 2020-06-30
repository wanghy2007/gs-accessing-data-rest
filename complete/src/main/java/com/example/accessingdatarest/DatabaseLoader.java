package com.example.accessingdatarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final PersonRepository repository;

    @Autowired
    public DatabaseLoader(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new Person("Frodo", "Baggins"));
        this.repository.save(new Person("Bilbo", "Baggins"));
        this.repository.save(new Person("Gandalf", "the Grey"));
        this.repository.save(new Person("Samwise", "Gamgee"));
        this.repository.save(new Person("Meriadoc", "Brandybuck"));
        this.repository.save(new Person("Peregrin", "Took"));
    }
}