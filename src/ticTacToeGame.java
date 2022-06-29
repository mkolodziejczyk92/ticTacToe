import java.util.Random;
import java.util.Scanner;

public class ticTacToeGame {

    static Scanner scanner = new Scanner(System.in);
    static Random generator = new Random();
    static char[][] ticTacBoard = new char[3][3];
    static char playerSign = drawSign();
    static char computerSign = playerSign == 'X' ? 'O' : 'X';
    static int gameStart;

    public static void main(String[] args) {

        tiTacPlaying();


    }

    private static void tiTacPlaying() {


        System.out.println("Let's play!");
        System.out.println("Your sign today is: " + playerSign);

        emptyBoard();

        gameStart = generator.nextInt(2);
        if (gameStart == 0) {
            System.out.println("The player will start the game!");
            do {
                playerMove();
                if(anyoneHasWon(ticTacBoard)){
                    break;
                }
                computerMove();
            }
            while (!anyoneHasWon(ticTacBoard));
            theEndOfTheGame();
        } else {
            System.out.println("The computer will start the game!");
            do {
                computerMove();
                if(anyoneHasWon(ticTacBoard)){
                    break;
                }
                playerMove();
            }
            while (!anyoneHasWon(ticTacBoard));
            theEndOfTheGame();
        }

    }

    private static boolean anyoneHasWon(char[][] a) {

        if (a[0][0] == a[0][1] && a[0][0] == a[0][2] && a[0][0] != '-') {
            return true;
        } else if (a[0][0] == a[1][0] && a[0][0] == a[2][0] && a[0][0] != '-') {
            return true;
        } else if (a[1][0] == a[1][1] && a[1][0] == a[1][2] && a[1][0] != '-') {
            return true;
        } else if (a[2][0] == a[2][1] && a[2][0] == a[2][2] && a[2][0] != '-') {
            return true;
        } else if (a[0][1] == a[1][1] && a[0][1] == a[2][1] && a[0][1] != '-') {
            return true;
        } else if (a[0][2] == a[1][2] && a[0][2] == a[2][2] && a[0][2] != '-') {
            return true;
        } else if (a[0][0] == a[1][1] && a[0][0] == a[2][2] && a[0][0] != '-') {
            return true;
        } else if (a[0][2] == a[1][1] && a[0][2] == a[2][0] && a[0][2] != '-') {
            return true;
        } else {
            return false;
        }
    }


    private static void theEndOfTheGame() {
        System.out.println("THE END! PLAYER WON!");
        System.out.println(" ");
        System.out.println("Do You want to play again?");
        System.out.println("Enter [1] for YES or [2] for NO:");
        playingAgain();
    }

    private static void playingAgain() {
        switch (getInput()) {
            case 1:
                tiTacPlaying();
                break;
            case 2:
                System.out.println("Thank you for playing! BB");
                break;
            default:
                System.out.println("Wrong number! Please enter [1] or [2]");
                playingAgain();
        }
    }
    


    private static void computerMove() {
        System.out.println("It's computer time!");
        int x = getComputerCoordinates();
        int y = getComputerCoordinates();

        while (checkDuplicatedPosition(x, y)) {
            x = getComputerCoordinates();
            y = getComputerCoordinates();
        }
        ticTacBoard[x][y] = computerSign;
        paintingBoardAfterMove();
    }

    private static int getComputerCoordinates() {
        return generator.nextInt(3);
    }

    private static int getInput() {

        return scanner.nextInt();
    }


    private static void playerMove() {
        int x;
        int y;
        boolean isDuplicated;

        do {
            System.out.println("Where do you want to put a sign?");
            System.out.println("Enter a first number (1-3): ");
            x = getTableIndexAccordingInput(getInput());
            while (x == -1) {
                System.out.println("Wrong number! Please enter a number from [1] to [3]:");
                x = getTableIndexAccordingInput(getInput());
            }
            System.out.println("Enter a second number (1-3): ");
            y = getTableIndexAccordingInput(getInput());
            while (y == -1) {
                System.out.println("Wrong number! Please enter a number from [1] to [3]:");
                y = getTableIndexAccordingInput(getInput());
            }
            isDuplicated = checkDuplicatedPosition(x, y);

            if (isDuplicated) {
                System.out.println("Your position " + "[" + (x + 1) + "]" + "[" + (y + 1) + "] " + "is duplicated! Enter new numbers.");
            }
        } while (isDuplicated);

        ticTacBoard[x][y] = playerSign;
        paintingBoardAfterMove();

    }


    private static boolean checkDuplicatedPosition(int x, int y) {

        return ticTacBoard[x][y] != '-';
    }

    private static char drawSign() {
        if (generator.nextBoolean()) {
            return 'X';
        } else {
            return 'O';
        }
    }

    private static int getTableIndexAccordingInput(int input) {
        switch (input) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
        }
        return -1;
    }


    private static void emptyBoard() {
        for (int i = 0; i < ticTacBoard.length; i++) {
            for (int j = 0; j < ticTacBoard.length; j++) {
                ticTacBoard[i][j] = '-';
                System.out.print(ticTacBoard[i][j]);
            }
            System.out.println();
        }
    }

    private static void paintingBoardAfterMove() {

        for (int i = 0; i < ticTacBoard.length; i++) {
            for (int j = 0; j < ticTacBoard.length; j++) {
                System.out.print(ticTacBoard[i][j] + " ");
            }
            System.out.println();
        }
    }


}
