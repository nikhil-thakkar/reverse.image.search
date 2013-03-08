package webapp.dotnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DotNet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//  str = "http://" + this.server + "/processWordJSON?lang=hindi&inString=" + input;

		HttpURLConnection conn = (HttpURLConnection) new URL("http://search.twitter.com/search.atom?q=").openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);		
		StringBuilder sb = new StringBuilder();
		conn.setRequestProperty("method","POST");
		InputStream in = conn.getInputStream();
		int c;
		while ((c = in.read()) != -1) sb.append((char) c);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()), 1);
		
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			
			sb.append(line);
		}
		System.out.println(sb.toString());
		
		
		
	}
	

}
