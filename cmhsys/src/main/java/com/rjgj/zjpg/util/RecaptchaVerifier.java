package com.rjgj.zjpg.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RecaptchaVerifier {

    @Value("${recaptcha.secret-key}")
    private String secretKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean verify(String recaptchaResponse) {
        String url = "https://www.google.com/recaptcha/api/siteverify";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("secret", secretKey);
        requestBody.put("response", recaptchaResponse);

        Map<String, Object> response = restTemplate.postForObject(url, requestBody, Map.class);

        return response != null && (Boolean) response.get("success");
    }
}
