package com.example.FixGenie_ai.dto;

public class AiDebugResponseDto {
    private String solution;
    private String explanation;
    private double confidence;

    public AiDebugResponseDto() {}

    public AiDebugResponseDto(String solution, String explanation, double confidence) {
        this.solution = solution;
        this.explanation = explanation;
        this.confidence = confidence;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
