package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
	Connection conn;
	ResultSet rs;
	
	PreparedStatement psmt;

	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	
	public Employee insertEmpBySeq(Employee emp) {
		conn = DBcon.getConnect();

		Employee empl = new Employee();

		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp (employee_id, first_name, last_name, email, hire_date, job_id, salary, department_id) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, 50)";
		try {
			int empId = 0;
			psmt = conn.prepareStatement(sql1);
			rs = psmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);
			}
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, emp.getJobId());
			psmt.setString(7, emp.getSalary());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");
			// 입력한 값 반환해주기 위해서
			empl.setEmployeeId(empId);
			empl.setEmail(emp.getEmail());
			empl.setFirstName(emp.getFirstName());
			empl.setLastName(emp.getLastName());
			empl.setHireDate(emp.getHireDate());
			empl.setJobId(emp.getJobId());
			empl.setSalary(emp.getSalary());

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return empl;// 여기서 시작

	}

	public void insertEmp(Employee emp) {
		String sql = "insert into emp_temp (employee_id, last_name, email, hire_date, job_id) VALUES "
				+ "((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?)";
		conn = DBcon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getJobId());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public List<Employee> getEmpByDept(String dept) {
		String sql = "select * from emp_temp where department_id = " + dept + "order by employee_id";
		conn = DBcon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getString("salary"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));

				employees.add(emp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return employees;
	}

	public List<Employee> getEmpList() {
		// 사원정보를 가지고 오는 처리

		String sql = "select * from emp_temp order by employee_id";
		conn = DBcon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getString("salary"));

				employees.add(emp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return employees;
	}

	// empl_demo
	public List<Employee> getEmployeeList() {
		// 사원정보를 가지고 오는 처리

		String sql = "select * from empl_demo order by employee_id";
		conn = DBcon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getString("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setPhoneNumber(rs.getString("phone_number"));

				employees.add(emp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return employees;
	}

	public Map<String, Integer> getEmployeeByDept() {
		// 부서명, 사원수
		Map<String, Integer> map = new HashMap<>();

		String sql = "select d.department_name, count(1)\r\n" + "from empl_demo e , departments d\r\n"
				+ "where e.department_id = d.department_id\r\n" + "group by d.department_name";
		conn = DBcon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}
		return map;
	}

	// 스케줄 정보를 가지고 오는 메소드;
	public List<ScheduleVO> getScheduleList() {
		conn = DBcon.getConnect();
		String sql = "select * from schedule";
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ScheduleVO vo = new ScheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDay(rs.getString("start_Day"));
				vo.setEndDay(rs.getString("end_Day"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 스케줄 테이블에 한건 입력
	public void insertSchedule(ScheduleVO vo) {
		conn = DBcon.getConnect();
		String sql = "insert into schedule values (?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDay());
			psmt.setString(3, vo.getEndDay());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}

	}
	public void deleteSchedule(ScheduleVO vo) {
		conn = DBcon.getConnect();
		String sql = "delete from schedule where title = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close();
		}
		
		
				
		
	}

}
