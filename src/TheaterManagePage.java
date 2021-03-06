import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TheaterManagePage {//8.2.2상영관관리페이지

	static Scanner scan = new Scanner(System.in);

	public static void theaterManagePage(UserInfo userinfo) {
		TheaterDataManage.setTime(userinfo.getTime(), userinfo.getDate());
		while(true) {
			String[] theaters=TheaterDataManage.getTheater(userinfo.getDate(), userinfo.getTime());
			System.out.println("======상영관 목록======");
			for(int i=0;i<theaters.length;i++) {
				System.out.println(theaters[i]);
			}
			System.out.println("===================");
			String[] tmp={"메인 페이지로가기","상영관 등록","상영관 수정 및 삭제"};
			Print.menu(tmp,true);
			System.out.print(">>>");
			int menuNum=InputRule.MenuRule(tmp);
			switch(menuNum) {
			case 0:
				return;
			case 1:
				theaterRegisterPage(userinfo.getDate(),userinfo.getTime());
				break;
			case 2:
				theaterCheckPage(userinfo.getDate(),userinfo.getTime());
				break;
			default:
				System.out.println("올바르지 않은 입력입니다.");
			}
		}
	}
	public static void theaterRegisterPage(String date, String time) {//8.2.2.1 상영관정보등록'
		while(true) {
			String theater="",rc="";
			int check=0;
			System.out.print("상영관 이름>>");
			theater=InputRule.ScreenRule();
			String theaters[] = TheaterDataManage.getTheaterName();
			// System.out.println("getTheaterName출력==============");
			for(int i=0;i<theaters.length;i++) {
				if(theaters[i].equals(theater)) {
					check=1;
				}
			}
			if(theater==null) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(check==1){ //안에 row, col가 모두 del인지 확인//&&!TheaterDataManage.check_log_del()
				// row col 모두 del이면 없는 상영관이니까 추가 가능하도록 하게 하기
				System.out.println("이미 존재하는 이름입니다.");
			}
			else {
				while(true) {
					System.out.print("좌석의 행과 열 수>>");
					rc=scan.nextLine();
					rc=rc.replace(" ","");
					int row=0,col=0;
					try {
						String[] tmp=rc.split("-|/");
						row=Integer.parseInt(tmp[0]);
						col=Integer.parseInt(tmp[1]);
					}catch(Exception e) {
						System.out.println("올바르지 않은 입력입니다.");
						continue;
					}
					if(row<1||row>26||col<1||col>50) {
						System.out.println("올바르지 않은 입력입니다.");
					}
					else {
						if(TheaterDataManage.getTh(theater)) {
							//int temp = TheaterDataManage.getTh2(theater);
							TheaterDataManage.RestoreTheater(theater, row, col);
							return;
						}
						else {
							TheaterDataManage.setJsonTheater(theater,row,col,date,time);
							System.out.println("=====등록완료=====");
							return;
						}
					}
				}	
			}
		}
	}
	public static void theaterCheckPage(String date,String time) {//8.2.2.2 상영관정보확인
		while(true) {
			String[] theaters=TheaterDataManage.getTheater(date,time);
			String[] theatersName=TheaterDataManage.getTheaterName();
			String[] theatermenu=new String[theatersName.length+1];
			theatermenu[0]="뒤로가기";
			for(int i=1;i<theaters.length+1;i++) {
				theatermenu[i]=theatersName[i-1].replaceAll("\"", "");
			}
			System.out.println("======상영관 목록======");
			System.out.println("0. 돌아가기");
			for(int i=0;i<theaters.length;i++)
				System.out.println((i+1)+". "+theaters[i]);
			System.out.println("===================");
			System.out.print("수정 및 삭제할 상영관을 선택하세요>>>");
			int menuNum=InputRule.MenuRule(theatermenu);
			
			while(menuNum==-1) {
				System.out.println("올바르지 않은 입력입니다.");
				System.out.print("수정 및 삭제할 상영관을 선택하세요>>>");
				menuNum=InputRule.MenuRule(theatermenu);
			}
			if(menuNum==0) break;
			else {
				theaterFixPage(menuNum-1);//정상입력시
			}
		}
	}
	
	
	public static void theaterFixPage(int index) {//8.2.2.2.1 상영관정보수정및삭제
		while(true) {
			String theater="",rc="";
			System.out.println("======상영관 수정 및 삭제======");
			System.out.println(TheaterDataManage.readIndexTheaterName2(index));
			System.out.println("0. 뒤로가기");
			System.out.println("1. 수정");
			System.out.println("2. 삭제");
			System.out.print(">>>");
			String[] tmp2={"뒤로가기","수정","삭제"};
			int menuNum=InputRule.MenuRule(tmp2);
			if(menuNum==-1) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			if(menuNum==0) {
				break;
			}
			else if(menuNum==1){
				while(true) {
					System.out.println("======상영관 수정======"); 
					System.out.print("상영관 이름>>>");
					theater=InputRule.ScreenRule();
					
					int check=0;
					String theaters[] = TheaterDataManage.getTheaterName();
					for(int i=0;i<theaters.length;i++) {
						if(theaters[i].equals(theater)) {
							check=1;
							break;
						}
					}
					if(TheaterDataManage.readIndexTheater2(index).replaceAll("\"","").split("/")[0].equals(theater)) {
						check=0;
					}
					if(theater==null) {
						System.out.println("올바르지 않은 입력입니다.");
					}
					else if(check==1) {
						System.out.println("이미 존재하는 이름입니다.");
					}
					else {
						System.out.print("좌석의 행과 열 수>>");
						rc=scan.nextLine();
						rc=rc.replace(" ","");
						int row=0,col=0;
						try {
							String[] tmp=rc.split("-|/");
							row=Integer.parseInt(tmp[0]);
							col=Integer.parseInt(tmp[1]);
						}catch(Exception e) {
							System.out.println("올바르지 않은 입력입니다.");
						}
						
						if(row<1||row>26||col<1||col>50) {
							System.out.println("올바르지 않은 입력입니다.");
							continue;
						}
						else {
							TheaterDataManage.fixTheater(index, theater, row, col);
							return;
						}
					}
					return;
				}
			}
			else if(menuNum==2) {
				if(RunningInfoManage.check_reserveInfo_for_delete(theater)) {
					TheaterDataManage.deleteTheater(index,theater);
					return;
				}
				else {
					System.out.println("예매한 내역이 있으므로 삭제할 수 없습니다.");
					return;
				}
			}
		}
	}
}
