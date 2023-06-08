package tatar.mackshchim.sm.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tatar.mackshchim.sm.dto.user.NewOrEditedUserDTO;
import tatar.mackshchim.sm.dto.user.UserDTO;
import tatar.mackshchim.sm.dto.user.UsersPage;
import tatar.mackshchim.sm.exceptions.AlreadyExistsException;
import tatar.mackshchim.sm.exceptions.NotFoundException;
import tatar.mackshchim.sm.models.User;
import tatar.mackshchim.sm.models.UserRelation;
import tatar.mackshchim.sm.repositories.UserRelationsRepository;
import tatar.mackshchim.sm.repositories.UsersRepository;
import tatar.mackshchim.sm.services.UsersService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {


    private final UsersRepository usersRepository;

    private final UserRelationsRepository userRelationsRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${default.page-size.users}")
    private int DEFAULT_PAGE_SIZE;


    @Override
    public UserDTO getUser(Long userID) {
        User user = getUserOrElseThrow(userID);
        return UserDTO.from(user);
    }

    @Override
    public UsersPage getAllUsers(int page) {
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<User> usersPage = usersRepository.findAll(pageRequest);
        return UsersPage.builder()
                .users(UserDTO.from(usersPage.getContent()))
                .totalPagesCount(usersPage.getTotalPages()).build();
    }

    @Transactional
    @Override
    public UserDTO addUser(NewOrEditedUserDTO newUser) {
        User createdUser = User.builder()
                .email(newUser.getEmail())
                .username(newUser.getUsername())
                .hashPassword(passwordEncoder.encode(newUser.getPassword()))
                .build();

        usersRepository.save(createdUser);
        return UserDTO.from(createdUser);
    }


    @Transactional
    @Override
    public void followUser(Long userToFollowID) {
        if (hasNoRelation(getCurrentUserID(), userToFollowID)) {

            User relatingUser = getUserOrElseThrow(getCurrentUserID());
            User relatedUser = getUserOrElseThrow(userToFollowID);

            if (hasRelation(userToFollowID, getCurrentUserID())) {

                List<UserRelation> friendship = new LinkedList<>();
                friendship.add(
                        UserRelation
                                .builder()
                                .relatingUser(relatingUser)
                                .relationType(UserRelation.RelationType.FRIEND)
                                .relatedUser(relatedUser)
                                .build());

                UserRelation usersToFollowRelation = userRelationsRepository
                        .findByRelatingUserIdAndRelatedUserId(
                                userToFollowID,
                                getCurrentUserID()
                        ).get();

                usersToFollowRelation.setRelationType(UserRelation.RelationType.FRIEND);
                friendship.add(usersToFollowRelation);

                userRelationsRepository.saveAll(friendship);

            } else {

                Optional<UserRelation> followingUserRelationOptional = userRelationsRepository
                        .findByRelatingUserIdAndRelatedUserId(getCurrentUserID(),userToFollowID);

                UserRelation newRelation;

                        if (followingUserRelationOptional.isPresent()) {
                            newRelation = followingUserRelationOptional.get();
                        } else {
                            newRelation = UserRelation
                                    .builder()
                                    .relatingUser(relatingUser)
                                    .relationType(UserRelation.RelationType.FOLLOWING)
                                    .relatedUser(relatedUser)
                                    .build();
                        }

                userRelationsRepository.save(newRelation);
            }


        } else {
            throw new AlreadyExistsException("Relation " +
                    "from this user(id: <" + getCurrentUserID() + ">) " +
                    "to user with id <" + userToFollowID + " already exists");
        }

    }


    @Transactional
    @Override
    public void unfollowUser(Long userToUnfollowID) {

        if (hasRelation(getCurrentUserID(), userToUnfollowID)) {

            UserRelation unfollowingUserRelation = userRelationsRepository
                    .findByRelatingUserIdAndRelatedUserId(getCurrentUserID(), userToUnfollowID).orElse(null);
            UserRelation userToUnfollowRelation = userRelationsRepository
                    .findByRelatingUserIdAndRelatedUserId(userToUnfollowID, getCurrentUserID()).orElse(null);

            if (hasRelation(userToUnfollowID, getCurrentUserID())) {


                List<UserRelation> brokenUpFriendship = new LinkedList<>();     //So sad...
                unfollowingUserRelation.setRelationType(UserRelation.RelationType.RELATION_DELETED);
                userToUnfollowRelation.setRelationType(UserRelation.RelationType.FOLLOWING);

                brokenUpFriendship.add(unfollowingUserRelation);
                brokenUpFriendship.add(userToUnfollowRelation);

                userRelationsRepository.saveAll(brokenUpFriendship);

            } else {

                unfollowingUserRelation.setRelationType(UserRelation.RelationType.RELATION_DELETED);
                userRelationsRepository.save(unfollowingUserRelation);

            }


        } else {
            throw new NotFoundException("Relation " +
                    "from this user(id: <" + getCurrentUserID() + ">) " +
                    "to user with id <" + userToUnfollowID + " is not found");
        }
    }

    private User getUserOrElseThrow(Long userID) {

        return usersRepository.findById(userID)
                .orElseThrow(
                        () -> new NotFoundException("The User with ID <" + userID + "> is not found")
                );
    }

    private boolean hasNoAnyRelationBetween(Long firstUserID, Long secondUserID) {

        boolean firstRelatingToSecond = hasNoRelation(firstUserID,secondUserID);
        boolean secondRelatingToFirst = hasNoRelation(secondUserID,firstUserID);

        return firstRelatingToSecond && secondRelatingToFirst;
    }

    private boolean hasRelation(Long firstUserID, Long secondUserID) {

        Optional<UserRelation> userRelation = userRelationsRepository
                .findByRelatingUserIdAndRelatedUserId(
                        firstUserID,
                        secondUserID);

        if (userRelation.isPresent()) {
            return userRelation.get().hasRelation();
        } else {
            return false;
        }
    }

    private boolean hasNoRelation(Long firstUserID, Long secondUserID) {

        return !hasRelation(firstUserID, secondUserID);

    }

    public Long getCurrentUserID() {
        return usersRepository.findByEmail(
                ((UserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUsername()
        ).get().getId();
    }

}
