package com.campushub.model;

import com.campushub.dto.MessageType;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.Instant;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private HubUser sender;

    @ManyToOne
    @JoinColumn(name="chat_id")
    private Chat chat;

    private Instant timestamp;
    private String content;
}
