package br.com.alanadiastech.apijunit5.services.impl;

import br.com.alanadiastech.apijunit5.domain.User;
import br.com.alanadiastech.apijunit5.domain.dto.UserDto;
import br.com.alanadiastech.apijunit5.repositories.UserRepository;
import br.com.alanadiastech.apijunit5.services.exceptions.DataIntegratyViolationException;
import br.com.alanadiastech.apijunit5.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SuppressWarnings("ALL")
@SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Nome";
    public static final String EMAIL = "nome@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    public static final String EMAIL_JÁ_CADASTRADO = "Email já cadastrado!";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado.";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDto userDto;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdReturnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);

        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try{
            service.findById(ID);
        } catch(Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void WhenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegratyViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_JÁ_CADASTRADO, ex.getMessage());
        }
    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_JÁ_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnDataIntegratyViolationException() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }


    @Test
    void WithSucess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);

        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));
        try{
            service.delete(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }


    private  void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}