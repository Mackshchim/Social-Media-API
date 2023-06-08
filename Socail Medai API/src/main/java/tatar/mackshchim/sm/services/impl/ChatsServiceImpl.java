package tatar.mackshchim.sm.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tatar.mackshchim.sm.dto.chat.ChatDTO;
import tatar.mackshchim.sm.dto.chat.MessageDTO;
import tatar.mackshchim.sm.exceptions.NotFoundException;
import tatar.mackshchim.sm.models.Message;
import tatar.mackshchim.sm.repositories.MessagesRepository;
import tatar.mackshchim.sm.services.ChatsService;


@Service
@RequiredArgsConstructor
public class ChatsServiceImpl implements ChatsService {


    private final MessagesRepository messagesRepository;

    @Value("${default.page-size.messages}")
    private int DEFAULT_PAGE_SIZE;


    @Override
    public ChatDTO getChat(Long firstUserID, Long secondUserID, int page) {

        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);

        Page<Message> messagePage = messagesRepository
                .findAllMessagesBetween(pageRequest, firstUserID, secondUserID)
                .orElseThrow(() -> new NotFoundException("The chat of users with ID <" + firstUserID + "> and <" + secondUserID + "> is not found"));

        return ChatDTO.builder()
                .firstUserID(firstUserID)
                .secondUserID(secondUserID)
                .messages(MessageDTO.from(messagePage.getContent()))
                .totalPagesCount(messagePage.getTotalPages())
                .build();
    }
}
