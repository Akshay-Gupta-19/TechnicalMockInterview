/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Akshay Gupta
 */
interface GameBoard{
    void move(Move move) throws InvalidMoveException;
    boolean isCheck(Move move);
    boolean isMate(Move move);
    boolean isDraw(Move move);
    boolean isGameFinished();
}
class Move{
    Box origin;
    Box destination;
}
class Box{
    int row;
    int column;
    Piece piece;
    
}

abstract class Piece{
   private  PieceColor pieceColor;
    void isValidMove(Move move) throws InvalidMoveException{
        if(move.destination.piece!=null && move.destination.piece.getPieceColor() == pieceColor){
            throw new InvalidMoveException("Friend at destination");
        }
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }
    
}
enum PieceColor{
    White,
    Black
}
class InvalidMoveException extends Exception{
    public InvalidMoveException(String message) {
        super(message);
    }
}
class Player{
    String name;
    Game game;
}
enum Turn{
    White,
    Black
}
class Game implements Runnable{
    GameBoard board;
    Player player1;
    Player player2;
    Turn turn;
    
    @Override
    public void run(){
        while(board.isGameFinished()){
            System.out.println("Make next move");
            Move move=null;
            try {
                board.move(move);
            } catch (InvalidMoveException ex) {
                System.out.println(ex);
                System.err.println("Incorrect move please make a correct move");
            }
        }
    }
}