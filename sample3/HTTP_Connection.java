package sample3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;

public class HTTP_Connection {

		public static void main(String[] args) throws Exception {

			HTTP_Connection http = new HTTP_Connection();

			System.out.println("Testing 1 - Send Http GET request");
			http.sendGet();
			
			BufferedReader br = new BufferedReader(new FileReader("/home/anil/Downloads/intern-sample/cuisines.txt"));
			Gson obj = new Gson();
			
			Example c= obj.fromJson(br, Example.class);
			System.out.println(c.getCuisines().get(0).getCuisine().getId());
			http.match(c);
			
		}
		void match(Example c)
		{
			String Name;
			Scanner in=new Scanner(System.in);
			Name=in.next();
			HashMap <String,Integer> hashmap=new HashMap<>();
			System.out.println(c.cuisines.size());
			for(int i=0;i<c.cuisines.size();i++)
			{
			 hashmap.put(c.getCuisines().get(i).getCuisine().getName(), c.getCuisines().get(i).getCuisine().getId());
		}
			System.out.println(hashmap.get(Name));
		}
		private void sendGet() throws Exception {

			String url = "https://developers.zomato.com/api/v2.1/cuisines?city_id=4";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
	        //con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("user-key", "442861c540487705cee55f9b804af1cd");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close(); 
			String st=response.toString();
			BufferedWriter writer=new BufferedWriter(new FileWriter("/home/anil/Downloads/intern-sample/cuisines.txt"));
		    writer.write(st);
		    writer.close();
		    //System.out.println(response.toString());
			
		}

}

