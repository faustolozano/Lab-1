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
- Created the validation logic
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

public class Team7_Lab1 {
    /* Asks the user for a file name and a target chessboard position,
     * reads chess piece data from the file, creates chess piece objects,
     * and verifies whether each piece can move to the target position.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Asks user for the input file name
        System.out.print("Enter file's name: ");
        String fileName = scan.nextLine();
        File file = new File(fileName);

        int count = 0;

        // Count number of lines to create array
        try {
            Scanner counter = new Scanner(file);
            while (counter.hasNextLine()) {
                String line = counter.nextLine().trim();
                if (!line.isEmpty()) {
                    count++;
                }
            }
            counter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        // Create array that will store the pieces
        ChessPiece[] pieces = new ChessPiece[count];

        // Read file and fills array
        try {
            Scanner reader = new Scanner(file);
            int index = 0;

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length < 4) continue;

                pieces[index++] = new ChessPiece(
                        data[0].trim(),
                        data[1].trim(),
                        Character.toUpperCase(data[2].trim().charAt(0)),
                        Integer.parseInt(data[3].trim())
                );
            }
            reader.close();
        } 
        
        catch (FileNotFoundException e) {
            // Handles case where the file cannot be found
            System.out.println("File not found.");
            return;
        } 
        
        catch (NumberFormatException e) {
            // Handles invalid numeric values in the input file
            System.out.println("Error parsing number from file.");
            return;
        }

        // Asks user for the target position on the chessboard
        System.out.print("Enter target position (x, y): ");
        char targetX = Character.toUpperCase(scan.next().charAt(0));
        int targetY = scan.nextInt();

        // Traverse array to verify move
        for (int i = 0; i < pieces.length; i++) {

            ChessPiece p = pieces[i];
            boolean canMove = true;

            char xChar = Character.toUpperCase(p.pos_x);

            int x = xChar - 'A';
            int newX = targetX - 'A';
            int y = p.pos_y;
            int newY = targetY;

            // Check if it's inside the board
            if (x < 0 || x > 7 || y < 1 || y > 8 ||
                newX < 0 || newX > 7 || newY < 1 || newY > 8) {

                canMove = false;
            } else {

                // Determine movement based on piece type
                switch (p.name.toLowerCase()) {

                    case "king":
                        canMove = Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1;
                        break;

                    case "queen":
                        canMove = (Math.abs(newX - x) == Math.abs(newY - y)) ||
                                  (x == newX || y == newY);
                        break;

                    case "rook":
                        canMove = x == newX || y == newY;
                        break;

                    case "bishop":
                        canMove = Math.abs(newX - x) == Math.abs(newY - y);
                        break;

                    case "knight":
                        int dx = Math.abs(newX - x);
                        int dy = Math.abs(newY - y);
                        canMove = (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
                        break;

                    case "pawn":
                        if (p.color.equalsIgnoreCase("white")) {
                            canMove = x == newX && newY == y + 1;
                        } else {
                            canMove = x == newX && newY == y - 1;
                        }
                        break;

                    default:
                        canMove = false;
                }
            }

            // Output
            if (canMove) {
                System.out.println(p.name + " at " + p.pos_x + ", " + p.pos_y +
                        " can move to " + targetX + ", " + targetY);
            } else {
                System.out.println(p.name + " at " + p.pos_x + ", " + p.pos_y +
                        " can NOT move to " + targetX + ", " + targetY);
            }
        }

        scan.close();
    }
}