package com.Solution.CRM.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessage {
    private String sender;
    //private String senderEmailId;
    private String content;
}