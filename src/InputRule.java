import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//
public class InputRule {//입력규칙 정의 (static으로)
	static Scanner sc = new Scanner(System.in);
	public static String MenuRule()
	   {
	      String menu = sc.nextLine();
	      return null;
	   }
	   public static String IDRule()
	   {
	      Pattern pattern = Pattern.compile("^[A-Za-z[0-9]]{2,10}$");
	      String id;
	      id = sc.nextLine();
	      Matcher matcher = pattern.matcher(id);
	      if(!matcher.find())
	      {
	         return null;
	      }
	      return id;
	   }
	   
	   public static String PWRule()
	   {   
	      Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9]).{8,}$");
	      String pw;
	      pw = sc.nextLine();
	      Matcher matcher = pattern.matcher(pw);
	      if(!matcher.find())
	      {
	         return null;
	      }
	      return pw;
	   }
	
	public static String MTRule() { //7.3영화제목입력규칙
		String movie;
		while(true) {
			movie = sc.nextLine();
			movie=movie.trim();
			if(movie.equals("0")) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(movie.length()<1 || movie.length()>30) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else {
				break;
			}
		}
		return movie;
	}
	public static int TimeRule(){ //7.4시간입력규칙
		char tmp;
		int r_answer;
		while(true) {
			Boolean a_check=true;
			String answer="";
			String time=sc.nextLine();
			int q_count=0;
			r_answer=0;
			String check_time = time.replaceAll(" ","");
			//#있는지 체크 필요
			if(time.contains("#")) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else {
				if(!time.equals(check_time)) { //공백이 있는 경우
					System.out.println("올바르지 않은 입력입니다.");
					a_check=false;
				}
				else {
					Boolean check=true;
					for(int i=0;i<time.length();i++) {
						tmp=time.charAt(i);
						if('0'<=tmp&&tmp<='9') {
							if(check)
								answer+=tmp;
							else {
								answer+='#';
								q_count+=1;
								answer+=tmp;
								check=true;
							}
						}
						else {
							if(check) {
								answer+='#';
								q_count+=1;
								answer+=tmp;
								check=false;
							}
							else {
								answer+=tmp;
							}
						}
					}
					if(q_count==0) { //숫자만 있는 경우
						r_answer=Integer.parseInt(answer);
					}
					else if(q_count==1) { //숫자+문자
						String[] splited=answer.split("#");
						if(splited[1].equals("시간")||splited[1].equals("h")) {
							r_answer=Integer.parseInt(splited[0])*60;
						}
						else if(splited[1].equals("분")||splited[1].equals("m")){
							r_answer = Integer.parseInt(splited[0]);
						}
						else {
							System.out.println("올바르지 않은 입력입니다.");
							a_check=false;
						}
					}
					else if(q_count==3) { //숫자+문자+숫자+문자
						String[] splited=answer.split("#");
						if(splited[1].equals("h") || splited[1].equals(":") || splited[1].equals("-") || splited[1].equals("/")) {
							if(splited[3].equals("분") || splited[3].equals("m")) {
								r_answer=Integer.parseInt(splited[0])*60+Integer.parseInt(splited[2]);
							}
							else {
								System.out.println("올바르지 않은 입력입니다.");
								a_check=false;
							}
						}
						else {
							System.out.println("올바르지 않은 입력입니다.");
							a_check=false;
						}
					}
					else {
						System.out.println("올바르지 않은 입력입니다.");
						a_check=false;
					}
				}
				if(r_answer>=10&&r_answer<=720&&a_check) {
					break;
				}
				else if(a_check) {
					System.out.println("올바르지 않은 입력입니다.");
				}
			}
		}
		return r_answer; 
	}
	public static String ScreenRule() {//7.5 상영관입력규칙
		String screen;
		while(true) {
			screen=sc.nextLine();
			String check_screen = screen.trim();
			if(!screen.equals(check_screen)) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(screen.length()<1 || screen.length()>100) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(screen.charAt(screen.length()-1)!='관'){
				System.out.println("올바르지 않은 입력입니다.");
			}
			// 입력으로 '관' 만 입력하는 경우 --->(기획서 반영?)
			else {
				break;
			}
		}
		return screen;
	}

	public static String DateRule() {// 7.6 날짜입력규칙 _ return 값은 YYYYMMDD형식
		String checkdate = sc.nextLine();
		boolean isNumeric = checkdate.chars().allMatch(Character::isDigit);
		if (isNumeric) {// 문법규칙(1)의 경우
			if (checkdate.length() == 8) {
				//매우 올바른형식임_아래 의미규칙만 확인
			} else if (checkdate.length() == 6) {
				String s_year = checkdate.substring(0, 2);
				if(s_year.startsWith("0"))
					s_year =  s_year.substring(1,2);
				int year = Integer.parseInt(s_year);
				if (year >= 50 && year <= 99) {
					checkdate = "19" + checkdate;
				}
				else {
					checkdate = "20" + checkdate;
				}
			} else {
				return null; // 문법형식에 맞지 않음
			}
		} // 문법규칙(2)의 경우
		else if (checkdate.contains("-") || checkdate.contains("/")) {
			checkdate = checkdate.replace("/", "-"); // 구분기호 "-로 통일
			int count = 0;
			for(int i =0; i<checkdate.length(); i++)
				if(checkdate.charAt(i)=='-')
					count++;
			if(count!=2)	return null;
			String date[] = checkdate.split("-");
			if (date[0].length() == 2) {
				if(date[0].startsWith("0"))
					date[0] =  date[0].substring(1,2);
				int year = Integer.parseInt(date[0]);
				if (year >= 50 && year <= 99)
					year = 1900 + year;
				else
					year = 2000 + year;
				date[0] = Integer.toString(year);
			}
			if (date[1].length() == 1)
				date[1] = "0" + date[1];
			if (date[2].length() == 1)
				date[2] = "0" + date[2];
			checkdate = "";
			for (int i = 0; i < date.length; i++)
				checkdate += date[i];
		} else {
			return null; // 위의 조건에 만족하지 않는 경우 없음
		}
		//return checkdate; // YYYYMMDD형식
		
		//의미규칙 확인 유효한 날짜인지
		String s_year = checkdate.substring(0, 4);
		String s_month = checkdate.substring(4,6);
		if(s_month.startsWith("0"))
			s_month = checkdate.substring(5,6);
		String s_day = checkdate.substring(6, 8);
		if(s_day.startsWith("0"))
			s_day = checkdate.substring(7,8);
		int year = Integer.parseInt(s_year);
		int month = Integer.parseInt(s_month);
		int day = Integer.parseInt(s_day);
//		System.out.println(s_year);
//		System.out.println(s_month);
//		System.out.println(s_day);
		boolean isLeapYear = false;
		if((year / 4 == 0 && year / 100 != 0) || year / 400 == 0)
			isLeapYear = true;
		if(month>=1 && month <= 12) {
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(day >= 1 && day <= 31)
					return checkdate;
				else
					return null;
			}else if(month == 4 || month == 6 || month == 9 || month == 11) {
				if(day >= 1 && day <= 30)
					return checkdate;
				else
					return null;
			}else if(month==2) {
				if(isLeapYear) {
					if(day >= 1 && day <= 29)
						return checkdate;
					else
						return null;
				}else {
					if(day >= 1 && day <= 28)
						return checkdate;
					else
						return null;
				}
			}else
				return null;
		}else
			return null;
	}
	public static String Time2Rule() {// 7.7시각입력규칙 _ return 값은 HH:MM형식
		String checktime = sc.nextLine();
		boolean isNumeric = checktime.chars().allMatch(Character::isDigit);
		if(isNumeric && checktime.length()==4) {
			//매우 올바른형식임_아래 의미규칙만 확인
		}else if(checktime.contains("-") || checktime.contains(":")) {
			checktime = checktime.replace("-", ":"); // 구분기호 ":"로 통일
			int count = 0;
			for(int i =0; i<checktime.length(); i++)
				if(checktime.charAt(i)==':')
					count++;
			if(count!=1)	return null;
			String time[] = checktime.split(":");
			if (time[0].length() == 1) {
				time[0] = "0"+time[0];
			}
			if (time[1].length() == 1) {
				time[1] = "0"+time[1];
			}
			checktime = "";
			for (int i = 0; i < time.length; i++)
				checktime += time[i];
		} else {
			return null;
		}
		checktime = checktime.substring(0, 2)+":"+checktime.substring(2, 4);
		//return null;
		//의미규칙확인
		String s_hour = checktime.substring(0, 2);
		String s_min = checktime.substring(3,5);
		if(s_hour.startsWith("0"))
			s_hour =  s_hour.substring(1,2);
		int hour = Integer.parseInt(s_hour);
		if(s_min.startsWith("0"))
			s_min =  s_min.substring(1,2);
		int min = Integer.parseInt(s_min);
		if(hour>=0 && hour<24 && min>=0 && min < 60) {
			return checktime;
		}else
			return null;
	}
	
	/* 정윤 - 인원수 정상 입력 : 인원수 반환 / 0 입력 : 0반환 / 잘못된 입력 -1 반환 */ 
	public static int rsrvPplInput() { // 7.8 예매인원 입력 규칙 
		String pplStr;
		int pplNum;
		
		pplStr = sc.nextLine();
		pplStr=pplStr.trim(); // 앞뒤 공백제거 
		
		// 0 입력한 경우 
		if(pplStr.equals("0"))
			return 0;  
		
		// 0을 제외한 입력 
		int lastIdx = pplStr.length()-1;
		if(pplStr.charAt(lastIdx)=='명' || pplStr.charAt(lastIdx)=='인') {
			pplStr= pplStr.substring(0, lastIdx); // 명 or 인을 제외한 문자열로 재할당 
		}
		
		pplStr=pplStr.trim(); // 앞뒤 공백제거 
		 
		try {
			pplNum = Integer.parseInt(pplStr);
		} catch (NumberFormatException e) {
			return -1;
		}
		
		return pplNum;
	}
	
	public static String SeatRule() { //7.9 예매 좌석 입력 규칙
        String seat = sc.nextLine();
        seat = seat.replace(" ", "");
        seat = seat.trim();
        seat = seat.toLowerCase();
        int ascii = seat.charAt(0);
        if(ascii >= 97 && ascii <= 122) {
            if(seat.length() > 3 || seat.length() == 1) {
                return null;
            }
            else if(seat.length() == 3) {
                int a = seat.charAt(1) - '0';
                int b = seat.charAt(2) - '0';
                int num = a * 10 + b;
                if(num >= 1 && num <= 50) return seat;
                else return null;
            } else {
                int num = seat.charAt(1) - '0';
                if(num >= 1 && num <= 9) return seat;
                else return null;
            }
        } else {
            return null;
        }
    }
	public static int YesOrNo() { // 7.10 yes / no 입력규칙
        String yon = sc.next();
        yon = yon.toLowerCase();
        if(yon.equals("yes") || yon.equals("y")) return 1; //yes or y --> 1
        else if(yon.equals("no") || yon.equals("n")) return 0; //no or n --> 0
        else return -1; //others --> -1
    }
	
}
