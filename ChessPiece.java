public class ChessPiece{

    // Attributes
    public String name;
    public String color;
    public char pos_x;
    public int pos_y;


    // Constructs a ChessPieces object with the given attributes.
    public ChessPiece(String name, String color, char pos_x, int pos_y){
        this.name = name;
        this.color = color;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    
}