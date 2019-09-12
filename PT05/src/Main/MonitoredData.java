package Main;

import java.util.Date;

public class MonitoredData {
	
	private Date start_time;
	private Date end_time;
	private String activity;
	
	public MonitoredData(Date start_time, Date end_time, String activity)
	{
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity = activity;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
}
