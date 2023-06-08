package tatar.mackshchim.sm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import tatar.mackshchim.sm.controllers.api.ChatsAPI;
import tatar.mackshchim.sm.dto.chat.ChatDTO;
import tatar.mackshchim.sm.repositories.UsersRepository;
import tatar.mackshchim.sm.services.ChatsService;


@RestController
@RequiredArgsConstructor
public class ChatsController implements ChatsAPI {

    private final ChatsService chatsService;
    private final UsersRepository usersRepository;

    @Override
    public ResponseEntity<ChatDTO> getChat(Long interlocutorID, int page) {
        return ResponseEntity.ok(chatsService.getChat(getCurrentUserID(), interlocutorID, page));
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
