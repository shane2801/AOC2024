import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Day4{
    static List<List<String>> grid = new ArrayList<>();

    public static void main(String[] args){
        // initialise grid
        getInput();

        part1();
        part2();
    }
    public static void part1(){
        int matchesFound =0;
        for ( int i = 0; i<grid.size();i++) {
            List<String> current = grid.get(i);
            for ( int j=0; j<current.size(); j++ ) {
                if( current.get(j).equals("X") ){
                    matchesFound += xmasMatchFound(i,j);
                }
            }
        }
        out.println(matchesFound);
    }
    public static void part2(){
        int matchesFound =0;
        for ( int i = 0; i<grid.size();i++) {
            List<String> current = grid.get(i);
            for ( int j=0; j<current.size(); j++ ) {
                if( current.get(j).equals("A") ){
                    matchesFound += x_masMatchFound(i,j);
                }
            }
        }
        out.println(matchesFound);
    }
    public static int x_masMatchFound(int i, int j){
        int matchesFound = 0;
        // if diagonal neighbours are in grid and diagonal neighbours are either M or S and always diagonally the opposite of each other.
        if( isIndexInGrid(i-1,j+1) && isIndexInGrid(i-1,j-1) && isIndexInGrid(i+1,j+1) && isIndexInGrid(i+1,j-1) ){
            String topLeft = grid.get(i-1).get(j-1);
            String topRight = grid.get(i-1).get(j+1);
            String bottomLeft = grid.get(i+1).get(j-1);
            String bottomRight = grid.get(i+1).get(j+1);
            if(((topLeft.equals("M")&&bottomRight.equals("S")) || ( topLeft.equals("S")&&bottomRight.equals("M")))
               && ((topRight.equals("M")&&bottomLeft.equals("S"))|| ( topRight.equals("S")&&bottomLeft.equals("M"))) ){
                matchesFound +=1;
            }
        }
        return matchesFound;
    }
    public static int xmasMatchFound(int i, int j){
        int matchesFound = 0;
        // right
        if( isIndexInGrid(i,j+3) ){
            if( grid.get(i).get(j+1).equals("M") && grid.get(i).get(j+2).equals("A") &&grid.get(i).get(j+3).equals("S") ) matchesFound+=1;
        }
        // left
        if( isIndexInGrid(i,j-3) ){
            if( grid.get(i).get(j-1).equals("M") && grid.get(i).get(j-2).equals("A") &&grid.get(i).get(j-3).equals("S") ) matchesFound+=1;
        }
        // top
        if( isIndexInGrid(i-3,j) ){
            if( grid.get(i-1).get(j).equals("M") && grid.get(i-2).get(j).equals("A") &&grid.get(i-3).get(j).equals("S") ) matchesFound+=1;
        }
        // bottom
        if( isIndexInGrid(i+3,j) ){
            if( grid.get(i+1).get(j).equals("M") && grid.get(i+2).get(j).equals("A") &&grid.get(i+3).get(j).equals("S") ) matchesFound+=1;
        }
        // top right
        if( isIndexInGrid(i-3,j+3) ){
            if( grid.get(i-1).get(j+1).equals("M") && grid.get(i-2).get(j+2).equals("A") &&grid.get(i-3).get(j+3).equals("S") ) matchesFound+=1;
        }
        // top left
        if( isIndexInGrid(i-3,j-3) ){
            if( grid.get(i-1).get(j-1).equals("M") && grid.get(i-2).get(j-2).equals("A") &&grid.get(i-3).get(j-3).equals("S") ) matchesFound+=1;
        }
        // bottom right
        if( isIndexInGrid(i+3,j+3) ){
            if( grid.get(i+1).get(j+1).equals("M") && grid.get(i+2).get(j+2).equals("A") &&grid.get(i+3).get(j+3).equals("S") ) matchesFound+=1;
        }
        // bottom left
        if( isIndexInGrid(i+3,j-3) ){
            if( grid.get(i+1).get(j-1).equals("M") && grid.get(i+2).get(j-2).equals("A") &&grid.get(i+3).get(j-3).equals("S") ) matchesFound+=1;
        }
        return matchesFound;
    }

    public static boolean isIndexInGrid(int i, int j){
        return (i>=0&&i<=grid.size()-1)&&(j>=0&&j<=grid.get(0).size()-1);
    }

    public static void getInput(){
        try {
            File file = new File("puzzles/Day4.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                grid.add(Arrays.asList(line.split("")));
            }
        } catch (FileNotFoundException e) {
            out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
