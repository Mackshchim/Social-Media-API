package tatar.mackshchim.sm.services;

import org.springframework.stereotype.Service;
import tatar.mackshchim.sm.dto.user.NewOrEditedUserDTO;
import tatar.mackshchim.sm.dto.user.UserDTO;
import tatar.mackshchim.sm.dto.user.UsersPage;

@Service
public interface UsersService {
    UserDTO getUser(Long userID);

    UsersPage getAllUsers(int page);

    UserDTO addUser(NewOrEditedUserDTO newUser);

    void followUser(Long userID);

    void unfollowUser(Long userID);
}
