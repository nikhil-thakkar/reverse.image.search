package webapp.dotnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
public class Search extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		String q= req.getParameter("q");
		String rs;
		System.out.println(q);
		if(q==null||q.trim().equals("")) /*q.equals(" ")||q.equals("  ")||q.equals("   ")||q.equals("     "))*/
			out.println("Search field cantain nothing to search..!<br>");
		else
		{
			try{
			HttpURLConnection conn = (HttpURLConnection) new URL("http://search.twitter.com/search.atom?q="+q).openConnection();	
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
			rs=xpars(sb.toString());
			out.println(rs);
			out.flush();
			out.close();
			}catch (IOException e) {
				out.println("unable to access the twitter, Either the link is block by your IT Administrator..!");
				e.getMessage();
			}
		}			
	}
	///////  Method to parse the xml... start ////
	public static String xpars(String xml) {
		/*Calendar cal = Calendar.getInstance();
		Date date = new Date();
		*/
		System.out.println(xml);
		String res[]=new String[15];
		String tem="";
					
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();
		
			NodeList nList = doc.getElementsByTagName("entry");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
				{
					tem=tem+"<div \"style=\"background-color:lightblue; width: 740px;\"><table><tr><td width=\"48px;\"> <img alt=\"tweet.png\" src=\"";
					Node nNode = nList.item(temp);
			
					if (nNode.getNodeType() == Node.ELEMENT_NODE)
					{
						Element eElement = (Element) nNode;
						NodeList nNode1 = eElement.getElementsByTagName("link");
						String im = nNode1.item(1).getAttributes().item(0).toString();
						im = im.substring(6,(im.length()-1));
						tem=tem+im+"\"></td><td><a href=\""+getTagValue("uri", eElement)+"\">"+getTagValue("name", eElement)+"</a></td></tr><tr><td colspan=\"2\"><html:html>"+getTagValue("content", eElement)+"</html:html></td></tr>";
						tem=tem+"</table></div><br>";
						res[temp]=tem;
						
	/*/////     testing   Date time start /////////
						String dt = getTagValue("published", eElement);
						String dt1= dt.substring(0,4);
						dt1=dt1+"/"+dt.substring(5,7);
						dt1=dt1+"/"+dt.substring(8,10);
						dt1=dt1+" "+dt.substring(11,13);
						dt1=dt1+":"+dt.substring(14,16);
						dt1=dt1+":"+dt.substring(17,19);
												
						//System.out.println("after parse "+date.parse(dt1));
						//date.setTime(date.getTime());
						SimpleDateFormat sdf =new SimpleDateFormat("yyy/mm/dd hh:mm:ss");
						System.out.println("afetr comp "+date.compareTo(sdf.parse(dt1)));
						System.out.println("cal date "+sdf.parse(dt1));
						
						DateTime start = new DateTime(sdf.parse(dt1));
						DateTime end = new DateTime(date);
						//Interval interval = new Interval(start, end);

						
						Date old=new Date();
						old = Date.parse(dt1);
						Date newDate = new Date();
						newDate =date;
						//DateTime newDate=new DateTime(date);
						System.out.println("Old date is "+start);
						System.out.println("Current date is "+end);
						//Interval interval = new Interval(old, newDate);
						
						//System.out.println("duration "+interval.toString());
						
						//System.out.println("after change "+sdf.parse(dt1));
						//System.out.println("current time "+cal);
						format(date)
						
						date.compareTo(date.parse(dt1));
						
						//System.out.println(date.compareTo(date.parse(dt1)));
					
						//System.out.println(date.getTime());
						/////     testing   Date time end  /////////  
*/						

					}
				}
			
			if(tem!="")
			return tem;
			else
				return tem="Nothing to display";
		}catch (Exception e)
			{
			e.printStackTrace();		
			return "Nothing to display";		
			}
						
		}
	
	private static String getTagValue(String sTag, Element eElement)
	{
	    NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0); 
	    return nValue.getNodeValue();  

	}

	//////// Parse method end...//////////

}
