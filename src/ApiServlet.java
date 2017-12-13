import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

/**
 * Servlet implementation class ApiServlet
 */
@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ApiServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "https://www.googleapis.com/webfonts/v1/webfonts?key=AIzaSyAPa4qDKlCdAuPry4pKeLPTItzr18d_Xoo";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer resp = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			resp.append(inputLine);
		}
		in.close();
		
		String str = null; 
		JsonParser parser = null;
		try {
			parser = new JsonParser(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		JsonObject mainObject = parser.parse(resp.toString()).getAsJsonObject();
		JsonArray items = mainObject.getAsJsonArray("items"); 

		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<title>Google Fonts API</title>\r\n" + 
				"	<link rel=\"stylesheet\" href=\"css/style.css\" />\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h1>Google Fonts</h1>\r\n"
				+ "<p>");
		for (JsonElement ff : items) {
		    JsonObject ffObject = ff.getAsJsonObject(); 
		    str = ffObject.get("family").toString(); 
		    out.println(str + "</p>");
		}	
		out.println("</body>\r\n" + 
				"</html>");
		out.close();
	}

}
