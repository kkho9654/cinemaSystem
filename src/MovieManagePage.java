import java.util.Scanner;

public class MovieManagePage {//8.2.1영화관리페이지

	static Scanner scan = new Scanner(System.in);

	public static void movieManagePage() {
		while(true) {
			System.out.println("======영화목록======");
			//등록된 영화 읽어오기 (DataManage클래스 내 함수 호출)
			System.out.println("오징어게임 /90분");
			System.out.println("DP /100분");
			System.out.println("=================");
			System.out.println("0. 뒤로가기");
			System.out.println("1. 영화 정보 등록");
			System.out.println("2. 영화 정보 수정 및 삭제");
			System.out.print(">>>");

			String[] tmp={"뒤로가기","영화정보등록","영화정보수정및삭제"};
			int menuNum=InputRule.MenuRule(tmp);
			switch(menuNum) {
			case 0:
				return;
			case 1:
				movieRegisterPage();
				break;
			case 2:
				movieCheckPage();
				break;
			default:
            	System.out.println("올바르지 않은 입력입니다.");
			}
		}
	}
	public static void movieRegisterPage() {//8.2.1.1 영화정보등록
		String tmp="";
		int time=0;
		System.out.print("영화제목>>");
		tmp=InputRule.MTRule();
		System.out.print("상영시간>>");
		time=InputRule.RunTimeRule();
		
		System.out.println("=====등록완료=====");
	}
	public static void movieCheckPage() {//8.2.1.2 영화정보확인
		System.out.println("======영화목록======");
		System.out.println("0. 뒤로가기");
		//등록된 영화 읽어오기 (DataManage클래스 내 함수 호출)
		System.out.println("1. 오징어게임 /90분");
		System.out.println("2. DP /100분");
		System.out.println("=================");
		System.out.print("수정 및 삭제할 영화를 선택하세요>>>");
		
		String[] tmp={"뒤로가기","오징어게임","DP"};
		int menuNum=InputRule.MenuRule(tmp);
		if(menuNum==0) return;//8.2.1로
		
		//정상입력시
		movieFixPage();
	}
	public static void movieFixPage() {//8.2.1.2.1 영화정보수정및삭제
		String tmp="";
		System.out.println("======영화 수정 및 삭제======");
		System.out.println("오징어게임 /90분");
		System.out.println("0. 뒤로가기");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.print(">>>");
		String[] tmp2={"뒤로가기","수정","삭제"};
		int menuNum=InputRule.MenuRule(tmp2);
		if(menuNum==0) return;
		System.out.println("======영화수정======"); //or 영화삭제
		
		int time=0;
		System.out.print("영화제목>>");
		tmp=InputRule.MTRule();
		System.out.print("상영시간>>");
		time=InputRule.RunTimeRule();
	}
}
