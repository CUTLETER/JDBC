package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCSelect2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 직원 테이블과 부서 테이블을 left조인하고
		 * 조건 : 직원 아이디 입력 받아서, 해당 아이디의 직원 아이디, 직무 아이디, 부서명, 이름 출력하기
		 */
		
		
		// 내꺼 오류
		
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속 주소
		String uid = "HR"; //계정 ID
		String upw = "HR"; //비밀번호
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("조회할 직원의 ID>");
		String id = scan.next();
		
		String sql = "SELECT E.EMPLOYEE_ID, E.JOB_ID, D.DEPARTMENT_NAME, CONCAT(E.FIRST_NAME||' ', E.LAST_NAME) AS NAME FROM EMPLOYEES E LEFT JOIN DEPARTMENTS D ON E.EMPLOYEE_ID = D.EMPLOYEE_ID  WHERE E.EMPLOYEE_ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int eid = rs.getInt("employee_id");
				String jid = rs.getString("job_id");
				String dname = rs.getString("department_name");
				String pname = rs.getString("name");
				
				System.out.println(eid);
				System.out.println(jid);
				System.out.println(dname);
				System.out.println(pname);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
	}

}
