package com.sia.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Utils {

	public static boolean isQuestion(String text) {
		boolean res = false;
		String[] questionWords = {"how", "what", "when", "where", "which", "who", "why"};
	
		String[] tokens = text.split(" ");
		
		for (String token: tokens) {
			token = token.toLowerCase();
	
			for (String questionWord: questionWords) {
				if (token == questionWord) {
					res = true;
					break;
				}
			}
	
			if (res) {
				break;
			}
		}
	
		return res;
	}
	
	public static String formatHighlightedTextForAndroid(String highlightedText) {
		Document doc = Jsoup.parse(highlightedText, "", Parser.xmlParser());

		Elements elements = doc.select("span");
		elements.tagName("font");
		elements.removeAttr("style");
		elements.attr("color", "red");

		return doc.toString();
	}
}
