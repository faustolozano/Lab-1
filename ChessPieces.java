public class ChessPieces{

    // Attributes
    private String piece_name;
    private String color;
    private char pos_X;
    private int pos_Y;


    // Constructs a ChessPieces object with the given attributes.
    public ChessPieces(String piece_name, String color, char pos_X, int pos_Y){
        this.piece_name = piece_name;
        this.color = color;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
    }
    
    //Getters
    public String getPieceName(){
        return piece_name;
    }

    // Returns the current X (column) position of the chess piece.
    public char getPosX(){
        return pos_X;
    }

    // Returns the current Y (row) position of the chess piece.
    public int getPosY(){
        return pos_Y;
    }

    // Converts a column character (A–H) into a zero-based integer index.
    private int convertX(char x){
        return Character.toUpperCase(x) - 'A';
    }

    // Determines if the chess piece can move from its current position to a specified target position.
    public boolean canMove(char newX, int newY){

        int currX = convertX(pos_X);
        int targetX = convertX(newX);

        // Validate board boundaries
        if (targetX < 0 || targetX > 7 || newY < 1 || newY > 8){
            return false;
        }

        // Prevent moving to the same position
        if (currX == targetX && newY == pos_Y){
            return false;
        }


        // Determine movement rules based on piece type
        if (piece_name.equalsIgnoreCase("king")){
            return kingMove(currX, pos_Y, targetX, newY);
        }

        else if (piece_name.equalsIgnoreCase("rook")){
            return rookMove(currX, pos_Y, targetX, newY);
        }

        else if (piece_name.equalsIgnoreCase("queen")){
            return queenMove(currX, pos_Y, targetX, newY);
        }

        else if (piece_name.equalsIgnoreCase("bishop")){
            return bishopMove(currX, pos_Y, targetX, newY);
        }

        else if (piece_name.equalsIgnoreCase("knight")){
            return knightMove(currX, pos_Y, targetX, newY);
        }

        else if (piece_name.equalsIgnoreCase("pawn")){
            return pawnMove(currX, pos_Y, targetX, newY);
        }

        return false;
    }

    // Validates the movement the king.
    private boolean kingMove(int x, int y, int newX, int newY){
        return Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1;
    }

    // Validates the movement of the rook
    private boolean rookMove(int x, int y, int newX, int newY){
        return x == newX || y == newY;
    }

    // Validates the movement of the bishop
    private boolean bishopMove(int x, int y, int newX, int newY){
        return Math.abs(newX - x) == Math.abs(newY - y);
    }

    // Validates the movement of the queen
    private boolean queenMove(int x, int y, int newX, int newY){
        return rookMove(x, y, newX, newY) || bishopMove(x, y, newX, newY);
    }

    // Validates the movement of the queen
    private boolean knightMove(int x, int y, int newX, int newY){
        int distanceX = Math.abs(newX - x);
        int distanceY = Math.abs(newY - y);
        return (distanceX == 2 && distanceY == 1) || (distanceX == 1 && distanceY == 2);
    }
    
    // Validates the movement of the pawn
    private boolean pawnMove(int x, int y, int newX, int newY){
        if (color.equalsIgnoreCase("white")){
            return x == newX && newY == y + 1;
        }
        else{
            return x == newX && newY == y - 1;
        }
    }
}