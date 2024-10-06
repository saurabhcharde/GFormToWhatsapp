package com.techfest.whatsapp.dto;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventFormTemplateMessage {

    public String create(String imageUrl, FormResponseDto responseDto){

        // Create the image object
        JSONObject imageObject = new JSONObject();
        imageObject.put("link", imageUrl);

        // Create the header parameters array with image type
        JSONObject headerParameter = new JSONObject();
        headerParameter.put("type", "image");
        headerParameter.put("image", imageObject);

        JSONArray headerParametersArray = new JSONArray();
        headerParametersArray.put(headerParameter);

        // Create the header component
        JSONObject headerComponent = new JSONObject();
        headerComponent.put("type", "header");
        headerComponent.put("parameters", headerParametersArray);

        // Create the body parameters array with text type
        JSONObject bodyParameter1 = new JSONObject();
        bodyParameter1.put("type", "text");
        bodyParameter1.put("text", "*Name* : " + responseDto.getName());

        JSONObject bodyParameter2 = new JSONObject();
        bodyParameter2.put("type", "text");
        bodyParameter2.put("text", "*Company* : " + responseDto.getCompany());

        JSONObject bodyParameter3 = new JSONObject();
        bodyParameter3.put("type", "text");
        bodyParameter3.put("text", "*Phone* : " + responseDto.getPhone());

        JSONObject bodyParameter4 = new JSONObject();
        bodyParameter4.put("type", "text");
        bodyParameter4.put("text", "*Event* : " + responseDto.getEvent());

        JSONObject bodyParameter5 = new JSONObject();
        bodyParameter5.put("type", "text");
        bodyParameter5.put("text", "*Date* : " + responseDto.getDate());

        JSONArray bodyParametersArray = new JSONArray();
        bodyParametersArray.put(bodyParameter1);
        bodyParametersArray.put(bodyParameter2);
        bodyParametersArray.put(bodyParameter3);
        bodyParametersArray.put(bodyParameter4);
        bodyParametersArray.put(bodyParameter5);

        // Create the body component
        JSONObject bodyComponent = new JSONObject();
        bodyComponent.put("type", "body");
        bodyComponent.put("parameters", bodyParametersArray);

        // Create the components array
        JSONArray componentsArray = new JSONArray();
        componentsArray.put(headerComponent);
        componentsArray.put(bodyComponent);

        // Create the language object
        JSONObject languageObject = new JSONObject();
        languageObject.put("code", "en");

        // Create the template object
        JSONObject templateObject = new JSONObject();
        templateObject.put("name", "event_registration_template");
        templateObject.put("language", languageObject);
        templateObject.put("components", componentsArray);

        // Create the final JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messaging_product", "whatsapp");
        jsonObject.put("recipient_type", "individual");
        jsonObject.put("to", "917058072526");
        jsonObject.put("type", "template");
        jsonObject.put("template", templateObject);

        return jsonObject.toString();
    }
}
