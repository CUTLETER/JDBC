package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 나이가 10 이상인 데이터 조회하기
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속 주소
		String uid = "HR"; //계정 ID
		String upw = "HR"; //비밀번호
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("조회할 나이>");
		int age = scan.nextInt();
		
		String sql = "SELECT * FROM MEMBER WHERE AGE >= ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 					차이점 : Select는 결과를 조회해서 처리할 ResultSet 객체가 필요함!
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw); // conn 객체 생성
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체 생성
			
			pstmt.setInt(1, age); // WHERE AGE >= ?의 ?값 채우기
			
			// rs에 조회된 값을 저장시킴
			rs = pstmt.executeQuery(); // 조회를 해서 값을 가져와야 하기 때문에 Select는 executeQuery() 구문으로 실행함
			
			// rs에 저장된 데이터를 1행씩 처리하는 구문
			while (rs.next()) { // ResultSet의 주요 메소드 중 하나, 다음 행이 있으면 전진하고 true 반환함
				// 1행에 대한 프로그램 처리
				// ResultSet의 주요 메소드인 getString(), getInt(), getDouble(), getTimestamp() 등을 써서 데이터를 가져옴
				String id = rs.getString("id"); // get(조회할 컬럼명), 대소문자 상관없음
				String pw = rs.getString("pw");
				int ages = rs.getInt("age");
				String email = rs.getString("email");
				
				System.out.println(id);
				System.out.println(pw);
				System.out.println(ages);
				System.out.println(email);
				System.out.println("------------------------------------------------------");
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		

	}

}
