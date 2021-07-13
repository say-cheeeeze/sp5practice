package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


public class DBQuery {

	private DataSource ds;
	
	public DBQuery( DataSource ds ) {
		this.ds = ds;
		// dataSource 객체를 주입한다.
	}
	
	public int count() {
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection(); // 풀에서 커넥션을 구한다.
			
			try( Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
				rs.next();
				return rs.getInt(1);
			} 
		} catch( SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if( conn != null ) {
				try {
					conn.close(); // 풀에 반환
				} catch ( SQLException e) {
					
				}
			}
		}
	}
}
