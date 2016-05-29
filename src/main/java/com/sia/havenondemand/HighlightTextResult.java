package com.sia.havenondemand;

public class HighlightTextResult {

	public HighlightTextResult() {
	}

	public HighlightTextResult(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String text;
}
