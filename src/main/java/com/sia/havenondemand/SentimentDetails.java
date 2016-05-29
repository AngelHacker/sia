package com.sia.havenondemand;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentimentDetails {

	public SentimentDetails() {
	}

	public SentimentDetails(String sentiment, String topic, double score, String originalText, int originalLength, String normalizedText, int normalizedLength) {
		this.sentiment = sentiment;
		this.topic = topic;
		this.score = score;
		this.originalText = originalText;
		this.originalLength = originalLength;
		this.normalizedText = normalizedText;
		this.normalizedLength = normalizedLength;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@JsonProperty("original_text")
	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	@JsonProperty("original_length")
	public int getOriginalLength() {
		return originalLength;
	}

	public void setOriginalLength(int originalLength) {
		this.originalLength = originalLength;
	}

	@JsonProperty("normalized_text")
	public String getNormalizedText() {
		return normalizedText;
	}

	public void setNormalizedText(String normalizedText) {
		this.normalizedText = normalizedText;
	}

	@JsonProperty("normalized_length")
	public int getNormalizedLength() {
		return normalizedLength;
	}

	public void setNormalizedLength(int normalizedLength) {
		this.normalizedLength = normalizedLength;
	}

	private String sentiment;
	private String topic;
	private double score;
	private String originalText;
	private int originalLength;
	private String normalizedText;
	private int normalizedLength;
}
