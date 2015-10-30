package codeSkeleton.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structualSpec.main.MainEntrance;

@SuppressWarnings("serial")
@WebServlet("/userQuery")
public class UserQueryServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String query = req.getParameter("query");
		MainEntrance.run(query);
		String[] topics = MainEntrance.getTopics(7);
		String[] files = MainEntrance.getFiles(0, 2);
		req.setAttribute("topics", topics);
		req.setAttribute("code", files);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
