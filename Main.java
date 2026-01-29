/* Fausto Lozano, Marisol Anaya Molina, Victoria Santana 
[CS 3331] Lab 1: Programming skills assessment

This work reads a list of chess pieces and their positions from a file and determines whether each piece can 
legally move to a user-specified target position on an 8×8 chessboard. Movement validation follows standard 
chess rules for each piece, assuming the board contains only the piece being evaluated.

Change log:

01/23/2026:
Fausto Lozano
- Created main class basic structure

01/27/2026:
Victoria Santana & Marisol Anaya Molina
- Created chesspieces class
- Crated getters
- Created the validation method
- Crated pieces movements
- Created file scanner

01/28/2026:
Victoria Santana:
- Changed file reader

01/29/2026:
Fausto Lozano
- Created the header and comments on the functionality of methods, parameters, and return values
- Changed file reader

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    
    
    /* Asks the user for a file name and a target chessboard position,
     * reads chess piece data from the file, creates chess piece objects,
     * and verifies whether each piece can move to the target position.
     */
    public static void main(String[] args) {

        // Prompt user for the input file name
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter file's name: ");
        String fileName = scan.nextLine();

        // File object representing the input file
        File file = new File(fileName);

        try (Scanner fileReader = new Scanner(file)) {
            // Asks user for the target position on the chessboard
            System.out.print("Enter target position (x,y): ");
            char targetX = scan.next().charAt(0);
            int targetY = scan.nextInt();

            System.out.println();
            
            // Read the file line by line
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine().trim();
                // Skip empty lines
                if (line.isEmpty()){
                    continue;
                }
                // Split line into parts
                String[] data = line.split(",");
                // Skip invalid lines
                if (data.length < 4) {
                    continue;
                }

                // Extract chess piece attributes
                String piece_name = data[0].trim();
                String color = data[1].trim();
                char pos_X = data[2].trim().charAt(0);
                int pos_Y = Integer.parseInt(data[3].trim());
                
                // Create a ChessPieces object using the extracted data
                ChessPieces piece = new ChessPieces(piece_name, color, pos_X, pos_Y);

                // Check if this piece can move to the target
                if (piece.canMove(targetX, targetY)) {
                    System.out.println(piece.getPieceName() + " at " + piece.getPosX() + ", " + piece.getPosY() +" can move to " + targetX + ", " + targetY);
                } else {
                    System.out.println(piece.getPieceName() + " at " + piece.getPosX() + ", " + piece.getPosY() +" can NOT move to " + targetX + ", " + targetY);
                }
            }

            scan.close();

        } catch (FileNotFoundException e) {
            // Handles case where the file cannot be found
            System.out.println("File not found.");
        } catch (NumberFormatException e) {
            // Handles invalid numeric values in the input file
            System.out.println("Error parsing number from file.");
        }
    }
}