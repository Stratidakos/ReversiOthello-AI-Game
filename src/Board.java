import java.util.ArrayList;

public class Board {

    private char [][] gameBoard;
    private int dimension;
    private Move lastMove;
    private char lastLetterPlayed;

    public Board(int dimension) //ok auto
    {
        this.lastMove = new Move();
        this.dimension = dimension;
        this.gameBoard = new char[this.dimension][this.dimension];
        lastLetterPlayed = 'O' ;
        //set starting position
        for(int i=0; i< this.dimension ; i++){
            for(int j=0; j< this.dimension; j++){
                this.gameBoard[i][j]= ' ';
                //System.out.println(i + " " + j);
            }
        }
        this.gameBoard[3][3] = 'O' ; //O is white
        this.gameBoard[3][4] = 'X' ; //X is black
        this.gameBoard[4][3] = 'X' ;
        this.gameBoard[4][4] = 'O' ;

        /* Testing the corners
        this.board[0][0]= 'T';
        this.board[0][7]= 'T';
        this.board[7][0]= 'T';
        this.board[7][7]= 'T';
        */
    }



    public Board(Board board)//ok auto
    {
        lastMove = board.lastMove;
        lastLetterPlayed = board.lastLetterPlayed;
        gameBoard= new char[8][8];
        for(int i=0; i< this.dimension ; i++){
            for(int j=0; j< this.dimension; j++){
                this.gameBoard[i][j]= board.gameBoard[i][j];
                //System.out.println(i + " " + j);
            }
        }
    }

    public Move getLastMove()//ok auto
    {
        return lastMove;
    }

    public char getLastLetterPlayed()//ok auto
    {
        return lastLetterPlayed;
    }

    public char[][] getGameBoard()//ok auto
    {
        return gameBoard;
    }

    public void setLastMove(Move lastMove)//ok auto
    {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }


    public void setLastLetterPlayed(char lastLetterPlayed)//ok auto
    {
        this.lastLetterPlayed = lastLetterPlayed;
    }


    public void setGameBoard(char[][] gameBoard)//ok auto
    {
        for(int i=0; i< this.dimension ; i++){
            for(int j=0; j< this.dimension; j++){
                this.gameBoard[i][j]= gameBoard[i][j];
                //System.out.println(i + " " + j);
            }
        }
    }

    public int getDimension() //ok auto
    {
        return dimension;
    }

    public void setDimension(int dimension)//ok auto
    {
        this.dimension = dimension;
    }



