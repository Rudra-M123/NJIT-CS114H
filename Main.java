/** File: Main.java
 *  Professor: Professor Kapleau
 *  Name: Rudra Mehta (rm279@njit.edu)
 *  Course: CS114
 *  Section: H02
 *
 *  The code below will read a file named "maze.dat" and display paths leading from the starting to the ending position 
 *  marked in "+" and "-", respectively. Paths that lead to dead-ends are marked with "." to prevent confusion.
 *  More specific commenting is given above each code segment, all located below.
 *  
 *  kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=
 */

//import required packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Main class that will contain the program
public class Main {
    //static variables to be used by all methods
    private static String[][] maze = new String[0][0];
    private static int posX, posY, endX, endY;
    private static String currentPath = "", correctPath = "";

    //controls program and calls methods to create, view, and solve the maze 
    public static void main(String[] args) {
        mazeGen("maze.dat");
        mazeSolve();
        mazeView();
    }

    //reads the file to discover the dimensions, fill the 2d string array accordingly, and locate "+" and "-" positions
    private static void mazeGen(String fileName){
        //2d string array to be populated with symbols or ignored if using incorrect file name 
        try (Scanner sc = new Scanner(new File(fileName))){
            //gathering row and column data from first line of the file
            String line = sc.nextLine();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);
            
            //populating values for the maze array using the file
            maze = new String[row][col];
            for(int r = 0; r < maze.length; r++){
                line = sc.nextLine();
                for(int c = 0; c < maze[r].length; c++){
                    maze[r][c] = line.substring(c,c+1);
                }
            }

        //if this executes, the file cannot be found
        } catch(FileNotFoundException e){
            System.out.println("The file \"" + fileName + "\" cannot be found in the directory that holds \"Main.java\"");
            System.exit(0);
        }

        //locate coordinates of starting and ending positions
        for(int r = 0; r < maze.length; r++){
            for(int c = 0; c < maze[r].length; c++){
                if(maze[r][c].equals("+")){ //find "+" to find starting position
                    posX = c;
                    posY = r;
                }

                if(maze[r][c].equals("-")){ //find "-" to find ending position
                    endX = c;
                    endY = r;
                }
            }
        }
    }

    //prints the maze to the terminal for the user to view
    private static void mazeView(){
        for(String[] row : maze){
            for(String symbol : row)
                System.out.print(symbol);

            System.out.println();
        }
    }

    //recursively traverses the maze and changes symbols to show correct and incorrect steps to go from "+" to "-"
    private static void mazeSolve(){
        //base case - program is at the end position
        if(posX == endX && posY == endY){
            System.out.println("Solved!");
        }

        //move case
        else if(check("right") || check("left") || check("down") || check("up")){
            //moves in a certain direction, if possible
            if(check("right")){ //move rightwards, if possible
                posX++;
                currentPath += "r";
            }

            else if(check("down")){ //move downwards, if possible
                posY++;
                currentPath += "d";
            }

            else if(check("left")){ //move leftwards, if possible
                posX--;
                currentPath += "l";
            }
            
            else{ //move upwards, if possible
                posY--;
                currentPath += "u";
            }

            //places a "+" at the new position and consider it the right position to move to
            maze[posY][posX] = "+";
            correctPath = currentPath;
            mazeSolve();
        }
        
        //backtrack case
        else{
            try{
                //makes the current position a dead-end
                maze[posY][posX] = ".";

                //returns to the previous position
                if(currentPath.charAt(currentPath.length()-1) == 'r') //previous direction was right so move left
                    posX--;

                else if(currentPath.charAt(currentPath.length()-1) == 'd') //previous direction was down so move up
                    posY--;

                else if(currentPath.charAt(currentPath.length()-1) == 'l') //previous direction was left so move right
                    posX++;

                else //previous direction was up so move down
                    posY++;

                //replays the maze from before reaching a dead-end            
                currentPath = currentPath.substring(0, currentPath.length()-1);
                mazeSolve();
            }

            //maze not solvable case
            catch(StringIndexOutOfBoundsException e){
                System.out.println("This maze cannot be solved. Check if a solution path exists from \"+\" to \"-\"."); 
                System.exit(0);
            }
        }
    }

    //determines whether you can move to an adjacent position
    private static boolean check(String dir){
        if(dir.equals("right")) //checks right direction
            return posX + 1 < maze[posY].length && (maze[posY][posX + 1].equals(" ") || maze[posY][posX+1].equals("-"));

        else if(dir.equals("down")) //checks down direction
            return posY + 1 < maze.length && (maze[posY+1][posX].equals(" ") || maze[posY+1][posX].equals("-")); 

        else if(dir.equals("left")) //checks left direction
            return posX - 1 > 0 && (maze[posY][posX - 1].equals(" ") || maze[posY][posX - 1].equals("-"));
            
        else //intended to check up direction
            return posY - 1 > 0 && (maze[posY - 1][posX].equals(" ") || maze[posY - 1][posX].equals("-"));
    }
}