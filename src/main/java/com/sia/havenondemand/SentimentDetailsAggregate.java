package com.sia.havenondemand;

public class SentimentDetailsAggregate {

	public SentimentDetailsAggregate() {
	}

	public SentimentDetailsAggregate(String sentiment, double score) {
		this.sentiment = sentiment;
		this.score = score;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	private String sentiment;
	private double score;
}
