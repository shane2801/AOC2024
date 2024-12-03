import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.*;

public class Day1{

    public static void main(String[] args){

        List<Integer> left = new LinkedList<>();
        List<Integer> right = new LinkedList<>();

        try {
            File myObj = new File("puzzles/Day1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitData = data.replaceAll(" +", " ").split(" ");
                left.add(Integer.parseInt(splitData[0]));
                right.add(Integer.parseInt(splitData[1]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            out.println("An error occurred.");
            e.printStackTrace();
        }

        part1(left, right);
        part2(left, right);

    }

    public static void part1(List<Integer> left, List<Integer> right  ){

        Collections.sort(left);
        Collections.sort(right);

        long sum =0;
        for ( int i=0,j=0; i<left.size() && j<right.size(); i++,j++ ) sum+=Math.abs(left.get(i)-right.get(j));

        out.println(sum);
    }
    public static void part2(List<Integer> left, List<Integer> right  ){

        Map<Integer,Integer> rightMap = new HashMap<>();
        right.forEach(integer->rightMap.put(integer, !rightMap.containsKey(integer) ? 1 : rightMap.get(integer)+1));

        int similarityScore =left.stream().filter(rightMap::containsKey).mapToInt(integer->integer * rightMap.get(integer)).sum();

        out.println(similarityScore);
    }
}
