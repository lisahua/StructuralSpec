package codeSkeleton.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codeSkeleton.core.main.MainEntrance;

@SuppressWarnings("serial")
@WebServlet("/userQuery")
public class UserQueryServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String query = req.getParameter("query");
		MainEntrance.run(query);
//		String[] topics = MainEntrance.getIRTopics(7);
//		String[] files = MainEntrance.getIRFiles(0, 2);
//		req.setAttribute("topics", topics);
//		req.setAttribute("code", files);
		req.setAttribute("code", MainEntrance.getStructuralCode());
//		req.setAttribute("code", MainEntrance.getIRTopics(0));
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
