package com.techfest.whatsapp.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UnsplashService {

    @Value("${unsplash.api.key}")
    private String UNSPLASH_ACCESS_KEY;

    public String getRandomImageUrl(String category) {
        RestTemplate restTemplate = new RestTemplate();

        // Build the Unsplash API URL
        String url = "https://api.unsplash.com/photos/random?query=" + category + "&client_id=" + UNSPLASH_ACCESS_KEY;

        // Make the HTTP GET request
        String response = restTemplate.getForObject(url, String.class);

        // Parse the JSON response
        JSONObject jsonObject = new JSONObject(response);

        // Extract the image URL from the JSON response

        return jsonObject.getJSONObject("urls").getString("regular");
    }
}
