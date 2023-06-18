/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TickTacToe;

import java.util.*;

/**
 *
 * @author Akshay Gupta
 */
public class OOTIckTackToe {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size ");
        int size=sc.nextInt();
        Board board =new ClassicXO(size);
        Player akshay=new Player("Akshay" , Symbol.X);
        Player shiva=new Player("Shiva", Symbol.O);
        Game game=new Game(board,List.of(akshay,shiva));
        game.playGame();
    }
}

class Game {

    Board board;
    List<Player> players;
    int noOfPlayers;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players=players;
        noOfPlayers = players.size();
    }

    void playGame() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < board.getSize(); i++) {
            Move nextMove = null;
            int playerNumer=i%noOfPlayers;
             System.out.println(players.get(playerNumer).name+"'s turn please enter row, column");
             nextMove = getNextMove(sc, players.get(playerNumer));
            boolean gameEnded = false;
            try {
                gameEnded = board.makeMove(nextMove);
            } catch (BoxAlreadyFull ex) {
                System.out.println("Play again ");
                i--;
            }catch(ArrayIndexOutOfBoundsException ex){
                System.err.println("Out of the board move");
                System.out.println("Play again");
                i--;
            }
            if (gameEnded) {
                System.out.println("Winner " + players.get(playerNumer));
                return;
            }
        }
        System.out.println("Game draw");
    }

    Move getNextMove(Scanner sc, Player symbol) {
        int row = sc.nextInt(), col = sc.nextInt();
        return new Move(row, col, symbol);
    }
}

enum Symbol {
    X,
    O,
    E
}

class Player {

    String name;
    Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", symbol=" + symbol + '}';
    }
    
}

interface Board {

    boolean makeMove(Move move) throws BoxAlreadyFull;

    int getSize();
}

record Move(int row, int col, Player player) {

}

class BoxAlreadyFull extends Exception {

    public BoxAlreadyFull(String message) {
        super(message);
        System.err.println(message);
    }
    
}

class ClassicXO implements Board {

    Symbol board[][];

    public ClassicXO(int size) {
        board=new Symbol[size][size];
        for (int i = 0; i < board.length; i++) {
           Arrays.fill(board[i],Symbol.E);
        }
        printBoard();
    }

    @Override
    public boolean makeMove(Move move) throws BoxAlreadyFull, ArrayIndexOutOfBoundsException{
        if (board[move.row()][move.col()]!= Symbol.E) {
            throw new BoxAlreadyFull("Box is alreayd filled boy");
        }
        board[move.row()][move.col()] = move.player().symbol;
        System.out.println("Current board ");
        printBoard();
        return checkWinner(move);
    }
    void printBoard(){
                for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("");
        }
    }
    boolean checkWinner(Move move) {
        out:for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j]!=move.player().symbol)
                    continue out;
            }
            return true;
        }
        
         out:for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[j][i]!=move.player().symbol)
                    continue out;
            }
            return true;
        }
         out:for (int i = 0; i < board.length; i++) {
             for (int j = 0,k=0; j < board.length && k<board[0].length; j++,k++) {
                 if(board[i][j]!=move.player().symbol){
                     continue out;
                 }
             }
             return true;
        }
         out:for (int i = 0; i < board.length; i++) {
             for (int j = 0,k=board[0].length-1; j < board.length && k<board[0].length; j++,k--) {
                 if(board[i][j]!=move.player().symbol){
                     continue out;
                 }
             }
             return true;
        }
        return false;
    }

    @Override
    public int getSize() {
        return board.length * board[0].length;
    }

}
