import java.util.Scanner;


public class XO {

    private static boolean checkedX = false;
    private static char[][] board = new char[3][3];
    private static boolean[][] marked = new boolean[3][3];
    private static boolean gameOver = false;

    public static void main(String[] args) {
        initBoard();
        startGame();
    }

    private static void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void startGame() {
        while (!gameOver) {
            Scanner scanner = new Scanner(System.in);

            if(!checkedX) {
                System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
            }

            String[] rowandcolumn = scanner.nextLine().split(" ");

            int r = 0;
            int c = 0;

            if(rowandcolumn.length > 2) {
                System.out.println("We can not input numbers more than 2... Try again");
                startGame();
            } else {
                r = Integer.parseInt(rowandcolumn[0]) - 1;
                c = Integer.parseInt(rowandcolumn[1]) - 1;
            }

            if(r < board.length && c < board.length) {
                if (!marked[r][c]) {
                    if (!checkedX) {
                        playerX(r, c);
                        printBoard();
                        if (checkPlayerXWin()) {
                            System.out.println("PLAYER 'X' WINS!");
                            gameOver = true;
                            break;
                        }

                        if (checkGameForDraw()) {
                            System.out.println("AND IT IS A DRAW!");
                            gameOver = true;
                            break;
                        }
                    } else {
                        playerO(r, c);
                        printBoard();
                        if (checkPlayerOWin()) {
                            System.out.println("PLAYER 'O' WINS!");
                            gameOver = true;
                            break;
                        }

                        if (checkGameForDraw()) {
                            System.out.println("AND IT IS A DRAW!");
                            gameOver = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("This move at (" + (r + 1) + "," + (c + 1) + ") is not valid... Try again");
                    startGame();
                }
            } else {
                System.out.println("Your move is out of bounds... Try again");
                startGame();
            }
        }
    }

    private static void playerX(int r,  int c) {
        board[r][c] = 'X';
        marked[r][c] = true;
        checkedX = true;
    }

    private static void playerO(int r,  int c) {
        board[r][c] = 'O';
        marked[r][c] = true;
        checkedX = false;
    }

    private static void printBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("-----------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("-----------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    private static boolean checkPlayerXWin() {

        int count;

        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 'X') count++;
                if(count == 3) return true;
            }
        }

        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board.length; j++) {
                count += (board[j][i] == 'X') ? 1 : 0;
                if(count == 3) return true;
            }
        }

        count = 0;
        for (int i = 0; i < board.length; i++) {
            count += (board[i][i] == 'X') ? 1 : 0;
            if(count == 3) return true;
        }

        count = 0;
        for (int i = 0; i < board.length; i++) {
            count += (board[i][(board.length-1)-i] == 'X') ? 1 : 0;
            if(count == 3) return true;
        }

        return false;
    }

    private static boolean checkPlayerOWin() {
        int count;

        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board.length; j++) {
                count += (board[i][j] == 'O') ? 1 : 0;
                if(count == 3) return true;
            }
        }

        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board.length; j++) {
                count += (board[j][i] == 'O') ? 1 : 0;
                if(count == 3) return true;
            }
        }

        count = 0;
        for (int i = 0; i < board.length; i++) {
            count += (board[i][i] == 'O') ? 1 : 0;
            if(count == 3) return true;
        }


        count = 0;
        for (int i = 0; i < board.length; i++) {
            count += (board[i][(board.length-1)-i] == 'O') ? 1 : 0;
            if(count == 3) return true;
        }

        return false;
    }

    private static boolean checkGameForDraw() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}