    public void print()// to prwto if na doume..
    {
        System.out.println("------------------------------------");
        System.out.println('\t' + "a " + '\t' + "b " + '\t' + "c " + '\t' + "d " + '\t' + "e " + '\t' + "f " + '\t' +"g " + '\t' + "h " + '\t');
        for(int i=0; i<this.dimension; i++)
        {
            int row=i+1;
            System.out.print(row + "\t");
            for(int j=0; j<this.dimension; j++)
            {
                if(this.gameBoard[i][j] == ' ')//karol - giati na einai iso me 0? char den einai auto?
                {
                    System.out.print(' ');
                }
                else
                {
                    System.out.print(this.gameBoard[i][j]);
                }
                if(j < this.dimension - 1)
                {
                    System.out.print( "|" +'\t');
                }
                else{
                    for(int k=0; k< this.dimension+1;k++) {
                        if(k==0){
                            System.out.println( " " + '\t');
                        }else {
                            System.out.print( '\t' + "-" );
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

    public void makeMove(int row, int col, char o) //ok auto
    {
        gameBoard[row][col] = o;
        lastMove = new Move(row, col);
        lastLetterPlayed = o;
        //twra 8a gurisoume ta poulia
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
        char c1=o;//o xarakthras pou paizei twra
        char c2;
        if (c1=='X')
        {
            c2='O';
        }
        else
        {
            c2='X';
        }
        int j;
        int i;
        // 1.psaxnw gia orizontia eu8eia me kateu8unsh aristera
        if (left)
        {
            j=col-1;
            while (this.gameBoard[row][j]==c2)
            {
                j--;
                if (j==-1)
                {
                    break;
                }
            }
            if (j!=-1 && j!=col-1) //ama den htan ola ta kelia aristera O (j!=-1) kai uparxei toulaxiston ena O (j!=col-1)
            {
                if (this.gameBoard[row][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    j++;
                    for (int l=j;l<col;l++)//allazw ta poulia
                    {
                        gameBoard[row][l]=c1;
                    }
                }
            }//allazw ta poulia

        }
        // 2.psaxnw gia diagwnio eu8eia me kateu8unsh panw aristera
        if (left && up)
        {
            j=col-1;
            i=row-1;
            while (this.gameBoard[i][j]==c2)
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
                if(this.gameBoard[i][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    j++;
                    i++;
                    for (int k=i;k<row;k++)//allazw ta poulia
                    {
                        gameBoard[k][j]=c1;
                        j++;
                    }
                }
            }
        }
        // 3.psaxnw gia diagwnio eu8eia me kateu8unsh katw aristera
        if (left && down)
        {
            j=col-1;
            i=row+1;
            while(this.gameBoard[i][j]==c2)
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
                if (this.gameBoard[i][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    j++;
                    i--;
                    for (int l=j;j<col;j++)//allazw ta poulia
                    {
                        gameBoard[i][l]=c1;
                        i--;
                    }
                }
            }
        }
        //4.psaxnw gia ka8eth eu8eia me kateu8unsh panw
        if (up)
        {
            i=row-1;
            while(this.gameBoard[i][col]==c2)
            {
                i--;
                if (i==-1)
                {
                    break;
                }
            }
            if (i!=-1 && i!=row-1)//ama den htan ola ta kelia panw O kai uparxei toulaxiston ena O (i!=row-1)
            {
                if(this.gameBoard[i][col]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    i++;
                    for (int k=i;k<row;k++)//allazw ta poulia
                    {
                        gameBoard[i][col]=c1;
                    }
                }
            }
        }
        //5.psaxnw gia ka8eth eu8eia me kateu8unsh katw
        if (down)
        {
            i=row+1;
            while(this.gameBoard[i][col]==c2)
            {
                i++;
                if (i==8)
                {
                    break;
                }
            }
            if(i!=8 && i!=row+1)//ama den htan ola ta kelia katw O kai uparxei toulaxiston ena O (i!=row+1)
            {
                if(this.gameBoard[i][col]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    i--;
                    for (int k=i;k>row;k--)//allazw ta poulia
                    {
                        gameBoard[k][col]=c1;
                    }

                }
            }
        }
        //6.psaxnw gia orizontia eu8eia me kateu8unsh deksia
        if (right)
        {
            j=col+1;
            while(this.gameBoard[row][j]==c2)
            {
                j++;
                if (j==8)
                {
                    break;
                }
            }
            if (j!=8 && j!=col+1)//ama den htan ola ta kelia deksia O kai uparxei toulaxiston ena O (j!=col+1)
            {
                if (this.gameBoard[row][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    j--;
                    for (int l=j;l>col;l--)//allazw ta poulia
                    {
                        gameBoard[row][l]=c1;
                    }
                }
            }
        }
        //7.psaxnw gia diagwnio eu8eia me kateu8unsh panw deksia
        if (right && up)
        {
            j=col+1;
            i=row-1;
            while(this.gameBoard[i][j]==c2)
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
                if(this.gameBoard[i][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    j--;
                    i++;
                    for (int k=i;k<row;k++)//allazw ta poulia
                    {
                        gameBoard[k][j]=c1;
                        j--;
                    }
                }
            }
        }
        // 8.psaxnw gia diagwnio eu8eia me kateu8unsh katw deksia
        if (right && down)
        {
            j=col+1;
            i=row+1;
            while(this.gameBoard[i][j]==c2)
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
                if(this.gameBoard[i][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    j--;
                    i--;
                    for (int k=i;k>row;k--)//allazw ta poulia
                    {
                        gameBoard[k][j]=c1;
                        j--;
                    }
                }
            }
        }

    }

    public ArrayList<Board> getChildren(char o){
        ArrayList<Board> children = new ArrayList<Board>();
        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                if(isValidMove(row, col)) {
                    Board child = new Board(this);
                    child.makeMove(row, col, o);
                    children.add(child);
                }
            }
        }
        return children;
    }

    public boolean isValidMove(int row, int col) //ok auto
    {
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
        if (lastLetterPlayed=='O')
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
            while (this.gameBoard[row][j]==c2)
            {
                j--;
                if (j==-1)
                {
                    break;
                }
            }
            if (j!=-1 && j!=col-1) //ama den htan ola ta kelia aristera O (j!=-1) kai uparxei toulaxiston ena O (j!=col-1)
            {
                if (this.gameBoard[row][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
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
            while (this.gameBoard[i][j]==c2)
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
                if(this.gameBoard[i][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
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
            while(this.gameBoard[i][j]==c2)
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
                if (this.gameBoard[i][j]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //4.psaxnw gia ka8eth eu8eia me kateu8unsh panw
        if (up)
        {
            i=row-1;
            while(this.gameBoard[i][col]==c2)
            {
                i--;
                if (i==-1)
                {
                    break;
                }
            }
            if (i!=-1 && i!=row-1)//ama den htan ola ta kelia panw O kai uparxei toulaxiston ena O (i!=row-1)
            {
                if(this.gameBoard[i][col]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //5.psaxnw gia ka8eth eu8eia me kateu8unsh katw
        if (down)
        {
            i=row+1;
            while(this.gameBoard[i][col]==c2)
            {
                i++;
                if (i==8)
                {
                    break;
                }
            }
            if(i!=8 && i!=row+1)//ama den htan ola ta kelia katw O kai uparxei toulaxiston ena O (i!=row+1)
            {
                if(this.gameBoard[i][col]==c1)// ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        //6.psaxnw gia orizontia eu8eia me kateu8unsh deksia
        if (right)
        {
            j=col+1;
            while(this.gameBoard[row][j]==c2)
            {
                j++;
                if (j==8)
                {
                    break;
                }
            }
            if (j!=8 && j!=col+1)//ama den htan ola ta kelia deksia O kai uparxei toulaxiston ena O (j!=col+1)
            {
                if (this.gameBoard[row][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
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
            while(this.gameBoard[i][j]==c2)
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
                if(this.gameBoard[i][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
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
            while(this.gameBoard[i][j]==c2)
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
                if(this.gameBoard[i][j]==c1)//ama to keli amesws meta to 1 h' perissotera O einai X - tote h kinhsh auth einai epitrepth gt uparxei epi8esh
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasMoves(char c)
    {
       boolean availableMove=false;//uparxei epitrepth kinhsh gia ton sygkekrimeno paixth?
       boolean differentChar=false;// h isValidMove elegxei gia ton anti8eto paixth tou lastletter, ara 8elw to lastletter na einai anti8eto tou c gia na kanei ton elegxo p 8elw
       if (getLastLetterPlayed()!=c)
       {
           differentChar=true;
       }
       else{
           if (c=='X')
           {
               setLastLetterPlayed('O');
           }else{
               setLastLetterPlayed('X');
           }

       }

       for (int i=0;i<dimension;i++)
       {
           for (int j=0;j<dimension;j++)
           {
               if(availableMove)
               {
                   break;
               }
               if(this.gameBoard[i][j]==' ')
               {
                   if (isValidMove(i,j))
                   {
                       availableMove=true;
                       System.out.println("Available Move: ("+i+","+j+")");
                       break;
                   }
               }
           }
       }
       if(!differentChar)
       {
           if (getLastLetterPlayed()=='X')
           {
               setLastLetterPlayed('O');
           }else{
               setLastLetterPlayed('X');
           }
       }
       return availableMove;
    }

    public boolean isTerminal()//ok auto
    {
        //periptwsh 1: terminal an o pinakas einai gematos
        //periptwsh 2:den uparxei epitrepth kinhsh gia kanenan apo tous 2 paixtes
        boolean complete=true;// einai gematos o pinakas?
        boolean noAvailableMoveP1=true;//den uparxei epitrepth kinhsh gia paixth1?
        boolean noAvailableMoveP2=true;//den uparxei epitrepth kinhsh gia paixth2?
        for (int i=0;i<dimension;i++)
        {
            for (int j=0;j<dimension;j++)
            {
                if (this.gameBoard[i][j]==' ')
                {
                    complete=false;
                    if (isValidMove(i,j))
                    {
                        noAvailableMoveP1=false;
                    }
                }

            }
        }
        if (lastLetterPlayed=='O') //elegxw an borei na paiksei o paixths2
        {
            setLastLetterPlayed('X');

        }
        else
        {
            setLastLetterPlayed('O');
        }
        for (int i=0;i<dimension;i++)
        {
            for (int j=0;j<dimension;j++)
            {
                if (this.gameBoard[i][j]==' ')
                {
                    if (isValidMove(i,j))//
                    {
                        noAvailableMoveP2=false;
                    }
                }

            }
        }
        if (lastLetterPlayed=='O') //ksana8etw ton paixth pou einai h seira t twra
        {
            setLastLetterPlayed('X');
        }
        else
        {
            setLastLetterPlayed('O');
        }

        if (complete || (noAvailableMoveP1 && noAvailableMoveP2)) //ama o pinakas einai gematos h' kanenas apo tous 2 paixtes den exei epitreptes kinhseis tote isTerminal=true
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int evaluate(){
        int value;
        int parityX=0;//plithos X
        int parityO=0;//Plithos O
        int cornersX=0;//poses gonies exei o X
        int cornersO=0;//poses gonies exei o O
        int sidelinesX=0;
        int sidelinesO=0;
        int presidelinesX=0;
        int presidelinesO=0;
        int midPresenceX=0;
        int midPresenceO=0;

        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                //pawn count
                if(gameBoard[row][col]=='X') {
                    parityX++;
                }else if(gameBoard[row][col]=='O'){
                    parityO++;
                }
                //corners count
                if(gameBoard[0][0]=='X'){
                    cornersX+=10;
                }else if(gameBoard[0][0]=='O'){
                    cornersO+=10;
                }
                if(gameBoard[0][7]=='X'){
                    cornersX+=10;
                }else if(gameBoard[0][7]=='O'){
                    cornersO+=10;
                }
                if(gameBoard[7][0]=='X'){
                    cornersX+=10;
                }else if(gameBoard[7][0]=='O'){
                    cornersO+=10;
                }
                if(gameBoard[7][7]=='X'){
                    cornersX+=10;
                }else if(gameBoard[7][7]=='O'){
                    cornersO+=10;
                }
                //sidelines count
                if(gameBoard[0][col]=='X'){
                    sidelinesX+=3;
                }else if(gameBoard[0][col]=='O'){
                    sidelinesO+=3;
                }
                if(gameBoard[row][0]=='X'){
                    sidelinesX+=3;
                }else if(gameBoard[row][0]=='O'){
                    sidelinesO+=3;
                }
                if(gameBoard[row][7]=='X'){
                    sidelinesX+=3;
                }else if(gameBoard[row][7]=='O'){
                    sidelinesO+=3;
                }
                if(gameBoard[7][col]=='X'){
                    sidelinesX+=3;
                }else if(gameBoard[7][col]=='O'){
                    sidelinesO+=3;
                }
                //presidelines count
                if(gameBoard[1][col]=='X' && col!=0 && col!=7){
                    presidelinesO+=3;
                }else if(gameBoard[1][col]=='O' && col!=0 && col!=7){
                    presidelinesX+=3;
                }
                if(gameBoard[row][1]=='X' && row!=0 && row!=7){
                    presidelinesO+=3;
                }else if(gameBoard[1][col]=='O' && row!=0 && row!=7){
                    presidelinesX+=3;
                }
                if(gameBoard[row][6]=='X' && row!=0 && row!=7){
                    presidelinesO+=3;
                }else if(gameBoard[row][6]=='O' && row!=0 && row!=7){
                    presidelinesX+=3;
                }
                if(gameBoard[6][col]=='X' && col!=0 && col!=7){
                    presidelinesO+=3;
                }else if(gameBoard[6][col]=='O' && col!=0 && col!=7){
                    presidelinesX+=3;
                }
                //count pawns that occupy the mid of the board
                if(gameBoard[row][col]=='X' && (row>1 && row<6) && (col>1 && col<6)){
                    midPresenceX++;
                }else if(gameBoard[row][col]=='O' && (row>1 && row<6) && (col>1 && col<6)){
                    midPresenceO++;
                }
            }
        }

        int P=25*(parityX-parityO);
        int C=30*(cornersX-cornersO);
        int S=20*(sidelinesX-sidelinesO);
        int preS=10*(presidelinesX-presidelinesO);
        int M=15*(midPresenceX-midPresenceO);

        value= (P)+(C)+(S)+(preS)+(M);
        return value;
    }
}
