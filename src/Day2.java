import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

public class Day2{
    static Map<List<String>, Boolean> safeMap = new HashMap<>();
    public static void main(String[] args){
        part1();
        part2();
    }
    public static void part1(){
        List<List<String>> reports = getInput();
        for ( List<String> report: reports) {
            if( isReportSafe(report) ) safeMap.put(report, true);
            else safeMap.put(report, false);
        }
        out.println(getSafeReportsCount());
    }
    public static void part2(){
        for (var entry : safeMap.entrySet()) {
            if( !entry.getValue() ){
                List<String> report = entry.getKey();
                // test level removals to see if we can make a report safe
                List<List<String>> generatedReports = generate(report);
                if( generatedReports.stream().anyMatch(Day2::isReportSafe) ){
                    safeMap.put(report, true);
                }
            }
        }
        out.println(getSafeReportsCount());
    }

    // returns the sum of safe reports from the our safeMap
    public static int getSafeReportsCount(){
        return (int) safeMap.entrySet().stream().filter(Map.Entry::getValue).count();
    }
    public static boolean isReportSafe(List<String> report){
        String initialState = "", currentState;
        for ( int i=0; i<report.size()-1; i++ ) {
            int c = Integer.parseInt(report.get(i));
            int n = Integer.parseInt(report.get(i+1));

            // diff equals 0 or greater than 3 return unsafe
            int diff = Math.abs(c-n);
            if( diff==0||diff>3 ) return false;

            // set initial state if at first index
            if( i==0 ) initialState=c>n ? "decreasing" : "increasing";
            else{
                // monitor current state
                currentState = c>n ? "decreasing" : "increasing";
                // check if state changes
                if(!initialState.equals(currentState) ) return false;
            }
        }
        return true;
    }

    // generate versions of a report where a level has been removed
    public static List<List<String>> generate(List<String> report){
        List<List<String>> output = new ArrayList<>();
        for ( int i=0; i<report.size(); i++ ) {
            List<String> temp = new ArrayList<>();
            for ( int j=0; j<report.size(); j++ ) {
                if( i!=j ) temp.add(report.get(j));
            }
            output.add(temp);
        }
        return output;
    }

    public static List<List<String>> getInput(){
        List<List<String>> reports = new ArrayList<>();
        try {
            File file = new File("puzzles/Day2.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data= scanner.nextLine().split(" ");
                reports.add(Arrays.asList(data));
            }
        } catch (FileNotFoundException e) {
            out.println("An error occurred.");
            e.printStackTrace();
        }
        return reports;
    }
}

