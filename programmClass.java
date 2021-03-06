import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;
public class programmClass {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

        public static void main(String []args){
            //To make the game board bigger, lengthen the row
            char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '}};

            Scanner scan = new Scanner(System.in);

            while(true){
                System.out.print("Enter a number (1-9): ");
                int playerPos = scan.nextInt();
                while(playerPositions.contains(playerPos) || (cpuPositions.contains(playerPos))){
                    System.out.println("Position Taken. Input a new position");
                    playerPos = scan.nextInt();
                }

                placePiece(gameBoard, playerPos, "player");
                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1;
                while(playerPositions.contains(cpuPos) || (cpuPositions.contains(cpuPos))){
                    cpuPos = rand.nextInt(9) + 1;
                }
                placePiece(gameBoard, cpuPos, "cpu");
                printGameBoard(gameBoard);

                String result = checkWinner();
                System.out.println(result);
            }
        }

        //Iterates over each row and prints the row and ends line
        public static void printGameBoard(char [] [] gameBoard){
            for(char [] row : gameBoard){
                for(char c : row){
                    System.out.print(c);
                }
                System.out.println();
            }
        }

        public static void placePiece(char [] [] gameBoard, int pos, String user) {
            char symbol;
            if (user.equals("player")) {
                symbol = 'X';
                playerPositions.add(pos);
            } else {
                symbol = 'O';
                cpuPositions.add(pos);
            }
            switch (pos) {
                case 1:
                    gameBoard[0][0] = symbol;
                    break;
                case 2:
                    gameBoard[0][2] = symbol;
                    break;
                case 3:
                    gameBoard[0][4] = symbol;
                    break;
                case 4:
                    gameBoard[2][0] = symbol;
                    break;
                case 5:
                    gameBoard[2][2] = symbol;
                    break;
                case 6:
                    gameBoard[2][4] = symbol;
                    break;
                case 7:
                    gameBoard[4][0] = symbol;
                    break;
                case 8:
                    gameBoard[4][2] = symbol;
                    break;
                case 9:
                    gameBoard[4][4] = symbol;
                    break;
                default:
                    break;
            }
        }

        public static String checkWinner(){

            List topRow = Arrays.asList(1,2,3);
            List middleRow = Arrays.asList(4,5,6);
            List bottomRow = Arrays.asList(7,8,9);
            List firstCol = Arrays.asList(1,4,7);
            List secondCol = Arrays.asList(2,5,8);
            List thirdCol = Arrays.asList(3,6,9);
            List firstCross = Arrays.asList(1,5,9);
            List secondCross = Arrays.asList(3,5,7);

            List<List> winningConditions = new ArrayList<List>();
            winningConditions.add(topRow);
            winningConditions.add(middleRow);
            winningConditions.add(bottomRow);
            winningConditions.add(firstCol);
            winningConditions.add(secondCol);
            winningConditions.add(thirdCol);
            winningConditions.add(firstCross);
            winningConditions.add(secondCross);

            for(List l : winningConditions) {
                if (playerPositions.containsAll(l)) {
                    return "Congrats, You Won";
                }else if(cpuPositions.containsAll(l)){
                    return "Sorry, you lost.";
                }else if(playerPositions.size() + cpuPositions.size() == 9){
                    return "Its a draw!";
                }
            }
            return"";
        }
}

