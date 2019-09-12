package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static final String path= "C:\\Users\\Alex\\eclipse-workspace\\PT05\\src\\Main\\Activities.txt";

	public static void main(String[] args) throws IOException {
		
		ArrayList<MonitoredData> dataList = new ArrayList<MonitoredData>();
		

		
		//separating start_time,end_time and activity and creating a list of MonitoredData type objects
		try{
			
			Stream<String> filestream = Files.lines(Paths.get(path));
			filestream.forEach(s->{
				Date startDate, endDate;
				String activity;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] StringDivide = s.split("\t\t");
				try {
					startDate = dateFormat.parse(StringDivide[0]);
					endDate = dateFormat.parse(StringDivide[1]);
					activity = StringDivide[2];//.replaceAll("\\s", "");
					dataList.add(new MonitoredData(startDate,endDate,activity));
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			});
			filestream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		//PrintWriter writer = new PrintWriter("tasks.txt");
		
		System.out.println("1) Calculating number of distinct days");
		List<Integer> distinctDays = dataList.stream().map( m ->{
			Calendar time = Calendar.getInstance();
			time.setTime(m.getStart_time());
			return time.get(Calendar.DAY_OF_MONTH);
		}).distinct().collect(Collectors.toList());
		
		System.out.println("SOLVED: Number of disting days in log: "+ distinctDays.size());
		
		System.out.println("2) Counting how many times each activity has appeared over the entire monitoring period");
		System.out.println("SOLVED: Result listed below:");
        Map<String,Integer> distinctAction =
                dataList.stream()
                        .collect(Collectors.groupingBy(m->m.getActivity(), Collectors.summingInt(m->1)));
        distinctAction.entrySet().stream().forEach(m-> System.out.println(m.getKey()+"->"+m.getValue()));
        System.out.println("");
        
        //task3
        System.out.println("3) Counting how many times has appeared each activity for each day over the monitoring period");
        Map<Long, Map<String, Integer>> map=dataList.stream()
				.collect(Collectors.groupingBy(m->m.getStart_time().getTime()/(1000*60*60*24), Collectors.toMap(MonitoredData::getActivity,e->1,Integer::sum)));
        map.entrySet().stream().forEach(m-> System.out.println(m.getKey()+"->"+m.getValue()));;
        
        System.out.println("4) Calculating duration of each activity (per line)");
        List<Long> difference=dataList.stream()
        							.map(m->m.getEnd_time().getTime()-m.getStart_time().getTime())
        							.collect(Collectors.toList());
        difference.stream().forEach(m-> System.out.println(m));
        
        System.out.println("5) Computing entire duration of each activity");
        Map<String,Long> totalDuration=dataList.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity,Collectors.summingLong(m->m.getEnd_time().getTime()-m.getStart_time().getTime())));
        totalDuration.entrySet().stream().forEach(m-> System.out.println(m.getKey()+"->"+m.getValue()));
		
	}

}

