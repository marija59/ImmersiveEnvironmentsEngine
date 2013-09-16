import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class GetDataSentences {
		
	public comboItem[] subjectArray;
	
	void getData(){
		try {
			  String s = "";
		      int id = -1;	
		      comboItem cItem;
			  			  			  
			  ConnectDatabase cdb = new ConnectDatabase();
		        
			  ResultSet result = cdb.st.executeQuery("SELECT idsubjects, SubjectName FROM subjects");        
			  List<comboItem> subjects = new ArrayList<comboItem>();
		      cItem = new comboItem(s, id);
		      subjects.add(cItem);
		 	  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("SubjectName");
		        id = result.getInt("idsubjects");		        
		        cItem = new comboItem(s, id);
		        subjects.add(cItem);		        
		      }	 
		 	  subjectArray = subjects.toArray( new comboItem[subjects.size()]);	 
		     
		      cdb.st.close();	
		    }
		    catch( Exception e ) {
		      e.printStackTrace();
		}
	}

}
