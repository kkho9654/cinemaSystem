import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class TheaterDataManage {
	static String theater;
	static int row;
	static int col;
	static String time;
	static String date;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static String pathTheater = "."+File.separator+"resource"+File.separator+"theater.json";

	static ArrayList<TheaterInfo> ti;
	public static JsonArray getJson(){ // json 파일 get
		try {
			Reader reader = new FileReader(pathTheater);
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonArray jsonArray = element.getAsJsonArray();


			return jsonArray;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void setTime(String i_time, String i_date)
	{
		time = i_time;
		if(time==null)
		{
			System.out.println("올바르게 등록되어있지 않습니다.");
		}
		date = i_date;
		if(time==null)
		{
			System.out.println("올바르게 등록되어있지 않습니다.");
		}
	}

	public static String[] getTheater(String date,String time) { // row, col 정보
		ArrayList<TheaterInfo> ta=getTheaterObjArr();
		String qwe=date+time;
		long dt=Long.parseLong(qwe);//현재시각 (사용자설정시각)
		ArrayList<String> tmp=new ArrayList<String>();
		int flag;
		int n=0;
		for(int i=0;i<ta.size();i++) {
			flag=-1;
			for(int j=0;j<ta.get(i).getLog().size();j++){
				LogData ld=ta.get(i).getLog().get(j);
				String asd=ld.getDate()+ld.getTime();
				long tt=Long.parseLong(asd);
				if(ld.getRow()==-1&&tt<=dt) { //현재시각보다 먼저 상영관이 삭제되어있으면 flag=1
					flag=1;
					continue;
				}
				if(tt<=dt) {
					flag=0;
				}
			}
			if(flag==0) {
				n++;
				int tmp2=0;
				ArrayList al=new ArrayList();
				for(int j=0;j<ta.get(i).getLog().size();j++){
					LogData ld=ta.get(i).getLog().get(j);
					long tt=Long.parseLong(ld.getDate()+ld.getTime());
					if(dt>=tt) al.add(tt);
				}
				Collections.sort(al,Collections.reverseOrder());
				for(int j=0;j<ta.get(i).getLog().size();j++){
					LogData ld=ta.get(i).getLog().get(j);
					long tt=Long.parseLong(ld.getDate()+ld.getTime());
					if(al.size()!=0) {
						if((long)al.get(0)==tt) {
							tmp2=j;
						}
					}
				}
				LogData as=ta.get(i).getLog().get(tmp2);
				tmp.add(ta.get(i).getName()+"/"+as.getRow()*as.getCol()+"석");
			}
		}
		String[] rt=new String[n];
		for(int i=0;i<n;i++) {
			rt[i]=tmp.get(i);
		}
		//		JsonObject jsonobject = getJson();
		//		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		//		if(theaterInfos.size()==0) return null;
		//		String[] rt=new String[theaterInfos.size()];
		//		for(int i=0;i<theaterInfos.size();i++) { //영화관 전체 크기만큼 가져오기
		//			rt[i]=((JsonObject) theaterInfos.get(i)).get("theater").toString();
		//			rt[i]=rt[i].substring(1,rt[i].length()-1);
		//			JsonArray loginfo =  (JsonArray) ((JsonObject)theaterInfos.get(i)).get("log");
		//			System.out.println(loginfo);
		//			int sss =0 ;
		//			for(int j=0;j<loginfo.size();j++) {
		//				JsonObject temp = (JsonObject) loginfo.get(j);
		//				int row=Integer.parseInt(temp.get("row").toString());
		//				int col=Integer.parseInt(temp.get("col").toString());
		//				sss = row*col;
		//			}
		//			rt[i]+=" / "+sss+"석";
		//			System.out.println(rt[i]);
		//		}
		return rt;
	}
	public static ArrayList<String[]> getTheater2(String date,String time) { // str[0]은 영화제목 str[1]은 row str[2]는 col
		ArrayList<TheaterInfo> ta=getTheaterObjArr();
		ArrayList<String[]> rt=new ArrayList<String[]>();
		
		String qwe=date+time;
		long dt=Long.parseLong(qwe);//현재시각 (사용자설정시각)
		ArrayList<String> tmp=new ArrayList<String>();
		int flag;
		int n=0;
		for(int i=0;i<ta.size();i++) {
			String[] str=new String[3];
			flag=-1;
			for(int j=0;j<ta.get(i).getLog().size();j++){
				LogData ld=ta.get(i).getLog().get(j);
				String asd=ld.getDate()+ld.getTime();
				long tt=Long.parseLong(asd);
				if(ld.getRow()==-1&&tt<=dt) { //현재시각보다 먼저 상영관이 삭제되어있으면 flag=1
					flag=1;
					continue;
				}
				if(tt<=dt) {
					flag=0;
				}
			}
			if(flag==0) {
				n++;
				int tmp2=0;
				ArrayList al=new ArrayList();
				for(int j=0;j<ta.get(i).getLog().size();j++){
					LogData ld=ta.get(i).getLog().get(j);
					long tt=Long.parseLong(ld.getDate()+ld.getTime());
					if(dt>=tt) al.add(tt);
				}
				Collections.sort(al,Collections.reverseOrder());
				for(int j=0;j<ta.get(i).getLog().size();j++){
					LogData ld=ta.get(i).getLog().get(j);
					long tt=Long.parseLong(ld.getDate()+ld.getTime());
					if(al.size()!=0) {
						if((long)al.get(0)==tt) {
							tmp2=j;
						}
					}
				}
				LogData as=ta.get(i).getLog().get(tmp2);
				str[0]=ta.get(i).getName(); str[1]=as.getRow()+""; str[2]=as.getCol()+"";
				rt.add(str);
			}
		}
		return rt;
	}
//		public static void main(String[] args) {
//			
//		}


	// 상영관 정보 ArrayList로 받아오기 
	public static ArrayList<TheaterInfo> getTheaterObjArr() { 

		JsonArray theaterInfos = getJson();
		if(theaterInfos.size()==0) return null;

		ArrayList<TheaterInfo> theaterInfoArr= new ArrayList<TheaterInfo>();

		for(int i=0;i<theaterInfos.size();i++) { // 상영관 전체 크기만큼 가져오기
			JsonObject a=(JsonObject) theaterInfos.get(i);
			String name=a.get("name").toString();
			name = Print.removeQuotes(name);

			JsonArray jsonLog =(a.get("log")).getAsJsonArray();
			ArrayList<LogData> log = new ArrayList<LogData>();

			for(int j =0; j<jsonLog.size();j++) {

				String date = ((JsonObject) jsonLog.get(j)).get("date").toString();
				String time = ((JsonObject) jsonLog.get(j)).get("time").toString();
				date = Print.removeQuotes(date);
				time = Print.removeQuotes(time);
				if(!((JsonObject) jsonLog.get(j)).get("row").toString().equals("\"del\"")) {
					int row=Integer.parseInt(((JsonObject) jsonLog.get(j)).get("row").toString());
					int col=Integer.parseInt(((JsonObject) jsonLog.get(j)).get("col").toString());
					LogData logData = new LogData(date,time,row,col);
					log.add(logData);
				}else {
					LogData logData = new LogData(date,time,-1,-1);
					log.add(logData);
				}
				
			}

			TheaterInfo theaterInfo = new TheaterInfo(name,log);
			theaterInfoArr.add(theaterInfo);
		}
		//for(TheaterInfo t: theaterInfoArr)
		//System.out.println(t);
		//System.out.println("-----------------");
		return theaterInfoArr;
	}
	public static boolean check_log_del() {
		ArrayList<TheaterInfo> ta=getTheaterObjArr();
		long dt=Long.parseLong(date+time);//현재시각 (사용자설정시각)
		ArrayList<String> tmp=new ArrayList<String>();
		int n=0;
		for(int i=0;i<ta.size();i++) {
			for(int j=0;j<ta.get(i).getLog().size();j++){
				LogData ld=ta.get(i).getLog().get(j);
				long tt=Long.parseLong(ld.getDate()+ld.getTime());
				if(ld.getRow()==-1&&ld.getCol()==-1) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean getTh(String theater) {
		//ArrayList<String> tmp = new ArrayList<String>();
		JsonArray theaterInfos = getJson();
		for(int i = 0; i<theaterInfos.size(); i++) {
			JsonObject T =(JsonObject)theaterInfos.get(i);
			String N = T.get("name").toString();
			N =  Print.removeQuotes(N);
			if(N.equals(theater))
				return true;
			// System.out.println(N);
			//tmp.add(N);
		}
		return false;
//		for(int i=0;i<tmp.size();i++) {
//			if(tmp.get(i).equals(theater)) {
//				return true;
//			}
//		}
//		return false;
	}
	public static int getTh2(String theater) {
		ArrayList<String> tmp = new ArrayList<String>();
		JsonArray theaterInfos = getJson();
		int dd=0;
		for(int i = 0; i<theaterInfos.size(); i++) {
			JsonObject T =(JsonObject)theaterInfos.get(i);
			String N = T.get("name").toString();
			N =  Print.removeQuotes(N);
			// System.out.println(N);
			tmp.add(N);
		}
		for(int i=0;i<tmp.size();i++) {
			if(tmp.get(i).equals(theater)) {
				dd=i;
				break;
			}
		}
		return dd;
	}
	public static String[] getTheaterName() { //영화관 이름만 받아옴
		ArrayList<TheaterInfo> ta=getTheaterObjArr();
		long dt=Long.parseLong(date+time);//현재시각 (사용자설정시각)
		ArrayList<String> tmp=new ArrayList<String>();
		int flag;
		int n=0;
		for(int i=0;i<ta.size();i++) {
			flag=-1;
			for(int j=0;j<ta.get(i).getLog().size();j++){
				LogData ld=ta.get(i).getLog().get(j);
				long tt=Long.parseLong(ld.getDate()+ld.getTime());
				if(ld.getRow()==-1&&tt<=dt) { //현재시각보다 먼저 상영관이 삭제되어있으면 flag=1
					flag=1;
					continue;
				}
				if(tt<=dt) {
					flag=0;
				}
			}
			if(flag==0) {
				n++;
				tmp.add(ta.get(i).getName());
			}
		}
		String[] rt=new String[n];
		for(int i=0;i<n;i++) {
			rt[i]=tmp.get(i);
		}

		//ArrayList<LogData> log=ta.get(0).getLog(); 		//수정전
		/*
		 * JsonObject jsonobject = getJson(); JsonArray theaterInfos =
		 * (JsonArray)jsonobject.get("theaters"); if(theaterInfos.size()==0) return
		 * null; String[] rt=new String[theaterInfos.size()]; for(int
		 * i=0;i<theaterInfos.size();i++) { //영화전체 크기만큼 가져오기 rt[i]=((JsonObject)
		 * theaterInfos.get(i)).get("theater").toString(); //JsonObject movieinfo
		 * =(JsonObject)movieInfos.get(i); //System.out.println(movieinfo.get("title"));
		 * }
		 */
		return rt;
	}

	public static void setJsonTheater(String theater,int row,int col,String date,String time) { //json 파일 set
		//JsonObject jsonobject= getJson(); //Json파일 전체 받아옴
		//JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		//JsonObject temp=new JsonObject();
		// getJson();
		ArrayList<TheaterInfo> temp = getTheaterObjArr();
		try {
			FileWriter writer = new FileWriter(pathTheater);
			LogData tl = new LogData(date,time,row,col);
			ArrayList<LogData> log = new ArrayList<LogData>();
			log.add(tl);
			// System.out.println(log);
			TheaterInfo th = new TheaterInfo(theater,log);
			temp.add(th);
			gson.toJson(temp,writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//	public static void main(String[] args) {
	//		setJsonTheater("호경관",3,7,"20210315","1200");
	//	}
public static String readIndexTheater2(int index) {//index해당하는 영화관 출력
		ArrayList<String[]> tmp = new ArrayList<String[]>();
		tmp = getTheater2(date, time);
		String theaterName=tmp.get(index)[0];
	
		JsonArray theaterInfos = getJson();
		//String rt=((JsonObject) theaterInfos.get(index)).get("name").toString();
		//rt =  Print.removeQuotes(rt);
		for(int i = 0; i<theaterInfos.size(); i++) {
			//System.out.println("!");
			JsonObject T =(JsonObject)theaterInfos.get(i);
			String N = T.get("name").toString();
			N =  Print.removeQuotes(N);
			if(theaterName.equals(N)) {
				//System.out.println(N);
				index=i;
			}
		}
		// System.out.println(rt);
		LogData logdataNow = findTheater(theaterName, date, time);
		
		//여기 오류
		if(logdataNow==null) {
			System.out.println("에휴");
		}
		int row = logdataNow.getRow();
		int col = logdataNow.getCol();
		theaterName+="/"+row*col+"석";
		//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
		//System.out.println(movieinfo.get("runtime"));
		return theaterName;
	}
	public static String readIndexTheater(int index) {//index해당하는 영화관 출력
		
		JsonArray theaterInfos = getJson();
		String rt=((JsonObject) theaterInfos.get(index)).get("name").toString();
		rt =  Print.removeQuotes(rt);
		// System.out.println(rt);
		LogData logdataNow = findTheater(rt, date, time);
		
		//여기 오류
		if(logdataNow==null) {
			System.out.println("에휴");
		}
		int row = logdataNow.getRow();
		int col = logdataNow.getCol();
		rt+="/"+row*col+"석";
		//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
		//System.out.println(movieinfo.get("runtime"));
		return rt;
	}

	public static String readIndexTheaterName(int index) {//index해당하는 영화관 출력
		JsonArray theaterInfos = getJson();
		String rt=((JsonObject) theaterInfos.get(index)).get("name").toString();
		
		//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
		//System.out.println(movieinfo.get("runtime"));
		return rt;
	}
	public static String readIndexTheaterName2(int index) {//index해당하는 영화관 출력
		ArrayList<String[]> tmp = new ArrayList<String[]>();
		tmp = getTheater2(date, time);
		String theaterName=tmp.get(index)[0]+"/"+ Integer.parseInt(tmp.get(index)[1])*Integer.parseInt(tmp.get(index)[2])+"석";
		return theaterName;
	}
	public static int fixIndex(int OriginalIndex) {//입력받은 index를 사용자가 선택한 상영관 이름으로 return
		int fixIndex = 0;
		ArrayList<String[]> tmp = new ArrayList<String[]>();
		tmp = getTheater2(date, time);
		System.out.println(OriginalIndex);
		String theaterName=tmp.get(OriginalIndex)[0];
	
		JsonArray theaterInfos = getJson();
		for(int i = 0; i<theaterInfos.size(); i++) {
			//System.out.println("!");
			JsonObject T =(JsonObject)theaterInfos.get(i);
			String N = T.get("name").toString();
			N =  Print.removeQuotes(N);
			if(theaterName.equals(N)) {
				//System.out.println(N);
				fixIndex=i;
			}
		}
		return fixIndex;
	}
	public static void fixTheater(int index,String newT,int row,int col) {//index받아와서 해당 영화관 수정
		try {
			//사용자로 부터 입력받은 index를 Json전체파일의 index로 바꾸자
			index = fixIndex(index);
//			ArrayList<String[]> tmp = new ArrayList<String[]>();
//			tmp = getTheater2(date, time);
//			String theaterName=tmp.get(index)[0];
//			System.out.println(theaterName);
//			
			Reader reader = new FileReader(pathTheater);
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonArray theaterInfos = element.getAsJsonArray();
//			String N = null;
//			for(int i = 0; i<theaterInfos.size(); i++) {
//				//System.out.println("!");
//				JsonObject T =(JsonObject)theaterInfos.get(i);
//				N = T.get("name").toString();
//				N =  Print.removeQuotes(N);
//				if(theaterName.equals(N)) {
////					System.out.println(N);
//					index=i;
//				}
//			}
			
			//System.out.println("?");
			//System.out.println(index);
			//System.out.println();
			JsonObject theaterinfo =(JsonObject)theaterInfos.get(index);
			String theaterName = theaterinfo.get("name").toString();
			//상영관이 수정된 날짜 확인한 다음 그 날짜에 해당되는 상영관 좌석의 행과 열 가져오기
			//String theaterName = theaterinfo.get("name").toString();
			//int _row=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("row").toString());
			//int _col=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("col").toString());
			theaterName =  Print.removeQuotes(theaterName);
			//로그인할때 입력한 날짜와 시간 가져오기
			//String dateToday = user
			//String timeToday = user
			//System.out.println(theaterName);
			
			LogData logdataNow = findTheater(theaterName, date, time);
			int _row = 0;
			int _col = 0;
			//log에 있는 값이 "del"인지 확인
			if(logdataNow==null) {
				_row = row;
				_col = col;
				//System.out.println("!!!");
			}else {
				_row = logdataNow.getRow();
				_col = logdataNow.getCol();
			}
			//System.out.println(_row);
			//System.out.println(_col);
			
			//기존의 theaterinfo의 행과 열이 입력값보다 크면 info.json에서 확인 필요
			if(_row>row || _col>col) {
				//info.json에서 확인
				System.out.println("!!!");
				if(RunningInfoManage.check_reserveInfo_for_fix(theaterName, row, col)) {
					System.out.println("상영등록정보 중 소실되는 예매좌석이 생기므로 좌석을 수정할 수 없습니다.");
					return;
				}
			}
		
			//상영관 이름이 바뀌면 info.json에 있는 모든 상영관 이름 확인해서 바꾸기
			if(!theaterName.equals(newT)) {

		        //System.out.println("요기 지나감0");

		        //System.out.println("oldName	"+theaterName);
		        //System.out.println("newName	"+newT);
		        
				RunningInfoManage.fixTheaterName(theaterName, newT);
//				List<RunningInfo> ListInfodata = RunningInfoManage.findByMovieName(N);
//				for(int i = 0; i<ListInfodata.size(); i++) {
//					
//				}
			}
			//기존거 삭제되는지 확인해야댕
			//2차기획서에 맞게 현재날짜와 함께 행 열 저장하기
			//이거아님
			//setJsonTheater(theaterName,row,col,date,time);
			//if(_row==row&&_col==col) {
			//	theaterinfo.addProperty("name", newT);
			//}
			//기존의 theaterinfos에서 상영관 이름찾아서 상영관 이름바꾸고 log추가하기
			//else {
				theaterinfo.addProperty("name", newT);
				JsonArray temp = (JsonArray)theaterinfo.get("log");
				JsonObject logObj = new JsonObject();
				logObj.addProperty("date", date);
				logObj.addProperty("time", time);
				logObj.addProperty("row", row);
				logObj.addProperty("col", col);
				//JsonArray logArr = new JsonArray();
				temp.add(logObj);
				theaterinfo.add("log", temp);
			//}

			//theaterinfo.addProperty("theater", newT);
			//theaterinfo.addProperty("row", row);
			//theaterinfo.addProperty("col", col);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(pathTheater);
				writer.write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("수정완료");
	}
	public static void RestoreTheater(String theaterName, int row, int col) {
		LogData logdataNow = findTheater(theaterName, date, time);
		int _row = logdataNow.getRow();
		int _col = logdataNow.getCol();
		int index = 0;
		ArrayList<TheaterInfo> theaterInfoArr = getTheaterObjArr();
		for(int i =0; i<theaterInfoArr.size();i++) {
			if(theaterInfoArr.get(i).getName().equals(theaterName))
				index = i;
		}
		
		try {
			Reader reader = new FileReader(pathTheater);
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonArray theaterInfos = element.getAsJsonArray();
			JsonObject theaterinfo =(JsonObject)theaterInfos.get(index);
			//String theaterName = theaterinfo.get("name").toString();
		
			JsonArray temp =(JsonArray) theaterinfo.get("log");
			JsonObject logObj = null;
			int check=0;
			JsonElement jo=null;
			for(int i=0;i<temp.size();i++) {//똑같은시간에 정보있으면 지워버림
				jo=temp.get(i);
				logObj= jo.getAsJsonObject();
				//System.out.println(logObj.get("date").toString()+":"+logObj.get("time"));
				int x=Integer.parseInt(logObj.get("date").toString().replaceAll("\"", ""));
				int y=Integer.parseInt(logObj.get("time").toString().replaceAll("\"", ""));
				//System.out.println(x+","+Integer.parseInt(time));
				if(x==Integer.parseInt(date)&&y==Integer.parseInt(time)) {
					check=1;
				}
			}
			if(check==1) {
				System.out.println("in");
				logObj.addProperty("row",row);
				logObj.addProperty("col", col);
				
			}else {
				logObj = new JsonObject();
				logObj.addProperty("date", date);
				logObj.addProperty("time", time);
				logObj.addProperty("row", row);
				logObj.addProperty("col", col);
				//JsonArray logArr = new JsonArray();
				temp.add(logObj);
				theaterinfo.add("log", temp);
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(pathTheater);
				writer.write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
			}
		}catch(IOException e) {
			}
		}
		
	public static void deleteTheater(int index,String newT) {//해당 index의 영화관 삭제
		try {
			//사용자로 부터 입력받은 index를 Json전체파일의 index로 바꾸자
			ArrayList<String[]> tmp = new ArrayList<String[]>();
			tmp = getTheater2(date, time);
			String theaterName=tmp.get(index)[0];
			
			Reader reader = new FileReader(pathTheater);
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonArray theaterInfos = element.getAsJsonArray();
			for(int i = 0; i<theaterInfos.size(); i++) {
				JsonObject T =(JsonObject)theaterInfos.get(i);
				String N = T.get("name").toString();
				N =  Print.removeQuotes(N);
				if(theaterName.equals(N)) {
//					System.out.println(N);
					index=i;
				}
			}
			JsonObject theaterinfo =(JsonObject)theaterInfos.get(index);
			
			//상영관이 수정된 날짜 확인한 다음 그 날짜에 해당되는 상영관 좌석의 행과 열 가져오기
			//String theaterName = theaterinfo.get("name").toString();
			//int _row=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("row").toString());
			//int _col=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("col").toString());
			//theaterName =  Print.removeQuotes(theaterName);
			//로그인할때 입력한 날짜와 시간 가져오기
			//String dateToday = user
			//String timeToday = user
			JsonArray temp =(JsonArray) theaterinfo.get("log");
			JsonObject logObj = null;
			int check=0;
			for(int i=0;i<temp.size();i++) {//똑같은시간에 정보있으면 지워버림
				JsonElement jo=temp.get(i);
				logObj= jo.getAsJsonObject();
				System.out.println(logObj.get("date").toString()+":"+logObj.get("time"));
				int x=Integer.parseInt(logObj.get("date").toString().replaceAll("\"", ""));
				int y=Integer.parseInt(logObj.get("time").toString().replaceAll("\"", ""));
				//System.out.println(x+","+Integer.parseInt(time));
				if(x==Integer.parseInt(date)&&y==Integer.parseInt(time)) {
					check=1;
					System.out.println("in");
					logObj.addProperty("row", "del");
					logObj.addProperty("col", "del");
				}
			}
				
			LogData logdataNow = findTheater(theaterName, date, time);
			int _row = logdataNow.getRow();
			int _col = logdataNow.getCol();
			//기존의 theaterinfo의 행과 열이 입력값보다 크면 info.json에서 확인 필요
			if(_row>0 || _col>0) {
				if(RunningInfoManage.check_reserveInfo_for_del(theaterName)) {
					System.out.println("상영등록정보 중 소실되는 예매좌석이 생기므로 좌석을 삭제할 수 없습니다.");
					return;
				}
			}
			//기존거 삭제되는지 확인해야댕
			//2차기획서에 맞게 현재날짜와 함께 행 열 저장하기
			//이거아님
			//setJsonTheater(theaterName,row,col,date,time);
			
			//기존의 theaterinfos에서 상영관 이름찾아서 상영관 이름바꾸고 log추가하기
			//theaterinfo.addProperty("name", newT);
			if(check==0) {
				logObj = new JsonObject();
				logObj.addProperty("date", date);
				logObj.addProperty("time", time);
				logObj.addProperty("row", "del");
				logObj.addProperty("col", "del");
				temp.add(logObj);
			}		
			//JsonArray logArr = new JsonArray();
			
			theaterinfo.add("log", temp);
			//theaterinfo.addProperty("name", newT);
			
			//theaterinfo.addProperty("theater", newT);
			//theaterinfo.addProperty("row", row);
			//theaterinfo.addProperty("col", col);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(pathTheater);
				writer.write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("삭제완료");
	}
	//상영정보등록할때 사용자가 입력한 날짜에 상영관이 유효한지 확인
	public static boolean checkDate(String theaterName,String inputdate, String inputtime) {
		LogData logdataNow = findTheater(theaterName, inputdate, inputtime);
		if(logdataNow==null) {
			return true;
		}else {
			if(logdataNow.getRow()<0)
				return true;
		}
		return false;
		
	} 

	// 상영관 명 + 상영시간 입력하면 TheaterInfo객체 반환하는 함수
	public static LogData findTheater(String theaterName,String dateStr, String timeStr) {
		ArrayList<TheaterInfo> theaterInfoArr = getTheaterObjArr();
		for(TheaterInfo t: theaterInfoArr) {
			// 이름 같은 상영관 찾기
			if(t.getName().equals(theaterName)){ 
				int idx =0;
				boolean isAfter = false;

				// 날짜 순으로 정렬
				t.getLog().sort(null); 

				// 날짜 비교하여 적합한 LogData 반환
				for(LogData l: t.getLog()) {	 
					isAfter = Print.isAfterDate(l.getDate(), l.getTime(), dateStr, timeStr);
					if(isAfter) {
						if(idx==0) {
							return t.getLog().get(0);
						}
						return t.getLog().get(idx-1);
					}else idx++;
				}

				// 마지막 log 날짜보다 나중에 상영하는 경우 
				return t.getLog().get(idx-1);
			}
		}
		return null; // unreachable code 
	}
//	public static void main(String args[]) {
//		LogData l = findTheater("수정관", "20210901","1200");
//		System.out.println(l.getRow()+"/"+ l.getCol());
//	} 

	public static int readTheaterNameReturnSeat(String theaterName, int count) {
		int index = 0;
		int flag = 0;
		String[] theaterNames =  getTheaterName();
		for(int i =0; i<theaterNames.length; i++) {
			if(theaterNames[i].equals(theaterName)) {
				if(count==flag) {
					index=i;
					break;
				}
				else {
					flag++;
				}
			}
		}
		JsonArray theaterInfos = getJson(); 
		LogData logdataNow = findTheater(theaterName, date, time);
		int row = logdataNow.getRow();
		int col = logdataNow.getCol();
		//		int row=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("row").toString());
		//		int col=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("col").toString());
		return row*col;
	}
}
