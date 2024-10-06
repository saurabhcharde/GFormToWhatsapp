package com.techfest.whatsapp.controllers;

import com.techfest.whatsapp.dto.FormResponseDto;
import com.techfest.whatsapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class FormController {

    @Autowired
    private MessageService messageService;

    @PostMapping("send-message")
    public String sendMessage(@RequestBody FormResponseDto response) {
        System.out.println("started service");
        messageService.sendMessage(response);
        return "Message sent to user";
    }
}
