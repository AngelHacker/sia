package com.sia.havenondemand;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HavenOnDemandService {
	@GET("/1/api/sync/analyzesentiment/v1")
	Call<AnalyzeSentimentResult> analyzeSentiment(@Query("text") String text, @Query("apikey") String apikey);

	@GET("/1/api/sync/tokenizetext/v1")
	Call<TokenizeTextResult> tokenizeText(@Query("text") String text, @Query("stemming") boolean stemming, @Query("apikey") String apikey);

	@GET("/1/api/sync/autocomplete/v1")
	Call<AutoCompleteResult> autoComplete(@Query("text") String text, @Query("apikey") String apikey);

	@GET("/1/api/sync/highlighttext/v1")
	Call<HighlightTextResult> highlightText(@Query("text") String text, @Query("highlight_expression") String highlightExpression, @Query("apikey") String apikey);
}
