package com.board.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.transform.Templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private JdbcTemplate templates;
	String sql;
	
	@Override //list를 부러온ㄴ 메서드
	public List<BoardDTO> getBoardList() {
		// TODO Auto-generated method stub
		List<BoardDTO> list = null;
		sql="select * from board order by board_no desc";
		
		return list = templates.query(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				
				return dto;
			}
		});
	}//getBoardList() end;

	@Override // 작성한 폼을 DB에 보내는 메서드
	public int insertBoard(final BoardDTO dto) {
		
		sql="insert into board values((select max(board_no) from board),?,?,?,?,?,sysdate)";
		return templates.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, dto.getBoard_writer());
				ps.setString(2, dto.getBoard_title());
				ps.setString(3, dto.getBoard_cont());
				ps.setString(4, dto.getBoard_pwd());
				ps.setInt(5, 0);
			}
		});
	}

	@Override	//상세보기 메서드
	public BoardDTO boardCont(int no) {
		// TODO Auto-generated method stub
		final BoardDTO dto = new BoardDTO();
		sql ="select * from board where board_no="+no;
		return templates.queryForObject(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				
				return dto;
			}
		});
	}//boardCont() end;

	@Override	//조회수를 올려주는 메서드
	public void readCount(int hit,int no) {
		// TODO Auto-generated method stub
		sql="update board set board_hit="+hit+" where board_no="+no;
		templates.update(sql);
		
	}//readCount()end;

	@Override //글을 수정하는 메서드
	public int updateBoard(final BoardDTO dto) {
		// TODO Auto-generated method stub
		sql="update board set board_title=?, board_cont=? where board_no=?";
		
		return templates.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, dto.getBoard_title());
				ps.setString(2, dto.getBoard_cont());
				ps.setInt(3, dto.getBoard_no());
				
			}
		});
	}//updateBoard() end;

	@Override //글을 삭제하는 메서드
	public int deleteBoard(int no) {
		// TODO Auto-generated method stub
		
		sql="delete from board where board_no="+no;
		
		int res = templates.update(sql);
		System.out.println("BoardDAOImpl -deleteBoard() : 삭제성공하면 반환되는값 ??" +res);
		return res;
	}//deleteBoard() end;

	@Override// 검색하는 메서드
	public List<BoardDTO> searchList(@RequestParam("search1") String s1,@RequestParam("search2") String s2) {
		List<BoardDTO> list = null;
		
		
		  if(s1.equals("title+cont")) {
		  sql="select * from board where board_title like '%"+s2+"%' or board_cont like '%"+s2+"%'"; }
		  else if(s1.equals("writer")) {
		  sql="select * from board where board_writer like '%"+s2+"%'"; }
		 
		
		
		
		list = templates.query(sql, new RowMapper<BoardDTO>() {
			
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				
				return dto;
			}
		});
		
		return list;
	}
	
	

}
