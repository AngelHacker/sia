/*package com.sia.Scraper;

import java.io.IOException;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;

@Path("wikihowscraper")
public class WikihowScraper {

	String baseUrl = "http://www.wikihow.com/";

	@GET
	public String getData(@QueryParam("title") String title) {

		if (title == null )
			return "title="+title+" userId=";

		Document doc;
		try {
			String url = baseUrl + title;
			doc = Jsoup.connect(url).get();
			//doc = Jsoup.connect("http://www.wikihow.com/Alleviate-Tailbone-Pain").get();
			
			 * Elements links = doc.select("a[href]");
			 * System.out.println("\nLinks: (%d)"+ links.size()); for (Element
			 * link : links) {
			 * System.out.println("\n* a:"+link.attr("abs:href")+
			 * link.text().trim()); }
			 
			String listHeader = "";
			Elements itemProps = doc.getElementsByAttribute("itemprop");
			System.out.println("\nLinks: (%d)" + itemProps.size());
			for (Element itemProp : itemProps) {
				if (itemProp.attr("itemprop").equals("url")) {
					listHeader = itemProp.text().trim();
					break;
				}
				// System.out.println("\n* a:"+itemProp.attr("itemprop")+itemProp.text().trim());

			}

			if (listHeader.equals("")) {
				return "Invalid header";
			}

			Elements headers = doc.getElementsByClass("whb");

			if (headers != null && headers.size() > 0) {
				createInputStructure(headers, userId, listHeader, url, token);
			}
			System.out.println("\n headers: (%d)" + headers.size());
			for (Element header : headers) {
				System.out.println("\n* header:" + header.text().trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "success";

	}

	private void createInputStructure(Elements headers, String userId,
			String listHeader, String url, String token) {

		System.out.println("Parameters for createInputStructure is userId:"
				+ userId + " ListHEader:" + listHeader + " url:" + url);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("isNew", "true");
		request.put("shared", "true");
		request.put("header", listHeader);
		Random rand = new Random();
		String listId = "";
		try {
			listId = userId + "#" + listHeader.replace(" ", "").substring(4, 9)
					+ "#" + Calendar.getInstance().getTimeInMillis() + "#"
					+ rand.nextInt(50);
		} catch (Exception e) {
			listId = userId + "#" + Calendar.getInstance().getTimeInMillis()
					+ "#" + rand.nextInt(50);
		}

		request.put("listId", listId);

		Map<String, String> backLinkMap = new HashMap<String, String>();
		backLinkMap.put("text", url);
		request.put("backLink", backLinkMap);

		List<Map<String, String>> items = new ArrayList<Map<String, String>>();

		for (Element header : headers) {
			Map<String, String> contentMap = new HashMap<String, String>();
			String content = header.text();
			String itemId = "";
			contentMap.put("content", content);
			contentMap.put("isNew", "true");
			
			 * Request will fail in ModifyListITem as there won't be any itemId.
			 * Modify ModifyListItem API to neglect absense of itemIds for these data
			 * try {
				itemId = listId + "#"
						+ content.replace(" ", "").substring(0, 5) + "#"
						+ Calendar.getInstance().getTimeInMillis() + "#"
						+ rand.nextInt(50);
			} catch (Exception e) {
				itemId = listId + "#"
						+ Calendar.getInstance().getTimeInMillis() + "#"
						+ rand.nextInt(50);
			}

			contentMap.put("id", itemId);
			//ToDo: Modify ModifyListItem API to neglect this 
			//contentMap.put("isChecked", "false");
			items.add(contentMap);

		}
		request.put("items", items);
		String parsedRequest=gson.toJson(request);
		System.out.println("Request is " + parsedRequest);
		
	}

}
*/