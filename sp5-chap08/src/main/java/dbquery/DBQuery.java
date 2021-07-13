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
			conn = ds.getConnection(); 	// 풀에서 커넥션을 구한다. 이 때 풀에서 커넥션을 가져온다.
										// 이 시점에서 커넥션 conn 은 활성 상태이다.
			
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
					conn.close(); 	// 커넥션을 종료하면 실제 커넥션을 끊지 않고 풀에 반환한다. 종료되지 않는다.
									// 풀에 반환된 커넥션을 다시 유휴상태(idle)가 된다.
				} catch ( SQLException e) {
					
				}
			}
		}
	}
}
