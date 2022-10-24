package br.com.alanadiastech.apijunit5.services;

import br.com.alanadiastech.apijunit5.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
