import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Get user input
        int maxDepth;
        String firstTurn;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter max depth: ");
        maxDepth= input.nextInt();
        Scanner input2 = new Scanner(System.in);
        System.out.println("Do you want to play first? Y or N");
        firstTurn= input2.nextLine();
        //Print to check the values
        System.out.println("Depth: " + maxDepth + " First: " + firstTurn);

        //Create the starting position
        Board gameBoard = new Board(8);
        gameBoard.print();
        GamePlayer Human;
        GamePlayer Computer;

        // NOTE 1
        if(firstTurn.equals("Y")){
            Human = new GamePlayer(maxDepth, 'X');
            Computer = new GamePlayer(maxDepth, 'O');

        }else{
            Computer = new GamePlayer(maxDepth, 'X');
            Human = new GamePlayer(maxDepth, 'O');
        }
        String move;
        Scanner input3 = new Scanner(System.in);
        //Game begins
        System.out.println("Human letter: "+Human.getPlayerLetter());
        if(Human.getPlayerLetter()=='X') {

            while (!gameBoard.isTerminal()) {
                System.out.println();
                switch (gameBoard.getLastLetterPlayed()) {
                    //If X played last, then O plays now
                    case 'X':
                        //------------------na to doume auto an einai swsto otan dinei thn seira
                        if (gameBoard.hasMoves('O'))//elegxos an uparxei kinhsh na kanei alliws prepei na dwsei thn seira t
                        {
                            System.out.println("O moves");
                            Move OMove = Computer.MiniMax(gameBoard);
                            if (gameBoard.isValidMove(OMove.getRow(), OMove.getCol()))
                            {
                                gameBoard.makeMove(OMove.getRow(), OMove.getCol(), 'O');
                            }

                        }
                        else{
                            gameBoard.setLastMove(new Move());
                            gameBoard.setLastLetterPlayed('O');
                            System.out.println("O has no available moves.");
                        }
                        break;
                        //------------------------------------------------------------------------------------------------------
                        /*System.out.println("O moves");
                        Move OMove = Computer.MiniMax(gameBoard);
                        gameBoard.makeMove(OMove.getRow(), OMove.getCol(), 'O');
                        break;*/
                    //If O played last, then X plays now
                    case 'O':
                        //-------------------------------------------------------------------------------------------------------------
                        if (gameBoard.hasMoves('X'))//elegxos an uparxei kinhsh na kanei alliws prepei na dwsei thn seira t
                        {
                            System.out.println("X moves. Type your move (rowcolumn): ");
                            move = input3.next();
                            boolean playableMove=false;
                            int chessRow=0;
                            int chessColumn=0;

                            while (!playableMove)
                            {
                                while(move.length()!=2)
                                {
                                    System.out.println("Wrong input. Please try again.");
                                    System.out.println("Type your move (row column): ");
                                    move = input3.next();
                                }
                                char chessColumnLetter = move.charAt(1);
                                char chessRowLetter = move.charAt(0);
                                chessRow=Character.getNumericValue(chessRowLetter);
                                chessRow--;
                                chessColumn = convertLetterToNumber(chessColumnLetter);
                                System.out.println("Row: "+chessRow+ " Column: "+chessColumn);
                                playableMove = checkMove(chessRow , chessColumn , gameBoard.getGameBoard(),gameBoard.getLastLetterPlayed());
                                if (!playableMove) {
                                    System.out.println("Not a valid move. Please try again.");
                                    System.out.println("Type your move (row column): ");
                                    move = input3.next();
                                }
                            }
                            if (gameBoard.isValidMove(chessRow, chessColumn))
                            {
                                gameBoard.makeMove(chessRow, chessColumn, 'X');
                            }


                            break;
                        }
                        else{
                            gameBoard.setLastMove(new Move());
                            gameBoard.setLastLetterPlayed('X');
                            System.out.println("X has no available moves.");
                        }
                        break;
                        //----------------------------------------------------------------------------------------------------------
                        /*
                        while(move.length()!=3 && !playableMove)
                        {
                            if (move.length()!=3)
                            {
                                System.out.println("Wrong input. Please try again.");
                                System.out.println("Type your move (row column): ");
                                move = input3.next();

                            }
                            char chessColumnLetter = move.charAt(2);
                            char chessRowLetter = move.charAt(0);
                            chessRow=(int)chessRowLetter;
                            chessRow--;
                            chessColumn = convertLetterToNumber(chessColumnLetter);
                            playableMove = checkMove(chessRow , chessColumn , gameBoard.getGameBoard(),gameBoard.getLastLetterPlayed());

                            if (playableMove) {
                                gameBoard.makeMove(chessRow, chessColumn, 'X');
                            } else {
                                System.out.println("Not a valid move. Please try again.");
                                System.out.println("Type your move (row column): ");
                                move = input3.next();
                            }
                        }*/

                    default:
                        break;
                }

                gameBoard.print();
            }
        }else{
            while (!gameBoard.isTerminal()) {
                System.out.println();
                switch (gameBoard.getLastLetterPlayed()) {
                    //If X played last, then 0 plays now
                    case 'X':
                        if(gameBoard.hasMoves('O'))//elegxos an uparxei kinhsh na kanei alliws prepei na dwsei thn seira t
                        {
                            System.out.println("O moves. Type your move (rowcolumn): ");
                            move = input3.next();
                            boolean playableMove=false;
                            int chessRow=0;
                            int chessColumn=0;

                            while (!playableMove)
                            {
                                while(move.length()!=2)
                                {
                                    System.out.println("Wrong input. Please try again.");
                                    System.out.println("Type your move (row column): ");
                                    move = input3.next();
                                }
                                char chessColumnLetter = move.charAt(1);
                                char chessRowLetter = move.charAt(0);
                                chessRow=Character.getNumericValue(chessRowLetter);
                                chessRow--;
                                chessColumn = convertLetterToNumber(chessColumnLetter);
                                System.out.println("Row: "+chessRow+ " Column: "+chessColumn);
                                playableMove = checkMove(chessRow , chessColumn , gameBoard.getGameBoard(),gameBoard.getLastLetterPlayed());
                                if (!playableMove) {
                                    System.out.println("Not a valid move. Please try again.");
                                    System.out.println("Type your move (row column): ");
                                    move = input3.next();
                                }
                            }
                            if (gameBoard.isValidMove(chessRow, chessColumn))
                            {
                                gameBoard.makeMove(chessRow, chessColumn, 'O');
                            }

                        }else{
                            gameBoard.setLastMove(new Move());
                            gameBoard.setLastLetterPlayed('O');
                            System.out.println("O has no available moves.");
                        }
                        break;
                        /*while(move.length()!=3 && !playableMove)
                        {
                            if (move.length()!=3)
                            {
                                System.out.println("Wrong input. Please try again.");
                                System.out.println("Type your move (row column): ");
                                move = input3.next();

                            }

                            char chessColumnLetter = move.charAt(2);
                            char chessRowLetter = move.charAt(0);
                            int chessRow=(int)chessRowLetter;
                            chessRow--;
                            int chessColumn = convertLetterToNumber(chessColumnLetter);
                            playableMove = checkMove(chessRow , chessColumn , gameBoard.getGameBoard(),gameBoard.getLastLetterPlayed());

                            if (playableMove) {
                                gameBoard.makeMove(chessRow, chessColumn, 'O');
                            } else {
                                System.out.println("Not a valid move. Please try again.");
                                System.out.println("Type your move (row column): ");
                                move = input3.next();
                            }
                        }*/

                    //If O played last, then X plays now
                    case 'O':
                        if(gameBoard.hasMoves('X')) //elegxos an uparxei kinhsh na kanei alliws prepei na dwsei thn seira t
                        {
                            System.out.println("X moves");
                            Move XMove = Computer.MiniMax(gameBoard);
                            System.out.println("Row: "+XMove.getRow()+" Col: "+XMove.getCol());
                            if (gameBoard.isValidMove(XMove.getRow(), XMove.getCol()))
                            {
                                gameBoard.makeMove(XMove.getRow(), XMove.getCol(), 'X');
                            }

                        }else{
                            gameBoard.setLastMove(new Move());
                            gameBoard.setLastLetterPlayed('X');
                            System.out.println("X has no available moves.");
                        }
                        /*
                        System.out.println("X moves");
                        Move XMove = Computer.MiniMax(gameBoard);
                        gameBoard.makeMove(XMove.getRow(), XMove.getCol(), 'X');*/
                        break;
                    default:
                        break;
                }
                gameBoard.print();
            }
        }
        //Print results
        int countX=0;
        int countO=0;
        char [][] gb=gameBoard.getGameBoard();
        for (int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if (gb[i][j]=='X')
                {
                    countX++;
                }
                if (gb[i][j]=='O')
                {
                    countO++;
                }
            }
        }
        if (countX>countO)
        {
            System.out.println("Player X won with "+countX+" points\nPlayer O lost with "+countO+" points.");
        }else if (countX==countO)
        {
            System.out.println("Game ended in a tie.");
        }else
        {
            System.out.println("Player O won with "+countO+" points\nPlayer X lost with "+countX+" points.");
        }

        input.close();
        input2.close();
        input3.close();
    }

    private static int convertLetterToNumber(char chessColumnLetter){
        int chessColumn=0;
        switch(chessColumnLetter){
            case 'a':
                chessColumn=0;
                break;
            case 'b':
                chessColumn=1;
                break;
            case 'c':
                chessColumn=2;
                break;
            case 'd':
                chessColumn=3;
                break;
            case 'e':
                chessColumn=4;
                break;
            case 'f':
                chessColumn=5;
                break;
            case 'g':
                chessColumn=6;
                break;
            case 'h':
                chessColumn=7;
                break;
        }
        return chessColumn;
    }

    private static boolean checkMove(int row, int col, char[][] gameBoard, char letter)
    {
        System.out.println("Checking ("+row+","+col+")");

        if ((row < 0) || (col < 0) || (row > 7) || (col > 7))// periptwsh lan8asmenwn diastasewn
        {
            return false;
        }
        if (gameBoard[row][col]!=' ') //ama to keli den einai adeio
        {
            return false;
        }
        boolean right=true; //vriskw epitreptes metakinhseis se periptwsh akrianwn 8esewn
        boolean left=true;
        boolean up=true;
        boolean down=true;
        if (row==0)
        {
            up=false;
        }
        if (row==7)
        {
            down=false;
        }
        if (col==0)
        {
            left=false;
        }
        if(col==7)
        {
            right=false;
        }
        char c1;//o xarakthras pou paizei twra
        char c2;

        if (letter=='O')
        {
            c1='X';
            c2='O';
        }
        else
        {
            c1='O';
            c2='X';
        }
        //arkei na borei na epite8ei se mia apo oles tis kateu8unseis
        //koitazw gia to shmeio auto na vrw to anti8eto pouli sta 8 geitonika kelia (pi8anes epi8eseis)
        int j;
        int i;
        // 1.psaxnw gia orizontia eu8eia me kateu8unsh aristera
        if (left)
        {
            j=col-1;
            while (gameBoard[row][j]==c2)
            {
                j--;
                if (j==-1)
                {
                    break;
                }
            }
            if (j!=-1 && j!=col-1) //ama den htan ola ta kelia aristera O (j!=-1) kai uparxei toulaxiston ena O (j!=col-1)
            {
                if (gameBoard[row][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }

        }
        // 2.psaxnw gia diagwnio eu8eia me kateu8unsh panw aristera
        if (left && up)
        {
            j=col-1;
            i=row-1;
            while (gameBoard[i][j]==c2)
            {
                j--;
                i--;
                if (j==-1 || i==-1)
                {
                    break;
                }
            }
            if (!(j==-1 || i==-1) && j!=col-1)//ama den htan ola ta kelia panw aristera O kai uparxei toulaxiston ena O (j!=col-1)
            {
                if(gameBoard[i][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        // 3.psaxnw gia diagwnio eu8eia me kateu8unsh katw aristera
        if (left && down)
        {
            j=col-1;
            i=row+1;
            while(gameBoard[i][j]==c2)
            {
                j--;
                i++;
                if (j==-1 || i==8)
                {
                    break;
                }
            }
            if (!(j==-1 || i==8) && j!=col-1)//ama den htan ola ta kelia katw aristera O kai uparxei toulaxiston ena O (j!=col-1)
            {
                if (gameBoard[i][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //4.psaxnw gia ka8eth eu8eia me kateu8unsh panw
        if (up)
        {
            i=row-1;
            while(gameBoard[i][col]==c2)
            {
                i--;
                if (i==-1)
                {
                    break;
                }
            }
            if (i!=-1 && i!=row-1)//ama den htan ola ta kelia panw O kai uparxei toulaxiston ena O (i!=row-1)
            {
                if(gameBoard[i][col]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //5.psaxnw gia ka8eth eu8eia me kateu8unsh katw
        if (down)
        {
            i=row+1;
            while(gameBoard[i][col]==c2)
            {
                i++;
                if (i==8)
                {
                    break;
                }
            }
            if(i!=8 && i!=row+1)//ama den htan ola ta kelia katw O kai uparxei toulaxiston ena O (i!=row+1)
            {
                if(gameBoard[i][col]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //6.psaxnw gia orizontia eu8eia me kateu8unsh deksia
        if (right)
        {
            j=col+1;
            while(gameBoard[row][j]==c2)
            {
                j++;
                if (j==8)
                {
                    break;
                }
            }
            if (j!=8 && j!=col+1)//ama den htan ola ta kelia deksia O kai uparxei toulaxiston ena O (j!=col+1)
            {
                if (gameBoard[row][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //7.psaxnw gia diagwnio eu8eia me kateu8unsh panw deksia
        if (right && up)
        {
            j=col+1;
            i=row-1;
            while(gameBoard[i][j]==c2)
            {
                j++;
                i--;
                if(j==8 || i==-1)
                {
                    break;
                }
            }
            if (!(j==8 || i==-1) && j!=col+1)//ama den htan ola ta kelia panw deksia O kai uparxei toulaxiston ena O (j!=col+1)
            {
                if(gameBoard[i][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        // 8.psaxnw gia diagwnio eu8eia me kateu8unsh katw deksia
        if (right && down)
        {
            j=col+1;
            i=row+1;
            while(gameBoard[i][j]==c2)
            {
                j++;
                i++;
                if(j==8 || i==8)
                {
                    break;
                }
            }
            if (!(j==8 || i==8) && j!=col+1)//ama den htan ola ta kelia katw deksia O kai uparxei toulaxiston ena O (j!=col+1)
            {
                if(gameBoard[i][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        return false;
    }
}
