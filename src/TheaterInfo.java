import java.util.ArrayList;

public class TheaterInfo {//상영관정보
	private String name;
//	private int row;
//	private int col;
//	
	private ArrayList<LogData> log;
	
	public TheaterInfo (String name,ArrayList<LogData> log) {
		this.name = name;
		this.log = log;
	}
	
	public TheaterInfo () {
	}
	
	public ArrayList<LogData> getLog(){
		return this.log;
	}	
	
	
	public void setLog(ArrayList<LogData> log) {
		this.log = log;	
	}
	
	
	public String getName() {
		return name;
	}
	
	
	// for testing 
	@Override
	public String toString() {
		String str ="<" + name+">\n";
		for(LogData l : log) {
			str+=l.toString()+"\n";
		}
		return str;
	}

	

	
}
