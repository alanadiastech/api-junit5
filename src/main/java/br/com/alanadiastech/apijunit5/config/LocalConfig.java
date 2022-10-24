package br.com.alanadiastech.apijunit5.config;

import br.com.alanadiastech.apijunit5.domain.User;
import br.com.alanadiastech.apijunit5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        User u1 = new User(null, "Alana Dias Tech 1", "alanadiastech1@gmail.com", "123456");
        User u2 = new User(null, "Alana Dias Tech 2", "alanadiastech2@gmail.com", "123456");

        repository.saveAll(List.of(u1, u2));
    }
}
