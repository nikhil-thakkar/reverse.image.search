package webapp.control;
import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class DownloadFile extends Action{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("APPLICATION/OCTET-STREAM");
		
		PrintWriter out =response.getWriter();
		//String s= "vidyai";
		String s= request.getContextPath()+File.separator+"abc";
		
		
		File f = new File(s);
		if(f.isDirectory()){
			System.out.println("directory found.. "+s);
			String fileList[] = f.list();
			for(String file:fileList){
				System.out.println(file);
				out.println("file name is : "+file);
			}
		}else{
			System.out.println("directory not found "+s+f.mkdir());	
		}
		out.println("hi here is output...!");
	return null;
		//return super.execute(mapping, form, request, response);
	}
	
}
