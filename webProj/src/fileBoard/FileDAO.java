package fileBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBcon;

public class FileDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public void delFile(int num) {
		conn = DBcon.getConnect();
		String sql = "delete from file_board where num = ? ";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			int r = psmt.executeUpdate();
			
			System.out.println(r + "건 삭제");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
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
	
	public FileVO getFile(int num) { //num값으로 1건 조회
		conn = DBcon.getConnect();
		String sql = "select num, author, title, filename, to_char(day, 'yyyy-mm-dd') as day from file_board where num = ?";
		FileVO file = new FileVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if(rs.next()) {
				file.setNum(rs.getInt("num"));
				file.setTitle(rs.getString("title"));
				file.setAuthor(rs.getString("author"));
				file.setFileName(rs.getString("filename"));
				file.setDay(rs.getString("day"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
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
		
		return file;
	}

	public List<FileVO> getFileList() {
		conn = DBcon.getConnect();
		List<FileVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select num, author, title, filename, to_char(day, 'yyyy-mm-dd') as day\r\n"
					+ "from file_board order by num");
			rs = psmt.executeQuery();
			while (rs.next()) {
				FileVO vo = new FileVO();
				vo.setNum(rs.getInt("num"));
				vo.setAuthor(rs.getString("author"));
				vo.setTitle(rs.getString("title"));
				vo.setFileName(rs.getString("filename"));
				vo.setDay(rs.getString("day"));

				list.add(vo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
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

	public FileVO getInsertKeyVal(FileVO vo) {
		conn = DBcon.getConnect();
		// 신규번호, 한건입력, 한건조회
		String selectKey = "select nvl(max(num),0)+1 from file_board";
		String insertSql = "insert into file_board values (?,?,?,?,to_char(sysdate, 'yyyy-mm-dd'))";
		String selectSql = "select num, author, title, filename, to_char(day, 'yyyy-mm-dd') as day from file_board where num = ?";
		FileVO file = new FileVO();
		int key = 0;
		try {
			psmt = conn.prepareStatement(selectKey);
			rs = psmt.executeQuery();
			if (rs.next()) {
				key = rs.getInt(1);
			}
			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, key);
			psmt.setString(2, vo.getAuthor());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getFileName());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력");

			psmt = conn.prepareStatement(selectSql);
			psmt.setInt(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setFileName(rs.getString("filename"));
				file.setNum(rs.getInt("num"));
				file.setTitle(rs.getString("title"));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
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

		return file;
	}
	
	public boolean upadateFile(FileVO vo) {
		conn = DBcon.getConnect();
		int modifyCnt = 0;
		String sql = "update file_board set author=? , title=?, filename=? where num=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAuthor());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getFileName());
			psmt.setInt(4, vo.getNum());
			modifyCnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
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
		
		return modifyCnt == 0 ? false : true;
	}
	
	
}
