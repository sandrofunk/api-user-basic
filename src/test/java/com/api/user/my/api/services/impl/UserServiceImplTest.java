package com.api.user.my.api.services.impl;

import com.api.user.my.api.domain.User;
import com.api.user.my.api.domain.dto.UserDTO;
import com.api.user.my.api.repositories.UserRepository;
import com.api.user.my.api.services.exceptions.DataIntegratyViolationException;
import com.api.user.my.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    public static final String USER = "USER";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final Integer ID = 1;
    public static final String PASSWORD = "PASSWORD";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final Integer INDEX = 0;
    private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    private void startUser() {
        user = new User(ID, NAME, USER, EMAIL,  PASSWORD);
        userDTO = new UserDTO(ID, NAME, USER, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, USER, EMAIL, PASSWORD));
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {

        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());

    }

    @Test
    void whenFindByIdThenReturnAndObjectNotFoundException() {

        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {

        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

    }

    @Test
    void whenCreateThenReturnSuccess() {

        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {

        when(repository.save(any())).thenReturn(user);

        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {

        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());

    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {

        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.update(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }

    }

    @Test
    void deleteWithSuccess() {

        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);

        verify(repository, times(1)).deleteById(anyInt());

    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {

        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            service.delete(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }

    }

}
