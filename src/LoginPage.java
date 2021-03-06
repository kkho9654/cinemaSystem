import java.util.Scanner;

public class LoginPage {
   static Scanner scan = new Scanner(System.in);
   static UserInfo info;
   public static UserInfo loginPage()
   {
	  String[] menuname = {"뒤로가기", "관리자용 로그인", "고객용 로그인"};
      while(true) {
         System.out.println("======로그인======");
         System.out.println("0. 뒤로가기");
         System.out.println("1. 관리자용 로그인");
         System.out.println("2. 고객용 로그인");
         System.out.print(">>>");
         
         int menu = InputRule.MenuRule(menuname);
         
         switch(menu) {
            case 0:
              return null;
            case 1:
              return adminloginPage();
            case 2:
              return clientloginPage();
            default:
            	System.out.println("올바르지 않은 입력입니다.");
            }   
      }
      
      
   }
   public static UserInfo adminloginPage()
   {
      while(true)
      {
         System.out.println("=====관리자용=====");
         System.out.print("아이디 >>");
         String id = InputRule.IDRule();
         if(id== null)            //입력규칙과 맞지않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         System.out.print("비밀번호 >>");
         String pw = InputRule.PWRule();
         if(pw== null)            //입력규칙과 맞지 않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         if(LoginDataManage.is_Admin(id, pw))
         {
        	 System.out.println("====로그인 완료====");
             info = getDate(id,pw);
             info.setIsAdmin(true);
             return info;
         }
         else 
        	System.out.println("아이디나 비밀번호를 다시 확인해주세요");
      }
   }
   
   public static UserInfo clientloginPage()
   {
      while(true)
      {
         System.out.println("=====고객용=====");
         System.out.print("아이디 >>");
         String id = InputRule.IDRule();
         if(id== null)            //입력규칙과 맞지않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         System.out.print("비밀번호 >>");
         String pw = InputRule.PWRule();
         if(pw== null)            //입력규칙과 맞지 않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         if(LoginDataManage.is_User(id, pw))
         {
        	 System.out.println("====로그인 완료====");
             info = getDate(id,pw);
             info.setIsAdmin(false);
             return info;
         }
         else
        	 System.out.println("아이디나 비밀번호를 다시 확인해주세요");
         
      }
   }
   
   public static UserInfo getDate(String id, String pw)
   {
      while(true)
      {
	      System.out.println("날짜를 입력해 주세요");
	      System.out.print(">>");
	      String date = InputRule.DateRule();
	      if (date == null)
	      {
	    	  System.out.println("올바르지 않은 입력입니다.");
	    	  continue;
	      }
	      System.out.println("시각을 입력해 주세요");
	      System.out.print(">>");
	      String time = InputRule.TimeRule();        //시각 입력규칙에 의해 처리
	      if (time == null)
	      {
	    	  System.out.println("올바르지 않은 입력입니다.");
	    	  continue;
	      }
	      else
	      {
	    	  String[] splitTime = time.split(":");
	    	  time = "";
	    	  for(int i=0; i<splitTime.length; i++)
	    	  {
	    		  time += splitTime[i];
	    	  }
	      }
	      System.out.println("====환영합니다====");
	      
	      UserInfo userinfo = new UserInfo(id, pw, date, time);
	      return userinfo;
     }
   }
}