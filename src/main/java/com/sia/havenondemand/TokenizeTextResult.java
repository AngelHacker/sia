package com.sia.havenondemand;

import java.util.List;

public class TokenizeTextResult {
	
	public TokenizeTextResult() {
		
	}

	public TokenizeTextResult(List<Term> terms) {
		this.terms = terms;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	private List<Term> terms;
}
