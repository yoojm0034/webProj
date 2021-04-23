package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empList.do")
public class EmpServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String dept = req.getParameter("dept");

		EmpDAO dao = new EmpDAO();
		List<Employee> list = null;

		if (dept == null) {
			list = dao.getEmpList();
		} else {
			list = dao.getEmpByDept(dept);
		}

		String jsonData = "[";
		// [{"empId":"?", "fName":"?", "iName":"?"}, ...]
		int cnt = 0;
		for (Employee emp : list) {
			jsonData += ("{\"empID\":\"" + emp.getEmployeeId() + "\", \"fName\":\"" + emp.getFirstName()
					+ "\", \"lName\":\"" + emp.getLastName() + "\", \"email\":\"" + emp.getEmail() + "\", \"salary\":\""
					 +emp.getSalary() + "\", \"job_id\":\"" + emp.getJobId() + "\", \"hire_date\":\"" + emp.getHireDate() + "\"}");
			if (++cnt == list.size()) {
				continue;
			}
			jsonData += ",";
		}
		jsonData += "]";
		out.println(jsonData);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String salary = req.getParameter("salary");
		String jobId = req.getParameter("job_id");
		String hireDate = req.getParameter("hire_date");
	
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setSalary(salary);
		emp.setJobId(jobId);
		emp.setHireDate(hireDate);
		EmpDAO dao = new EmpDAO();
		Employee empl = dao.insertEmpBySeq(emp);
		// {"eId":"?", "fName":"?"..}
		PrintWriter out = resp.getWriter();
		out.print("{\"employee_id\":\"" + empl.getEmployeeId() + "\"," //
				+ "\"first_name\":\"" + empl.getFirstName() + "\"," //
				+ "\"last_name\":\"" + empl.getLastName() + "\"," //
				+ "\"email\":\"" + empl.getEmail() + "\"," //
				+ "\"salary\":\"" + empl.getSalary() + "\"," //
				+ "\"job_id\":\"" + empl.getJobId() + "\"," //
				+ "\"hire_date\":\"" + empl.getHireDate() + "\"" //
				+ "}");
	}
}
