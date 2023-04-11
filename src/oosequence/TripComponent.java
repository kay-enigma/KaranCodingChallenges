package oosequence;

import java.util.Date;

public class TripComponent {

	private Date start_Time;
	private Date end_Time;

	
	public TripComponent(Date gvn_dep, Date gvn_Arr) {
		
		if (gvn_dep == null || gvn_Arr == null) {
			
			start_Time = gvn_dep;
			end_Time = gvn_Arr;   
		}
		
		if(gvn_dep != null && gvn_Arr != null) {
			
			if(gvn_dep.after(gvn_Arr)||gvn_dep.equals(gvn_Arr)) {
				
				start_Time = gvn_dep;
				end_Time = null;
			}
			else if(gvn_dep.before(gvn_Arr)){
				
				start_Time = gvn_dep;
				end_Time = gvn_Arr;
			}
		}
	}
	
	public TripComponent() {
		Date temp_Flight = new Date();
		start_Time = temp_Flight;
		end_Time=new Date(temp_Flight.getTime() + 3600000);
		
		
	}

	public TripComponent(TripComponent C) {
		
		start_Time=C.start_Time;
		end_Time=C.end_Time;
	}

	public String getStart() {
		
		if(start_Time==null)return "";
		if(start_Time!=null)return start_Time.toString();
		return "";
	}
	public String getEnd() {
		
		if(end_Time==null) {
			return "";
		}
		if(end_Time!=null) {
			return end_Time.toString();
		}
		return "";
	}
	public void setStart(Date date_1) {	
		
		if(date_1 == null || end_Time == null) {
			start_Time = date_1;
		}

		else if (date_1.before(end_Time)) {
			start_Time = date_1;
		}
	}
	public void setEnd(Date date_2) { 
		
		if(date_2 == null || start_Time ==null)end_Time = date_2;
		
		else if (start_Time.before(date_2))end_Time = date_2;
	}

	protected long lengthInSeconds() {
		long diff_Time = 0;
		if( start_Time == null  ||  end_Time == null ) {
			return diff_Time;
		}
		if( start_Time != null  &  end_Time != null ) {
			long dep_Time = start_Time.getTime();
			long arr_Time = end_Time.getTime();
			return diff_Time = (arr_Time-dep_Time)/1000;
		}
		return diff_Time;			
	}
	public Boolean isBefore(TripComponent M) {
		
		if ( this.end_Time.getTime() < M.start_Time.getTime() ) {	
			return true;
		}
		return false;
		}
	
	public Boolean overlapsWith(TripComponent M) {
		if( !(start_Time==null) && !(end_Time==null) && !(M.start_Time==null) && !(M.end_Time==null)) {
		return !( this.isBefore(M));
		}
		//if(end.after(m.start)||m.start.after(start)&&m.end.before(end)||start.before(m.start)&&end.after(m.end))return true;
			
			//else if(start||m.start.after(end)&&m.end.after(end))return false;
		
		return false;
	}
}