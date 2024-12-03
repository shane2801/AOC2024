import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.out;

public class Day3{
    public static void main(String[] args){
        part1();
        part2();
    }

    public static StringBuilder getInput(){
        StringBuilder input =new StringBuilder();
        try {
            File file = new File("puzzles/Day3.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                input.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            out.println("An error occurred.");
            e.printStackTrace();
        }
        return input;
    }

    public static void part1(){
        // Create a pattern to be searched, mul(x,x)
        Pattern p = Pattern.compile("mul\\(\\d+,\\d+\\)");
        // Search above pattern in
        Matcher m = p.matcher(getInput().toString());

        int result =0;
        // Finding string using find() method
        while (m.find()){
            String match = m.group();
            String[] parts = match.substring(4, match.length()-1).split(",");
            result += Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
        }
        out.println(result);
    }

    public static void part2(){
        // Create a pattern to be searched, mul(x,x)
        String pattern = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)";
        Pattern p = Pattern.compile(pattern);
        // Search above pattern in
        Matcher m = p.matcher(getInput().toString());
        int result =0;

        // Finding string using find() method
        boolean enabled = true;
        while (m.find()){
            String match = m.group();
            int x = 0,y = 0;
            if( match.equals("don't()") ) enabled = false;
            else if (match.equals("do()")) enabled = true;
            else {
                String[] parts = match.substring(4, match.length()-1).split(",");
                x = Integer.parseInt(parts[0]);
                y = Integer.parseInt(parts[1]);
            }
            if( enabled ) result +=  x*y ;
        }
        out.println(result);
    }

}


