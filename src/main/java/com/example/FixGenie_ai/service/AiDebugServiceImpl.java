package com.example.FixGenie_ai.service;

import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.FixGenie_ai.dto.AiDebugRequestDto;
import com.example.FixGenie_ai.dto.AiDebugResponseDto;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AiDebugServiceImpl implements AiDebugService {

    private static final Logger logger = LoggerFactory.getLogger(AiDebugServiceImpl.class);

    @Value("${fixgenie.openrouter.api.key}")
    private String apiKey;

    @Value("${fixgenie.openrouter.api.url}")
    private String apiUrl;

    @Override
    public AiDebugResponseDto generateDebugSolution(AiDebugRequestDto request) {
        String prompt = "You're a senior developer. Help debug this " + request.getLanguage() + " code or error:\n\n"
                      + request.getErrorText() + "\n\n"
                      + "Give:\n1. A clear fix\n2. Explanation\n3. Confidence";

        try {
            OkHttpClient client = new OkHttpClient();

            JSONObject json = new JSONObject()
                .put("model", "mistralai/mistral-7b-instruct")
                .put("messages", new org.json.JSONArray()
                    .put(new JSONObject().put("role", "system").put("content", "You are a helpful AI code debugger."))
                    .put(new JSONObject().put("role", "user").put("content", prompt))
                )
                .put("temperature", 0.7);

            RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

            Request httpRequest = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

            Response response = client.newCall(httpRequest).execute();

            if (response.isSuccessful() && response.body() != null) {
                String result = response.body().string();
                JSONObject resJson = new JSONObject(result);
                String reply = resJson.getJSONArray("choices")
                                      .getJSONObject(0)
                                      .getJSONObject("message")
                                      .getString("content");

                String fixedCode = "";
                Matcher matcher = Pattern.compile("```(?:\\w+)?\\n([\\s\\S]*?)\\n```").matcher(reply);
                if (matcher.find()) {
                    fixedCode = matcher.group(1).trim();
                }

                return new AiDebugResponseDto(fixedCode, reply, 0.92);
            } else {
                String errorMsg = response.body() != null ? response.body().string() : "No error body";
                logger.error("AI API error response: {}", errorMsg);
                return new AiDebugResponseDto("Failed to connect to AI", errorMsg, 0.0);
            }

        } catch (IOException e) {
            logger.error("Exception while connecting to OpenRouter API", e);
            return new AiDebugResponseDto("Error occurred while contacting AI", e.getMessage(), 0.0);
        }
    }
}
