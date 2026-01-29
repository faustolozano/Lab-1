public class ChessPieces{

    //attributes
    private String piece_name;
    private String color;
    private char pos_X;
    private int pos_Y;



    public ChessPiece(String piece_name, String color, char pos_X, int pos_Y){
        this.piece_name = piece_name;
        this.color = color;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
    }
    
    public String getPieceName(){
        return piece_name;
    }

    public char getPosX(){
        return pos_X;
    }

    public int getPosY(){
        return pos_Y;
    }

    private int convertX(char x){
        return Character.toUpperCase(x) - 'A';
    }


    public boolean canMove(char newX, int newY){

        int currX = convertX(pos_X);
        int targetX = convertX(newX);

        if (targetX < 0 || targetX > 7 || newY < 1 || newY > 8){
            return false;
        }

        if (currX == targetX && newY == pos_Y){
            return false;
        }

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


    private boolean kingMove(int x, int y, int newX, int newY){
        return Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1;
    }

    private boolean rookMove(int x, int y, int newX, int newY){
        return x == newX || y == newY;
    }

    private boolean bishopMove(int x, int y, int newX, int newY){
        return Math.abs(newX - x) == Math.abs(newY - y);
    }

    private boolean queenMove(int x, int y, int newX, int newY){
        return rookMove(x, y, newX, newY) || bishopMove(x, y, newX, newY);
    }

    private boolean knightMove(int x, int y, int newX, int newY){
        int distanceX = Math.abs(newX - x);
        int distanceY = Math.abs(newY - y);
        return (distanceX == 2 && distanceY == 1) || (distanceX == 1 && distanceY == 2);
    }

    private boolean pawnMove(int x, int y, int newX, int newY){
        if (color.equalsIgnoreCase("white")){
            return x == newX && newY == y + 1;
        }
        else{
            return x == newX && newY == y - 1;
        }
    }
}