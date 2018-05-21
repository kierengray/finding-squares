import java.util.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.ArrayList;

class Squares{
    public static void main(String[] args) {
        if(args.length==1){
        Set<List<Integer>> allPoints= readData(args[0]); //read data into HashSet.
        Set<Set<List<Integer>>> listofSquares= findSquares(allPoints); //Finds squares and returns set of them.
        System.out.println(listofSquares.size()); //Print the number of elements in the set of squares.
      }
      else{
        System.out.println("Wrong parameters");
      }
    }

    /*
    This method reads each x,y point into an ArrayList<Integer> and adds it to a LinkedHashSet. This set is then
    returned to the main method.
     */
    public static Set<List<Integer>> readData(String filename) {
        Set<List<Integer>> allPoints= new LinkedHashSet<List<Integer>>(10000);
        try{
            Scanner sc= new Scanner(new FileReader(filename));
            while(sc.hasNextInt()){
                allPoints.add(newPoint(sc.nextInt(), sc.nextInt()));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            System.exit(1);
        }
        return allPoints;
    }

    /*
    This method compares every pair of points and calculates the other 2 points needed to create a square. If these
    2 calculated points are both located in the original set of points then a set of the 4 points that make
    a square are added to the set of squares. This set of squares is returned at the end.
     */
    public static Set<Set<List<Integer>>> findSquares(Set<List<Integer>>  allPoints){
        Set<Set<List<Integer>>> listofSquares= new LinkedHashSet<Set<List<Integer>>>();
        int i=0;
        for (List<Integer> point1 : allPoints) {
            int j=0;
            for (List<Integer> point2 : allPoints) {
                if(j>i){
                    List<Integer> point3= newPoint(
                            point1.get(0)-point2.get(1)+point1.get(1),  //calculates x value of 3rd corner
                            point1.get(1)+point2.get(0)-point1.get(0)); //calculates y value of 3rd corner

                    List<Integer> point4= newPoint(
                            point2.get(0)-point2.get(1)+point1.get(1),  //calculates x value of 4th corner
                            point2.get(1)+point2.get(0)-point1.get(0)); //calculates y value of 4th corner

                    if(allPoints.contains(point3) && allPoints.contains(point4)){
                        listofSquares.add(createSquare(point1, point2, point3, point4));
                    }
                }
                j++;
            }
            i++;
        }
        return listofSquares;
    }

    //This method creates an ArrayList of 2 integers x and y to represent a point and returns this list.
    public static List<Integer> newPoint(int x, int y){
        List<Integer> xypair= new ArrayList<Integer>(2);
        xypair.add(x);
        xypair.add(y);
        return xypair;
    }

    //This method creates a LinkedHashSet and adds 4 points(ArrayList with x,y) to represent a square.
    public static Set<List<Integer>> createSquare(List<Integer> point1, List<Integer> point2, List<Integer> point3,
                                                            List <Integer> point4){
        Set<List<Integer>> square= new LinkedHashSet<List<Integer>>();
        square.add(point1);
        square.add(point2);
        square.add(point3);
        square.add(point4);
        return square;
    }
}
