package spring;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *	유저 관련 Access Object  
 * @author cheeeeze
 *
 */

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	public Member selectByEmail( String email ) {
		
		final String query = "select * from member where email=?";
		
		List<Member> result = jdbcTemplate.query(
					query, (ResultSet rs, int rowNum ) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				}, email);
		
		return result.isEmpty() ? null : result.get(0);
	}
	
	public void insert( Member member ) {
		
	}
	
	public void update( Member member ) {
		
	}
	
	public Collection<Member> selectAll() {
		return null;
	}
}
