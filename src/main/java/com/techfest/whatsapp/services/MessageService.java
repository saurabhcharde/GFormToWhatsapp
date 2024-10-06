package com.techfest.whatsapp.services;

import com.techfest.whatsapp.dto.EventFormTemplateMessage;
import com.techfest.whatsapp.dto.FormResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Value("${auth.token}")
    private String AUTH_TOKEN;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UnsplashService unsplashService;

    public void sendMessage(FormResponseDto responseDto) {

        try {
            String url = "https://graph.facebook.com/v19.0/327260627142829/messages";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTH_TOKEN);

            String imgUrl = unsplashService.getRandomImageUrl(responseDto.getEvent());

            EventFormTemplateMessage eventFormTemplateMessage = new EventFormTemplateMessage();

            String jsonBody = eventFormTemplateMessage.create(imgUrl, responseDto);

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            log.info("Request {}", jsonBody);

            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        }
        catch(Exception e){
            log.info("Exception: {}",e.getMessage());
        }
    }
}
