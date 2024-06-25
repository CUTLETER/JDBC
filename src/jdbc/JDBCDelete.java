package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCDelete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 아이디를 입력 받아서 아이디에 해당하는 데이터를 삭제하는 jdbc 코드를 작성하기
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속 주소
		String uid = "HR"; //계정 ID
		String upw = "HR"; //비밀번호
		
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제할 아이디>");
		String id = scan.next();
	
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");

			
			conn = DriverManager.getConnection(url, uid, upw); 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("Delete 성공");
				
			} else {
				System.out.println("Delete 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
			}
			
		}
		

	}

}
