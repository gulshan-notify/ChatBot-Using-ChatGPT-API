package practice.demo.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
	@RequestMapping("/")
	public String page() {
		return "page";
	}
	

	public static String chatGPT(String prompt) {
		String url = "https://api.openai.com/v1/chat/completions";
		String apiKey = "YOUR OWN API KEY";
		String model = "gpt-3.5-turbo";

		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + apiKey);
			connection.setRequestProperty("Content-Type", "application/json");

			// The request body
			String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt
					+ "\"}]}";
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(body);
			writer.flush();
			writer.close();

			// Response from ChatGPT
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			StringBuffer response = new StringBuffer();

			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			// calls the method to extract the message.
			return extractMessageFromJSONResponse(response.toString());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String extractMessageFromJSONResponse(String response) {
		int start = response.indexOf("content") + 11;

		int end = response.indexOf("\"", start);

		return response.substring(start, end);

	}

	@PostMapping("/bot")
	@ResponseBody
	public String main(@RequestBody Map<String,String> str) {

		String string = str.get("message");
		if(string.equalsIgnoreCase("abe nikl")) {
			return "abe tu nikal";
		}else if(string.equalsIgnoreCase("tu kutta")) {
			return "Tera Baap Kutta";
		}else if(string.equalsIgnoreCase("pagal")){
			return "Yaha aa mt jaio dubara";
		}else if(string.equalsIgnoreCase("")) {
			return "Bhai tu rhne de!";
		}else if(string.equalsIgnoreCase("monu")) {
			return "MONSTER 👹 ";
		}else if(string.equalsIgnoreCase("yogesh")) {
			return "mitar hei ke... ";
		}else if(string.equalsIgnoreCase("gaba")) {
			return "Bakch##d";
		}else if(string.equalsIgnoreCase("sankar")) {
			return "SAKA ☠️";
		}else if(string.equalsIgnoreCase("gupta")) {
			return "mirchi 💀";
		}
		
		String chatGPT = chatGPT(string);
		return chatGPT;
	}
	
	
	@PostMapping("/check")
	@ResponseBody
	public String practice(@RequestBody String val1) {
		return "true";
	}

}
