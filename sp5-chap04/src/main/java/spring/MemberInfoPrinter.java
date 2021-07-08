package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void printMemberInfo( String email ) {
		Member member = memberDao.selectByEmail(email);
		if ( member == null ) {
			System.out.println(" 데이터가 없습니다. ");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}
	
	// 메소드에도 autowired 어노테이션을 붙일 수 있다.
	
	@Autowired
	public void setMemberDao( MemberDao memberDao ) {
		this.memberDao = memberDao;
	}
	
	@Autowired
	public void setPrinter( MemberPrinter printer ) {
		this.printer = printer;
	}
}
