package org.funnypinky.boerse.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.funnypinky.boerse.db.DBController;
import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;

public class collectData {

	private static boolean debug = true;
	
	private static final String apiKey = "LLZ3REBN0QSPCV76";
	
	//private String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey="+apiKey;

	public static Map<String, String> getSearchResult(String pattern){
		String url = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords="+pattern+"&apikey="+apiKey;
		System.out.println(url);
		InputStream is;
		HashMap<String, String> returnValue = new HashMap<>();
		JSONArray value = new JSONArray();
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject jo = new JSONObject(jsonText);
			value = jo.getJSONArray("bestMatches");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler beim abholen der Daten!");
			alert.setContentText(e.getLocalizedMessage());
			alert.showAndWait();
			if(debug) {
				JSONObject jo = new JSONObject(testSearch);
				value = jo.getJSONArray("bestMatches");
			}
		}  //disable for develop
		value.forEach(item -> {
			returnValue.put(((JSONObject) item).get("1. symbol").toString(),((JSONObject) item).get("2. name").toString());
		});
		return returnValue;
	}
	
	void collectData() {
		try {
			DBController dbc = DBController.getInstance();
			dbc.initDBConnection();
			//InputStream is = new URL(url).openStream();  //disable for develop
			//BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			BufferedReader rd = new BufferedReader(new FileReader(new File("T:\\ALLGEMEI\\Haesler\\workspace\\Boerse\\test.txt")));
			String jsonText = readAll(rd);
			JSONObject jo = new JSONObject(jsonText);
			JSONObject value = jo.getJSONObject("Monthly Adjusted Time Series");
			Iterator<String> iter = value.keys();
			
			while(iter.hasNext()) {
				System.out.println(iter.next());
			}
			
			//is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}
	
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	private static String testSearch = "{\r\n" + 
			"    \"bestMatches\": [\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BA\",\r\n" + 
			"            \"2. name\": \"The Boeing Company\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"1.0000\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BAC\",\r\n" + 
			"            \"2. name\": \"Bank of America Corporation\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.8000\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BABA\",\r\n" + 
			"            \"2. name\": \"Alibaba Group Holding Limited\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.6667\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"GOLD\",\r\n" + 
			"            \"2. name\": \"Barrick Gold Corporation\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.5714\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BIDU\",\r\n" + 
			"            \"2. name\": \"Baidu Inc.\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.5000\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BAYRY\",\r\n" + 
			"            \"2. name\": \"Bayer Aktiengesellschaft\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.4000\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BLDP\",\r\n" + 
			"            \"2. name\": \"Ballard Power Systems Inc.\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.3333\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BHC\",\r\n" + 
			"            \"2. name\": \"Bausch Health Companies Inc.\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.3333\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"1. symbol\": \"BK\",\r\n" + 
			"            \"2. name\": \"The Bank of New York Mellon Corporation\",\r\n" + 
			"            \"3. type\": \"Equity\",\r\n" + 
			"            \"4. region\": \"United States\",\r\n" + 
			"            \"5. marketOpen\": \"09:30\",\r\n" + 
			"            \"6. marketClose\": \"16:00\",\r\n" + 
			"            \"7. timezone\": \"UTC-05\",\r\n" + 
			"            \"8. currency\": \"USD\",\r\n" + 
			"            \"9. matchScore\": \"0.1538\"\r\n" + 
			"        }\r\n" + 
			"    ]\r\n" + 
			"}";
}
