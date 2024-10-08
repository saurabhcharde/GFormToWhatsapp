package com.techfest.whatsapp.services;

import com.techfest.whatsapp.dto.EventFormTemplateMessage;
import com.techfest.whatsapp.dto.FormResponseDto;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MessageService {

    @Value("${facebook.whatsapp.endpoint}")
    private String ENDPOINT;

    @Value("${auth.token}")
    private String AUTH_TOKEN;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UnsplashService unsplashService;

    public void sendMessage(FormResponseDto responseDto) {

        try {
            String imgUrl = unsplashService.getRandomImageUrl(responseDto.getEvent());

            EventFormTemplateMessage eventFormTemplateMessage = new EventFormTemplateMessage();
            String jsonBody = eventFormTemplateMessage.create(imgUrl, responseDto);

            log.info("Request {}", jsonBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(AUTH_TOKEN);
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            restTemplate.exchange(ENDPOINT, HttpMethod.POST, entity, String.class);
        }
        catch(Exception e){
            log.info("Exception: {}",e.getMessage());
        }
    }
}
