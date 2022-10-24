package br.com.alanadiastech.apijunit5.services;

import br.com.alanadiastech.apijunit5.domain.User;
import br.com.alanadiastech.apijunit5.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDto obj);

    User update(UserDto obj);

    void delete(Integer id);
}
