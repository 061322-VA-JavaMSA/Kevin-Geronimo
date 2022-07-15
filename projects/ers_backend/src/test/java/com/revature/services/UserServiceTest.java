package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.revature.daos.ERSUserHibernate;
import com.revature.models.ERSUserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.ERSUser;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private static ERSUserHibernate mockERSUserHibernate;

    @InjectMocks
    private static UserService userService;

    @BeforeAll
    public static void setup() {
        userService = new UserService();
    }

    @Test
    public void getUserByIdExists() throws UserNotFoundException {
        ERSUser udaoExpected = new ERSUser();
        udaoExpected.setId(1);
        udaoExpected.setUsername("cabbage");
        udaoExpected.setPassword("pass");
        udaoExpected.setEmail("random@random.com");
        udaoExpected.setFirstName("kevin");
        udaoExpected.setLastName("space");
        udaoExpected.setErsUserRole(new ERSUserRole());
        udaoExpected.getErsUserRole().setId(1);
        udaoExpected.getErsUserRole().setRole("EMPLOYEE");

        ERSUser userExpected = new ERSUser();
        userExpected.setId(1);
        userExpected.setUsername("cabbage");
        userExpected.setPassword("pass");
        userExpected.setEmail("random@random.com");
        userExpected.setFirstName("kevin");
        userExpected.setLastName("space");
        userExpected.setErsUserRole(new ERSUserRole());
        userExpected.getErsUserRole().setId(1);
        userExpected.getErsUserRole().setRole("EMPLOYEE");

        Mockito.when(mockERSUserHibernate.getUser(1)).thenReturn(udaoExpected);

        ERSUser userActual = userService.getUser(1);

        assertEquals(userExpected, userActual);
    }

    @Test
    public void getUserByIdDoesNotExist() {
        Mockito.when(mockERSUserHibernate.getUser(3)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUser(3));
    }
}