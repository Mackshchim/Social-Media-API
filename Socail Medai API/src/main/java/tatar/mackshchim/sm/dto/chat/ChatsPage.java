package tatar.mackshchim.sm.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Schema(description = "The chats page and all pages count")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatsPage {


    private Long ownerID;

    private List<Long> interlocutorIDs;

    private Integer totalPagesCount;

}
