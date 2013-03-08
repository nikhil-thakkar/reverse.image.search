package webapp.control;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import webapp.util.Utility;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class ServiceAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaActionForm requestForm = (DynaActionForm) form;
		response.setContentType("text/html");
		PrintWriter outs = response.getWriter();
		
		FormFile file1=null;
		FileOutputStream fosout = null;
		InputStream input=null;
		file1 = (FormFile) requestForm.get("file1");
		System.out.println("Url Hit...");
			
		String fn=Utility.PROPERTIES.getProperty("file.path");
		
		if(file1!=null){
			System.out.println("file name is :"+file1.getFileName());
			System.out.println("CONTENT TYPE IS "+file1.getContentType());
			String tempfolder =Utility.PROPERTIES.getProperty("file.path");
			File tempfolders = new File(tempfolder);
			if(!tempfolders.exists()){
				System.out.println(tempfolders.mkdir());
			}
			String fn1=tempfolder+file1.getFileName();
			File tempfn = new File(fn1);
			if(tempfn.isFile()){
				int i=0;
			do {
				i = i + 1;
				fn = tempfolder+i+"_"+file1.getFileName();
				tempfn = new File(fn);
			} while (tempfn.isFile());
			}
			
			if (file1 != null && file1.getFileSize() > 0) {
				fosout = new FileOutputStream(fn1);
				fosout.write(file1.getFileData());
				fosout.flush();
				fosout.close();
			}else{
				outs.write("file not found in the request..!");
				return null;
			}
			
			outs.write(tempfn.toString());
			return null;
		}
			outs.write("Invalid request..!");
			return null;

}
		
		//URL url = new URL("");
		//URLConnection uc = url.openConnection();

//		InputStreamReader input = new InputStreamReader(uc.getInputStream());
	/*	input = new DataInputStream(request.getInputStream());
		
		
		
		//BufferedInputStream in = new BufferedInputStream(input);
		fn=fn+request.getHeader("Content-Filename");
		byte [] ba =new byte[1024];
		int len=-1;
	    fosout=new FileOutputStream(fn);
	    
		while((len=input.read())!=-1)
		{
			//input.read(ba, 0, ba.length);
			fosout.write(len);
				
		}
		
		fosout.flush();
		fosout.close();
		//StringBuilder sb = new StringBuilder();
		//String inputLine;
		input.read(cbuf)
		if(in){
			fn=fn+request.getHeader("Content-Filename");
			System.out.println("File is : "+request.getHeader("Content-Filename").toString());
			fn = fn.replaceAll("%20"," ");
			FileWriter fw = new FileWriter(fn);
		
		while ((inputLine = in.readLine()) != null) {
			sb.append(in.readLine());
			//sb.append(inputLine);
			//System.out.println("Content is :"+inputLine+" :END");
		}
		fw.write(sb.toString());
		fw.close();
		System.out.println("file upload successfully...!");
		outs.write("File Upload Succefully..!");
		//in.close();
		}else{
			outs.write("Invalid Request..!");
		}
		}catch (Exception e) {
			System.out.println("error : ");
			e.printStackTrace();
			
		}finally
		{
			input.close();
			fosout.close();
		}
	*/	
		
	
	}
