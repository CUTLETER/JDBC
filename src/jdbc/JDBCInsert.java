package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCInsert {

	public static void main(String[] args) {
		
		/*
		 * JDBC
		 * 1. 프로그램과 DATEBASE를 연결해줌
		 * 2. 연결하려면 오라클에서 제공하는 '데이터베이스 연결 API'가 필요함
		 * 3. sqldeveloper - jdbc- lib - 파일
		 * 3-1. JDBC 프로젝트에 lib 폴더 생성 - 거기다 위 파일 드래그
		 * 4. JDBC 프로젝트 - 우 클릭 - build path - configure build path - module에 ojdbc 추가
		 * 5. 자바 11 버전 이후 꺼는 module-info.java 들어가서 jdbc 클래스 안에 requires java.sql; 추가
		 */
		
		// 1. sql 접속 정보를 선언함
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속 주소
		String uid = "HR"; //계정 ID
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
		
		
		//실행시킬 SQL 문장
		String sql = "INSERT INTO MEMBER(ID, PW, AGE, EMAIL) VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//2. JDBC 드라이버 호출
			// java.sql 패키지를 사용하는데 내부 클래스가 전부 throws로 처리 되어 있어서 try-catch구문으로 작성해야 함
			Class.forName("oracle.jdbc.OracleDriver"); // 앞은 패키지명, 뒤는 클래스명

			//3. SQL연결을 위한 Connection 객체 생성
			conn = DriverManager.getConnection(url, uid, upw); //주소, 아이디, 비밀번호
			//4. sql 쿼리구문 실행을 위한 Statement 객체 생성
			//sql의 VALUES(?, ?, ?, ?) 를 1부터 순서대로 채워줍니다. (setString(), setInt(), setDate(), setTimestamp() )
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setInt(3, age);
			pstmt.setString(4, email);
			
			//5. sql실행 - insert, update, delete는 executeUpdate()로 실행
			int result = pstmt.executeUpdate(); // 적용된 row행의 개수를 반환함
			
			if(result == 1) {
				System.out.println("인서트 성공");
				
			} else {
				System.out.println("인서트 실패");
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
