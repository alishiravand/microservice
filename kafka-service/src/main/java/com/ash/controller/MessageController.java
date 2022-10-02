package com.ash.controller;

import com.ash.model.Message;
import com.ash.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Shiravand, 9/28/22 8:45 PM
 */
@RestController
@RequestMapping("api/v2/message")
public class MessageController {

    public final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> publish(@RequestBody Message message) {
        messageService.publish(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<Message>> findAll() {
        return ResponseEntity.ok(messageService.findAll());
    }

}
