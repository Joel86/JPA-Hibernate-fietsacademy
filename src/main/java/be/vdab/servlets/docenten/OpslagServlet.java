package be.vdab.servlets.docenten;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.exceptions.RecordAangepastException;
import be.vdab.services.DocentService;
import be.vdab.util.StringUtils;

/**
 * Servlet implementation class OpslagServlet
 */
@WebServlet("/docenten/opslag.htm")
public class OpslagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/opslag.jsp";
	private static final String REDIRECT_URL = "%s/docenten/zoeken.htm?id=%d";
	private final transient DocentService docentService = new DocentService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> fouten = new HashMap<>();
		String percentageString = request.getParameter("percentage");
		BigDecimal percentage = null;
		if(StringUtils.isBigDecimal(percentageString)) {
			percentage = new BigDecimal(percentageString);
			if(percentage.compareTo(BigDecimal.ZERO) <= 0) {
				fouten.put("percentage", "tik een positief getal");
			}
		} else {
			fouten.put("percentage", "tik een positief getal");
		}
		if(fouten.isEmpty()) {
			long id = Long.parseLong(request.getParameter("id"));
			try {
				docentService.opslag(id, percentage);
				response.sendRedirect(response.encodeRedirectURL(
						String.format(REDIRECT_URL, request.getContextPath(), id)));
			} catch(RecordAangepastException ex) {
				fouten.put("percentage", "een andere gebruiker heeft deze docent gewijzigd");
			}
		}
		if(!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
