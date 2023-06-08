package tatar.mackshchim.sm.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tatar.mackshchim.sm.dto.user.NewOrEditedUserDTO;
import tatar.mackshchim.sm.dto.user.UserDTO;
import tatar.mackshchim.sm.dto.user.UsersPage;
import tatar.mackshchim.sm.services.UsersService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {
    public static final int page = 0;
    public static final Long ID = 1L;

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersService usersService;

    @Test
    public void getAllUsers() {

        final UsersPage usersPage = mock(UsersPage.class);
        when(usersService.getAllUsers(page)).thenReturn(usersPage);

        final ResponseEntity<UsersPage> actual = usersController.getAllUsers(page);

        assertNotNull(actual);
        assertEquals(actual, ResponseEntity.ok(usersPage));
        verify(usersService).getAllUsers(page);

    }

    @Test
    public void getUser() {

        final UserDTO userDTO = mock(UserDTO.class);
        when(usersService.getUser(ID)).thenReturn(userDTO);

        final ResponseEntity<UserDTO> actual = usersController.getUser(ID);

        assertNotNull(actual);
        assertEquals(ResponseEntity.ok(userDTO),actual);
        verify(usersService).getUser(ID);

    }

    @Test
    public void addUser() {

        final NewOrEditedUserDTO newUserDTO = mock(NewOrEditedUserDTO.class);
        final UserDTO userDTO = mock(UserDTO.class);
        when(usersService.addUser(newUserDTO)).thenReturn(userDTO);

        final ResponseEntity<UserDTO> actual = usersController.addUser(newUserDTO);

        assertNotNull(actual);
        assertEquals(actual,ResponseEntity.status(HttpStatus.CREATED).body(userDTO));
        verify(usersService).addUser(newUserDTO);

    }


}
