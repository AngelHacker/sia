package com.sia.havenondemand;

import java.util.List;

public class AnalyzeSentimentResult {
	
	public AnalyzeSentimentResult() {
	}

	public AnalyzeSentimentResult(List<SentimentDetails> positive, List<SentimentDetails> negative,
			SentimentDetailsAggregate aggregate) {
		this.positive = positive;
		this.negative = negative;
		this.aggregate = aggregate;
	}

	public List<SentimentDetails> getPositive() {
		return positive;
	}

	public void setPositive(List<SentimentDetails> positive) {
		this.positive = positive;
	}

	public List<SentimentDetails> getNegative() {
		return negative;
	}

	public void setNegative(List<SentimentDetails> negative) {
		this.negative = negative;
	}

	public SentimentDetailsAggregate getAggregate() {
		return aggregate;
	}

	public void setAggregate(SentimentDetailsAggregate aggregate) {
		this.aggregate = aggregate;
	}

	private List<SentimentDetails> positive;
	private List<SentimentDetails> negative;
	private SentimentDetailsAggregate aggregate;
}
