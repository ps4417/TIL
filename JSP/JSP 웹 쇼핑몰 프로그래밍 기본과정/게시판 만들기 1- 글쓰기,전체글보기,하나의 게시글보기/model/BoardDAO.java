package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//데이터 베이스의 커넥션 풀을 사용하도록 설정하는 메소드
	public void getCon() {
		
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			// datasource
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 하나의 새로운 게시글이 넘어와서 저장되는 메소드
	public void insertBoard(BoardBean bean) {
		
		getCon();
		//빈 클래스에 넘어오지 않았던 데이터들을 초기화 해주어야 합니다.
		int ref=0; // 글 그룹을 의미 = 쿼리를 실행시켜서 가장 큰 ref 값을 가져온 후 +1을 더해주면 된다.
		int re_step =1; // 새 글이기에  = 부모글이기에
		int re_level = 1;
		
		try {
			//가장 큰 ref값을 읽어오는 쿼리 준비
			String refsql = "SELECT MAX(REF) FROM BOARD";
			//쿼리 실행 객체
			pstmt = con.prepareStatement(refsql);
			//쿼리 실행 후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) { // 결과 값이 있다면
				ref = rs.getInt(1)+1; // 최대값에 +1을 더해서 글 그룹을 설정
			}
			// 실제로 게시글 전체값을 테이블에 저장
			String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			// ?에 값을 매핑
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			//쿼리를 실행하시오
			pstmt.executeUpdate();
			// 자원반납
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 모든 게시글을 리턴해주는 메서드 작성
	public ArrayList<BoardBean> getAllBoard(){
		// 리턴할 객체 선언
		ArrayList<BoardBean> arr = new ArrayList<>();
		
		getCon();
		try {
			// 쿼리 준비
			String sql = "SELECT * FROM BOARD ORDER BY REF DESC , RE_STEP ASC ";
			// 쿼리를 실행할 객체 선언
			pstmt = con.prepareStatement(sql);
			// 쿼리 실행 후 결과 저장
			rs = pstmt.executeQuery();
			// 데이터 개수가 몇개인지 모르기에 반복문을 이용하여 데이터를 추출
			while(rs.next()) {
				// 데이터를 패키징(가방 = BoardBean 클래스를 이용해줌)
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				// 패키징한 데이터를 ArrayList에 저장
				arr.add(bean);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;
			
	}
	
	//하나의 게시글을 리턴하는 메소드
	public BoardBean getOneBoard(int num) {
		
		//리턴타입 선언
		BoardBean bean = new BoardBean();
		getCon();
		
		
		try {
			// 조회수 증가 쿼리
			String readsql = "UPDATE BOARD SET READCOUNT = READCOUNT+1 WHERE NUM=?";
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
					
			
			// 쿼리 준비
			String sql = "SELECT * FROM BOARD WHERE NUM=?";
			// 쿼리 실행 객체 선언
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 쿼리 실행 후 결과 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
