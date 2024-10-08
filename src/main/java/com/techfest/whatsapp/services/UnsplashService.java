package com.techfest.whatsapp.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UnsplashService {

    @Value("${unsplash.api.key}")
    private String UNSPLASH_ACCESS_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public String getRandomImageUrl(String category) {

        String url = "https://api.unsplash.com/photos/random?query=" + category + "&client_id=" + UNSPLASH_ACCESS_KEY;

        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(response);

        return jsonObject.getJSONObject("urls").getString("regular");
    }
}
