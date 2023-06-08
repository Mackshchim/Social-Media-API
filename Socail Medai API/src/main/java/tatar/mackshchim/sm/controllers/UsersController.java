package tatar.mackshchim.sm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tatar.mackshchim.sm.controllers.api.UsersAPI;
import tatar.mackshchim.sm.dto.user.NewOrEditedUserDTO;
import tatar.mackshchim.sm.dto.user.UserDTO;
import tatar.mackshchim.sm.dto.user.UsersPage;
import tatar.mackshchim.sm.services.UsersService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UsersController implements UsersAPI {

    private final UsersService usersService;

    @Override
    public ResponseEntity<UserDTO> getUser(Long userID) {
        return ResponseEntity.ok(usersService.getUser(userID));
    }

    @Override
    public ResponseEntity<UsersPage> getAllUsers(int page) {
        return ResponseEntity.ok(usersService.getAllUsers(page));
    }

    @Override
    public ResponseEntity<UserDTO> addUser(@Valid NewOrEditedUserDTO newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.addUser(newUser));
    }

    @Override
    public ResponseEntity<?> followUser(Long userID) {
        usersService.followUser(userID);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<?> unfollowUser(Long userID) {
        usersService.unfollowUser(userID);
        return ResponseEntity.accepted().build();
    }
}
