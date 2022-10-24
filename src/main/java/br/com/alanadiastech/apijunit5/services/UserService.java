package br.com.alanadiastech.apijunit5.services;

import br.com.alanadiastech.apijunit5.domain.User;

public interface UserService {

    User findById(Integer id);
}
