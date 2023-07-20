package com.example.studybuddies.service;

import com.example.studybuddies.model.Message;
import com.example.studybuddies.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServicempl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message createMessage(Message message) {
        return this.messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Message message) {
        return this.messageRepository.save(message);
    }

    @Override
    public void deleteMessage(long id) {
        this.messageRepository.deleteById(id);
    }

    @Override
    public Message getMessageById(long id) {
        Optional<Message> optional = messageRepository.findById(id);
        Message message = null;
        if (optional.isPresent()) {
            message = optional.get();
        } else {
            throw new RuntimeException(" Message not found for id :: " + id);
        }
        return message;
    }
}
