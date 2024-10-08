package com.techfest.whatsapp.controllers;

import com.techfest.whatsapp.dto.FormResponseDto;
import com.techfest.whatsapp.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class FormController {

    @Autowired
    private MessageService messageService;

    @PostMapping("send-message")
    public ResponseEntity<String> sendMessage(@RequestBody FormResponseDto response) {
        log.info("sending message...");
        messageService.sendMessage(response);
        return new ResponseEntity<>("Message Sent to User successfully", HttpStatus.OK);
    }
}
