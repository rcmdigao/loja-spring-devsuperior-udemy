package com.lojanelioalves.api.config;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(1, "Electronics");
        Categoria cat2 = new Categoria(2, "Books");
        Categoria cat3 = new Categoria(3, "Computers");
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

    }
}
