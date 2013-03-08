package webapp.control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import webapp.model.User;

public class Login extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dna = (DynaActionForm) form;
		//response.setContentType("Object");
		
		PrintWriter out = response.getWriter();

		String userName=null;
		userName =dna.getString("userName");
		String password= null;
		password= dna.getString("password");
		if(userName.toString().trim().equals(password.toString().trim())){
			/*User user = new User();
			user.setFirstName(userName);
			user.setMiddleName("Middle Name..");
			user.setLaststName("Last Name..!");
			user.setCourse("Computer Science..!");
			user.setCollage("Intelliswift College of Development..!");
			*/
			out.write("true");
		}else{
			out.write("false");
		}
		return null;
		//return super.execute(mapping, form, request, response);
	}
	
	
	

}
