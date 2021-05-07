package fileBoard;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/fileModifyServlet")
public class FileModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileModifyServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletContext sc = this.getServletContext();
		String path = sc.getRealPath("upload"); // 서버상 경로

		MultipartRequest multi = new MultipartRequest(request, // 요청정보
				path, // 저장위치
				8 * 1024 * 1024, // 최대용량
				"UTF-8", // 인코딩
				new DefaultFileRenamePolicy());
		Enumeration en = multi.getFileNames(); // 올라간 파일을 읽기위해
		String author = multi.getParameter("author");
		String title = multi.getParameter("title");
		String num = multi.getParameter("num");
		String fileN = null;
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String fileName = multi.getFilesystemName(name);
			fileN = fileName;
			System.out.println("name: " + name + ", fileName:" + fileN);
		}
		// 입력 후 저장된 값 가져오기.
		FileDAO dao = new FileDAO();
		FileVO vo = new FileVO();
		vo.setAuthor(author);
		vo.setFileName(fileN);
		vo.setTitle(title);
		vo.setNum(Integer.parseInt(num));
		// {"retCode":"Success"}
		JSONObject obj = new JSONObject();
		if(dao.upadateFile(vo)) {
			obj.put("retCode", "Success");
		}else {
			obj.put("retCode", "Fail");
		};
		
		
		
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(obj);
	}
	

}
