package com.sia.havenondemand;

import java.util.List;

public class AutoCompleteResult {

	public AutoCompleteResult() {
		
	}

	public AutoCompleteResult(List<String> words) {
		this.words = words;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}

	private List<String> words;
}
