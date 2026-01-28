import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void Main(String[] args) {
        //File reader
        try{
            File file = new File("ChessPieces.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                fileReader.nextLine();
                size++;
            }
            fileReader.close();


            fileReader = new Scanner(file);
            ChessPieces[] pieces = [size];
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                String piece_name = data[0];
                String color = data[1];
                int pos_X = Integer.parseInt(data[2]);
                int pos_Y = Integer.parseInt(data[3]);

                ChessPiece piece = new ChessPiece(piece_name, color, pos_X, pos_Y);

            }
        }

        catch (FileNotFoundException e){
            System.out.println("File not found" + e.getMessage());
        }

        //Main
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a new position (X, Y) where you want your piece to move");
        int position = 0;
        position = scan.nextInt();

        for(int i = 0; i < pieces.length; i++){

        }

    }
}