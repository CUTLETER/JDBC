package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCUpdate {

	public static void main(String[] args) {
		
		//아이디, 비밀번호, 나이, 이메일을 받아서, 해당 아이디를 업데이트
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속 주소
		String uid = "HR"; //계정이름	
		String upw = "HR"; //비밀번호
		
		Scanner scan = new Scanner(System.in);
		System.out.println("아이디>");
		String id = scan.next();
		System.out.println("비밀번호>");
		String pw = scan.next();
		System.out.println("나이>");
		int age = scan.nextInt();
		System.out.println("이메일>");
		String email = scan.next();
		
		
		//sql
		String sql = "UPDATE MEMBER SET AGE = ?, PW = ?, EMAIL = ? WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver"); //드라이버 클래스 호출
			
			conn = DriverManager.getConnection(url, uid, upw); //conn 객체 생성
			
			pstmt = conn.prepareStatement(sql); //pstmt 객체 생성
			pstmt.setInt(1, age ); //정수형
			pstmt.setString(2, pw );
			pstmt.setString(3, email );
			pstmt.setString(4, id );
			
			int result = pstmt.executeUpdate(); //insert, update, delete
						
			if(result == 1) {
				System.out.println("업데이트 성공");
			} else {
				System.out.println("업데이트 실패");
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
