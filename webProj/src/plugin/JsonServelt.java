package plugin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/jsonServelt")
public class JsonServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JsonServelt() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				JSONObject outObj = new JSONObject();
				JSONArray fAry = new JSONArray();
				fAry.add("Reading");
				fAry.add("Sleeping");
				outObj.put("name", "hong");
				outObj.put("hobby", fAry);
				response.getWriter().print(outObj);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
