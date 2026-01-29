import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        
        File file = new File("ChessPieces.txt");
        try (Scanner fileReader = new Scanner(file)) {

            Scanner scan = new Scanner(System.in);
            System.out.print("Enter target position (X Y): ");
            char targetX = scan.next().charAt(0);
            int targetY = scan.nextInt();

            System.out.println();
            
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine().trim();
                if (line.isEmpty()){
                    continue; // skip empty lines
                }
                String[] data = line.split(",");
                if (data.length < 4) {
                    continue;
                }

                String piece_name = data[0].trim();
                String color = data[1].trim();
                char pos_X = data[2].trim().charAt(0);
                int pos_Y = Integer.parseInt(data[3].trim());

                ChessPiece piece = new ChessPiece(piece_name, color, pos_X, pos_Y);

                // Check if this piece can move to the target
                if (piece.canMove(targetX, targetY)) {
                    System.out.println(piece.getPieceName() + " at " + piece.getPosX() + ", " + piece.getPosY() +" can move to " + targetX + ", " + targetY);
                } else {
                    System.out.println(piece.getPieceName() + " at " + piece.getPosX() + ", " + piece.getPosY() +" can NOT move to " + targetX + ", " + targetY);
                }
            }

            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file.");
        }
    }

}