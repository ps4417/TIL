package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 오라클 데이터 베이스에 연결하고 select, insert, update, delete 작업을 실행해주는 클래스입니다.
public class MemberDAO {
	
	
	//오라클에 접속하는 소스를 작성
		String id = "system";
		String pass = "111111";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";  // 접속 url
		
		Connection con; // 데이터베이스에 접근할 수 있도록 설정
		PreparedStatement pstmt; // 데이터 베이스에서 쿼리를 실행시켜주는 객체
		ResultSet rs; // 데이터 베이스의 테이블의 결과를 리턴받아 자바에 저장해주는 객체
		
			
	// 데이터 베이스에 접근할 수 있도록 도와주는 메소드
	public void getCon() {
		
		try {
			// 1. 해당 데이터 베이스를 사용한다고 선언한다.(클래스를 등록 = 오라클용을 사용)
			Class.forName("oracle.jdbc.driver.OracleDriver");
						
			// 2. 해당 데이터 베이스에 접속한다.
			con = DriverManager.getConnection(url,id,pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 데이터 베이스에 한 사람의 회원 정보를 저장해주는 메소드
	public void insertMember(MemberBean mbean) {
		try{
			getCon();
			
			// 3-1. 접속 후 쿼리준비
			String sql = "INSERT INTO MEMBER(ID,PASS1,EMAIL,TEL,HOBBY,JOB,AGE,INFO) VALUES(?,?,?,?,?,?,?,?)";
			
			// 3-2. 쿼리를 사용하도록 설정
			PreparedStatement pstmt = con.prepareStatement(sql); // jsp에서 쿼리를 사용하도록 설정
			// 3-3. ?에 맞게 데이터 매핑
			pstmt.setString(1,mbean.getId());
			pstmt.setString(2,mbean.getPass1());
			pstmt.setString(3,mbean.getEmail());
			pstmt.setString(4,mbean.getTel());
			pstmt.setString(5,mbean.getHobby());
			pstmt.setString(6,mbean.getJob());
			pstmt.setString(7,mbean.getAge());
			pstmt.setString(8,mbean.getInfo());
			
			// 4. 오라클에서 쿼리를 실행하시오
			pstmt.executeUpdate(); // insert, update, delete 시 사용하는 메소드   // 결과를 받아올 땐 executeQuery(); 사용
			
			// 5. 자원 반납
			con.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
