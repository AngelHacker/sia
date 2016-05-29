package com.sia.havenondemand;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Term {
	
	public Term() {
	}

	public Term(String term, int weight, int documents, int occurrences, int case_, int length_,
			int numeric, int start_pos, String stop_word) {
		this.term = term;
		this.weight = weight;
		this.documents = documents;
		this.occurrences = occurrences;
		this.case_ = case_;
		this.length_ = length_;
		this.numeric = numeric;
		this.start_pos = start_pos;
		this.stop_word = stop_word;
	}

	private String term;
	private int weight;
	private int documents;
	private int occurrences;
	private int case_;
	private int length_;
	private int numeric;
	private int start_pos;
	private String stop_word;

	@JsonProperty("case")
	public int getCase_() {
		return case_;
	}

	public void setCase_(int case_) {
		this.case_ = case_;
	}

	@JsonProperty("length")
	public int getLength_() {
		return length_;
	}

	public void setLength_(int length_) {
		this.length_ = length_;
	}


	public int getNumeric() {
		return numeric;
	}

	public void setNumeric(int numeric) {
		this.numeric = numeric;
	}

	public int getStart_pos() {
		return start_pos;
	}

	public void setStart_pos(int start_pos) {
		this.start_pos = start_pos;
	}

	public String getStop_word() {
		return stop_word;
	}

	public void setStop_word(String stop_word) {
		this.stop_word = stop_word;
	}


	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDocuments() {
		return documents;
	}

	public void setDocuments(int documents) {
		this.documents = documents;
	}

	public int getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}
}
