package fileBoard;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/getFileServlet")
public class GetFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFileServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		int fileNum = Integer.parseInt(num);
		
		FileDAO dao = new FileDAO();
		FileVO vo = dao.getFile(fileNum);
		
		JSONObject obj = new JSONObject();
		obj.put("num", vo.getNum());
		obj.put("title", vo.getTitle());
		obj.put("author", vo.getAuthor());
		obj.put("filename", vo.getFileName());
		obj.put("day", vo.getDay());
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(obj);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String num = request.getParameter("num");
		int fileNum = Integer.parseInt(num);
		String fname = request.getParameter("filename");
		
		
		FileDAO dao = new FileDAO();
		dao.delFile(fileNum);
		
		String path = "D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webProj\\upload\\" + fname;
		File file = new File(path);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
		}else {
			System.out.println("존재하지않는파일입니다.");
		}
	}	

}
