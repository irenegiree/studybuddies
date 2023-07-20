package com.example.studybuddies.service;

import com.example.studybuddies.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Message> getAllMessages();
    Message createMessage(Message message);
    Message updateMessage(Message message);
    void deleteMessage(long id);
    Message getMessageById(long id);


}
