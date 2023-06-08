package tatar.mackshchim.sm.services;

import tatar.mackshchim.sm.dto.chat.ChatDTO;

public interface ChatsService {
    ChatDTO getChat(Long firstUserID, Long secondUserID, int page);
}
