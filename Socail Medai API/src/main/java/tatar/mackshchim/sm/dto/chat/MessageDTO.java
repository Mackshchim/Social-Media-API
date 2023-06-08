package tatar.mackshchim.sm.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tatar.mackshchim.sm.models.Message;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "Message info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {

    @Schema(description = "Message's text")
    private String messageText;

    @Schema(description = "Message sent time")
    private Date sendTime;

    @Schema(description = "Message's sender ID")
    private Long senderID;

    @Schema(description = "Recipient ID")
    private Long recipientID;

    public static MessageDTO from(Message message) {
        return MessageDTO.builder()
                .messageText(message.getMessageText())
                .sendTime(message.getSendTime())
                .senderID(message.getSender().getId())
                .recipientID(message.getRecipient().getId())
                .build();
    }

    public static List<MessageDTO> from(List<Message> messages) {
        return messages.stream()
                .map(MessageDTO::from)
                .collect(Collectors.toList());
    }

}
