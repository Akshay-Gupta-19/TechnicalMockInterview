/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess;

/**
 *
 * @author Akshay Gupta
 */
public class ClassicChess {
    
}

class ClassicChessGame implements GameBoard{

    Box boxes[][];

    public ClassicChessGame() {
        boxes=new Box[8][8];
    }
    
    @Override
    public void move(Move move) throws InvalidMoveException {
        move.origin.piece.isValidMove(move);
        boxes[move.destination.row][move.destination.column].piece= boxes[move.origin.row][move.origin.column].piece;
        boxes[move.origin.row][move.origin.column].piece = null;
        if(isCheck(move)){
            if(isMate(move)){
                System.out.println("Check mate");
            }
        }
        if(isDraw(move)){
            System.out.println("Game draw");
        }
    }
    public boolean isCheck(Move move){
        return false;
    }
    public boolean isMate(Move move){
        return false;
    }
    public boolean isDraw(Move move){
        return false;
    }

    @Override
    public boolean isGameFinished() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

class King extends Piece{

    @Override
    public void isValidMove(Move move) throws InvalidMoveException {
        super.isValidMove(move);
    }
}

class Pawn extends Piece{

    @Override
    public void isValidMove(Move move) throws InvalidMoveException {
        super.isValidMove(move);
    }

    @Override
    public PieceColor getPieceColor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}