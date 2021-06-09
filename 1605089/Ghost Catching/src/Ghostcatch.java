import java.util.*;

import static java.lang.System.exit;


public class Ghostcatch {
    double[][] board;
    int rowSize;
    int colSize;
    int ghostx;
    int ghosty;
    double corner1;
    double corner2;
    double general1;
    double general2;
    double border1;
    double border2;
    int attempts;
    HashMap<Coords, List<Double>> probabilityMap = new HashMap<Coords, List<Double>>();

    Ghostcatch(int n, int m){
         this.board = new double[n][m];
         this.colSize = m;
         this.rowSize = n;
         this.attempts = 0;
         corner1 = 0.48;
         corner2 = 0.02;
         border1 = 0.96/3.0;
         border2 = 0.04/3.0;
         general1 = 0.24;
         general2 = 0.008;
         for(int i =0;i<n;i++){
             for(int j =0;j<m;j++){
                 this.board[i][j] = (double) 1/(n*m);
                 if(i==0 && j ==0){
                     probabilityMap.put(new Coords(0,0), new ArrayList<Double>(Arrays.asList(0.0, corner1, corner1, 0.0, 0.0, corner2, 0.0,0.0, corner2)));
                 }

                 else if(i == 0 && j ==m-1){
                     probabilityMap.put(new Coords(0,m-1), new ArrayList<Double>(Arrays.asList(0.0,corner1,0.0, corner1,0.0, 0.0,corner2,0.0,corner2)));

                 }

                 else if(i == n-1 && j==0){
                     probabilityMap.put(new Coords(n-1,0), new ArrayList<Double>(Arrays.asList(corner1,0.0,corner1,0.0,corner2,0.0,0.0,0.0,corner2)));
                 }
                 else if(i == n-1 && j == m-1){
                     probabilityMap.put(new Coords(n-1,m-1), new ArrayList<Double>(Arrays.asList(corner1, 0.0, 0.0, corner1, 0.0, 0.0,0.0, corner2, corner2)));
                 }

                 else if((i>0 && i< n-1) && j==0){
                     probabilityMap.put(new Coords(i,0), new ArrayList<Double>(Arrays.asList(border1, border1, border1, 0.0, border2, border2, 0.0,0.0, border2)));
                 }

                 else if((i>0 && i< n-1) && j==m-1){
                     probabilityMap.put(new Coords(i,m-1), new ArrayList<Double>(Arrays.asList(border1, border1, 0.0,border1, 0.0, 0.0, border2 ,border2, border2)));
                 }

                 else if( i==0 && (j>0 && j< m-1) ){
                     probabilityMap.put(new Coords(0,j), new ArrayList<Double>(Arrays.asList(0.0, border1,border1,border1, 0.0, border2 , border2 ,0.0, border2)));
                 }

                 else if( i==n-1 && (j>0 && j< m-1) ){
                     probabilityMap.put(new Coords(n-1,j), new ArrayList<Double>(Arrays.asList(border1, 0.0,border1,border1, border2, 0.0 , 0.0 ,border2, border2)));
                 }
                 else {
                     probabilityMap.put(new Coords(i,j), new ArrayList<Double>(Arrays.asList(general1,general1,general1,general1,general2,general2,general2,general2,general2)));
                 }


             }
         }
         ghostx = (n-1)/2;
         ghosty = (m-1)/2;
    }

    void setBoard(int i, int j, double value)
    {
        this.board[i][j] = value;
    }

    double getBoard(int i, int j){
        return this.board[i][j];
    }

    void printBoard(){
        for(int i =0;i<rowSize;i++){
            for(int j =0;j<colSize;j++){
                System.out.printf("%.3f",this.getBoard(i,j));
                System.out.print(" ");
            }
            System.out.println();
        }
//        System.out.println("Printing static probabilities\n");
//        for(int i =0;i<rowSize;i++){
//            for(int j =0;j<colSize;j++){
//                System.out.print(probabilityMap.get(new Coords(i,j)));
//            }
//            System.out.println();
//        }

       // System.out.println("ghost tumi koi: " +this.getGhostx()+ ","+ this.getGhosty());
    }

    void setGhostx(int newx){
        ghostx = newx;
    }

    int getGhostx(){
        return ghostx;
    }

    void setGhosty(int newy){
        ghosty = newy;
    }

    int getGhosty(){
        return ghosty;
    }

    void updateAdvanceTime(){
        double[][] temp = new double [rowSize][colSize];


        for(int i =0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                temp[i][j] = this.getBoard(i,j);
            }
        }

        /* Updating probabilities */
        for(int i = 0; i<rowSize;i++){
            for(int j = 0; j< colSize;j++){

                if(i ==0 && j ==0){
                    double newValue = temp[0][1]*probabilityMap.get(new Coords(0,1)).get(3) + temp[1][0] * probabilityMap.get(new Coords(1,0)).get(0)+
                            temp[1][1]*probabilityMap.get(new Coords(1,1)).get(7)+ temp[0][0]*probabilityMap.get(new Coords(0,0)).get(8);

                    this.setBoard(0,0,newValue);

                }

                else if (i == 0 && j == colSize -1){
                    double newValue = temp [0][colSize-2] * probabilityMap.get(new Coords(0,colSize-2)).get(2)+
                            temp[1][colSize-1]* probabilityMap.get(new Coords(1,colSize-1)).get(0)+
                            temp[1][colSize-2]*probabilityMap.get(new Coords(1,colSize-2)).get(4)+
                            temp[0][colSize-1]*probabilityMap.get(new Coords(0,colSize-1)).get(8);

                    this.setBoard(0,colSize-1, newValue);
                }

                else if (i == rowSize-1 && j == 0){

                    double newValue = temp [rowSize-1][1] * probabilityMap.get(new Coords(rowSize-1,1)).get(3)+
                            temp[rowSize-2][0]* probabilityMap.get(new Coords(rowSize-2,0)).get(1)+
                            temp[rowSize-2][1]*probabilityMap.get(new Coords(rowSize-2,1)).get(6)+
                            temp[rowSize-1][0]*probabilityMap.get(new Coords(rowSize-1,0)).get(8);

                    this.setBoard(rowSize-1,0, newValue);

                }

                else if(i == rowSize-1 && j== colSize -1){
                    double newValue = temp [rowSize-1][colSize-2] * probabilityMap.get(new Coords(rowSize-1,colSize-2)).get(2)+
                            temp[rowSize-2][colSize-1]* probabilityMap.get(new Coords(rowSize-2,colSize-1)).get(1)+
                            temp[rowSize-2][colSize-2]*probabilityMap.get(new Coords(rowSize-2,colSize-2)).get(5)+
                            temp[rowSize-1][colSize-1]*probabilityMap.get(new Coords(rowSize-1,colSize-1)).get(8);

                    this.setBoard(rowSize-1,colSize-1, newValue);
                }

                else if((i>0 && i<rowSize-1) && j == 0){

                    double newValue = temp [i-1][0] * probabilityMap.get(new Coords(i-1,0)).get(1)+
                            temp[i+1][0]* probabilityMap.get(new Coords(i+1,0)).get(0)+
                            temp[i][1]*probabilityMap.get(new Coords(i,1)).get(3)+
                            temp[i-1][1]*probabilityMap.get(new Coords(i-1,1)).get(6)+
                            temp[i+1][1]*probabilityMap.get(new Coords(i+1,1)).get(7)+
                            temp[i][0]*probabilityMap.get(new Coords(i,0)).get(8);


                    this.setBoard(i,0, newValue);
                }

                else if((i>0 && i<rowSize-1) && j == colSize-1){

                    double newValue = temp [i-1][colSize-1] * probabilityMap.get(new Coords(i-1,colSize-1)).get(1)+
                            temp[i+1][colSize-1]* probabilityMap.get(new Coords(i+1,colSize-1)).get(0)+
                            temp[i][colSize-2]*probabilityMap.get(new Coords(i,colSize-2)).get(2)+
                            temp[i-1][colSize-2]*probabilityMap.get(new Coords(i-1,colSize-2)).get(5)+
                            temp[i+1][colSize-2]*probabilityMap.get(new Coords(i+1,colSize-2)).get(4)+
                            temp[i][colSize-1]*probabilityMap.get(new Coords(i,colSize-1)).get(8);


                    this.setBoard(i,colSize-1, newValue);


                }

                else if (i == 0 && (j>0 && j< colSize-1)){

                    double newValue = temp [1][j] * probabilityMap.get(new Coords(1,j)).get(0)+
                            temp[0][j+1]* probabilityMap.get(new Coords(0,j+1)).get(3)+
                            temp[0][j-1]*probabilityMap.get(new Coords(0,j-1)).get(2)+
                            temp[1][j-1]*probabilityMap.get(new Coords(1,j-1)).get(4)+
                            temp[1][j+1]*probabilityMap.get(new Coords(1,j+1)).get(7)+
                            temp[0][j]*probabilityMap.get(new Coords(0,j)).get(8);


                    this.setBoard(0,j, newValue);
                }

                else if(i == rowSize-1 && (j>0 && j< colSize-1)){
                    double newValue = temp [rowSize-2][j] * probabilityMap.get(new Coords(rowSize-2,j)).get(1)+
                            temp[rowSize-1][j+1]* probabilityMap.get(new Coords(rowSize-1,j+1)).get(3)+
                            temp[rowSize-1][j-1]*probabilityMap.get(new Coords(rowSize-1,j-1)).get(2)+
                            temp[rowSize-2][j-1]*probabilityMap.get(new Coords(rowSize-2,j-1)).get(5)+
                            temp[rowSize-2][j+1]*probabilityMap.get(new Coords(rowSize-2,j+1)).get(6)+
                            temp[rowSize-1][j]*probabilityMap.get(new Coords(rowSize-1,j)).get(8);


                    this.setBoard(rowSize-1,j, newValue);
                }

                else {
                    double newValue = temp [i-1][j] * probabilityMap.get(new Coords(i-1,j)).get(1)+
                            temp[i+1][j]* probabilityMap.get(new Coords(i+1,j)).get(0)+
                            temp[i][j-1]*probabilityMap.get(new Coords(i,j-1)).get(2)+
                            temp[i][j+1]*probabilityMap.get(new Coords(i,j+1)).get(3)+
                            temp[i-1][j+1]*probabilityMap.get(new Coords(i-1,j+1)).get(6)+
                            temp[i-1][j-1]*probabilityMap.get(new Coords(i-1,j-1)).get(5)+
                            temp[i+1][j-1]*probabilityMap.get(new Coords(i+1, j-1)).get(4)+
                            temp[i+1][j+1]*probabilityMap.get(new Coords(i+1,j+1)).get(7)+
                            temp[i][j]*probabilityMap.get(new Coords(i,j)).get(8);



                    this.setBoard(i,j, newValue);
                }



            }
        }

        /* normalizing */
        double sum = 0.0;
        for (int i = 0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                sum  = sum + this.getBoard(i,j);
            }
        }

        for (int i = 0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                double v = (double) this.getBoard(i,j) / sum;
                this.setBoard(i,j,v);
            }
        }
        /* normalizing finished*/

//        System.out.println("final summation is: ");
//        double sum2 = 0.0;
//        for (int i = 0;i<rowSize;i++){
//            for(int j = 0;j<colSize;j++){
//                sum2 = sum2 + this.getBoard(i,j);
//            }
//        }
//        System.out.println(sum2);
   /* ghost movement starts here */

        Random rand = new Random();
        double moveProbability = rand.nextDouble();

        if(this.getGhostx() ==0 && this.getGhosty() ==0){

            if(moveProbability>=0 && moveProbability< corner1){
                this.setGhostx(1);
                this.setGhosty(0);
                //move down
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){
                this.setGhostx(0);
                this.setGhosty(1);
                //move right
            }
            else if(moveProbability>=2*corner1 && moveProbability< (2*corner1+corner2)) {
                this.setGhostx(1);
                this.setGhosty(1);
                //move diag

            }
            else {
                this.setGhostx(0);
                this.setGhosty(0);
                //self
            }

        }

        else if (this.getGhostx() == 0 && this.getGhosty() == colSize -1){


            if(moveProbability>=0 && moveProbability< corner1){
                this.setGhostx(1);
                this.setGhosty(colSize-1);
                //move down
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){
                this.setGhostx(0);
                this.setGhosty(colSize-2);
                //move left
            }
            else if(moveProbability>= 2*corner1 && moveProbability< (2*corner1+corner2)) {
                this.setGhostx(1);
                this.setGhosty(colSize-2);
                //move diag

            }
            else {
                this.setGhostx(0);
                this.setGhosty(colSize-1);
                //self
            }

        }

        else if (this.getGhostx() == rowSize-1 && this.getGhosty() == 0){


            if(moveProbability>=0 && moveProbability< corner1){
                this.setGhostx(rowSize-2);
                this.setGhosty(0);
                //move up
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){
                this.setGhostx(rowSize-1);
                this.setGhosty(1);
                //move right
            }
            else if(moveProbability>= 2*corner1 && moveProbability< (2*corner1+corner2)) {
                this.setGhostx(rowSize-2);
                this.setGhosty(1);
                //move diag

            }
            else {
                this.setGhostx(rowSize-1);
                this.setGhosty(0);
                //self
            }


        }

        else if(this.getGhostx() == rowSize-1 && this.getGhosty() == colSize -1){


            if(moveProbability>=0 && moveProbability< corner1){
                this.setGhostx(rowSize-2);
                this.setGhosty(colSize-1);
                //move up
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){
                this.setGhostx(rowSize-1);
                this.setGhosty(colSize-2);
                //move left
            }
            else if(moveProbability>= 2*corner1 && moveProbability< (2*corner1+corner2)){
                this.setGhostx(rowSize-2);
                this.setGhosty(colSize-2);
                //move diag

            }
            else {
                this.setGhostx(rowSize-1);
                this.setGhosty(colSize-1);
                //self
            }

        }

        else if((this.getGhostx()>0 && this.getGhostx()<rowSize-1) && this.getGhosty() == 0){

            if(moveProbability>=0 && moveProbability< border1){
                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(0);
                //move up
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){
                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(0);
                //move down
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){
                this.setGhostx(this.getGhostx());
                this.setGhosty(1);
                //move right
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){
                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){
                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(1);
                //move diag2
            }
            else {
                this.setGhostx(this.getGhostx());
                this.setGhosty(0);
                //self
            }


        }

        else if((this.getGhostx()>0 && this.getGhostx()<rowSize-1) && this.getGhosty() == colSize-1){

            if(moveProbability>=0 && moveProbability< border1){
                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(colSize-1);
                //move up
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){
                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(colSize-1);
                //move down
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){
                this.setGhostx(this.getGhostx());
                this.setGhosty(colSize-2);
                //move left
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){
                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(colSize-2);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){
                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(colSize-2);
                //move diag2
            }
            else {
                this.setGhostx(this.getGhostx());
                this.setGhosty(colSize-1);
                //self
            }


        }

        else if (this.getGhostx() == 0 && (this.getGhosty()>0 && this.getGhosty()< colSize-1)){

            if(moveProbability>=0 && moveProbability< border1){
                this.setGhostx(1);
                this.setGhosty(this.getGhosty());
                //move down
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){
                this.setGhostx(0);
                this.setGhosty(this.getGhosty()+1);
                //move right
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){
                this.setGhostx(0);
                this.setGhosty(this.getGhosty()-1);
                //move left
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){
                this.setGhostx(1);
                this.setGhosty(this.getGhosty()+1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){
                this.setGhostx(1);
                this.setGhosty(this.getGhosty()-1);
                //move diag2
            }
            else {
                this.setGhostx(0);
                this.setGhosty(this.getGhosty());
                //self
            }

        }

        else if(this.getGhostx() == rowSize-1 && (this.getGhosty()>0 && this.getGhosty()< colSize-1)){

            if(moveProbability>=0 && moveProbability< border1){
                this.setGhostx(rowSize-2);
                this.setGhosty(this.getGhosty());
                //move up
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){
                this.setGhostx(rowSize-1);
                this.setGhosty(this.getGhosty()+1);
                //move right
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){
                this.setGhostx(rowSize-1);
                this.setGhosty(this.getGhosty()-1);
                //move left
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){
                this.setGhostx(rowSize-2);
                this.setGhosty(this.getGhosty()+1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){
                this.setGhostx(rowSize-2);
                this.setGhosty(this.getGhosty()-1);
                //move diag2
            }
            else {
                this.setGhostx(rowSize-1);
                this.setGhosty(this.getGhosty());
                //self
            }

        }

        else {

            if(moveProbability>=0 && moveProbability < general1){

                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(this.getGhosty());
                //move up
            }

            else if(moveProbability>= general1 && moveProbability< 2*general1){

                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(this.getGhosty());
                //move down
            }

            else if(moveProbability>= 2*general1 && moveProbability< 3*general1){

                this.setGhostx(this.getGhostx());
                this.setGhosty(this.getGhosty()+1);
                //move right
            }
            else if(moveProbability>= 3*general1 && moveProbability< 4*general1){

                this.setGhostx(this.getGhostx());
                this.setGhosty(this.getGhosty()-1);
                //move left
            }
            else if(moveProbability>= 4*general1 && moveProbability< (4*general1+general2))
            {
                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(this.getGhosty()+1);
                //move diag1
            }
            else if(moveProbability>= (4*general1+general2) && moveProbability< (4*general1+2*general2))
            {
                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(this.getGhosty()+1);
                //move diag2
            }
            else if(moveProbability>= (4*general1+2*general2) && moveProbability< (4*general1+3*general2))
            {
                this.setGhostx(this.getGhostx()+1);
                this.setGhosty(this.getGhosty()-1);
                //move diag3
            }
            else if(moveProbability> (4*general1+3*general2) && moveProbability<= (4*general1+4*general2))
            {
                this.setGhostx(this.getGhostx()-1);
                this.setGhosty(this.getGhosty()-1);
                //move diag4
            }
            else {

                this.setGhostx(this.getGhostx());
                this.setGhosty(this.getGhosty());
                //self
            }


        }




    }

    void updateColorProb(int r, int c){

        String colorfound = "";
        double distance = Math.abs(this.getGhostx()-r) + Math.abs(this.getGhosty()-c);
        double colorProbability = (distance/ (rowSize-1 + colSize-1)) * 100;

        if(colorProbability>=0.0 && colorProbability< (100.0/4.5)){
            colorfound = "red";

        }

        else if(colorProbability>= (100.0/4.50) && colorProbability < (300.0/4.50)){
            colorfound = "orange";
        }
        else {
            colorfound = "green";
        }
        System.out.println("The sensored color is: " + colorfound);

        /* updating board probabilities again */
        for(int i = 0; i<rowSize ; i++){
            for(int j = 0; j<colSize; j++){
                String tempColorFound;
                double d = Math.abs(i-r) + Math.abs(j-c);
                double cProbability = (d/ (rowSize-1 + colSize-1)) * 100;

                if(cProbability>=0.0 && cProbability< (100.0/4.5)){
                    tempColorFound = "red";

                }

                else if(cProbability>= (100.0/4.50) && cProbability < (300.0/4.50)){
                    tempColorFound = "orange";
                }
                else {
                    tempColorFound= "green";
                }

                if(tempColorFound.equalsIgnoreCase(colorfound)){

                    this.setBoard(i,j,this.getBoard(i,j)*0.99);
                }
                else {
                    this.setBoard(i,j,this.getBoard(i,j)*0.01);
                }


            }
        }

        /* normalizing */
        double sum = 0.0;
        for (int i = 0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                sum  = sum + this.getBoard(i,j);
            }
        }

        for (int i = 0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                double v = (double) this.getBoard(i,j) / sum;
                this.setBoard(i,j,v);
            }
        }
        /* normalizing finished*/

//        System.out.println("final summation is: ");
//        double sum2 = 0.0;
//        for (int i = 0;i<rowSize;i++){
//            for(int j = 0;j<colSize;j++){
//                sum2 = sum2 + this.getBoard(i,j);
//            }
//        }
//        System.out.println(sum2);

    }

    boolean updateCatchghostProb(int r, int c){

        boolean Catch = false;

        if(this.getGhostx() != r || this.getGhosty() != c){
            this.setBoard(r,c,0.0);

            /* normalizing */
            double sum = 0.0;
            for (int i = 0;i<rowSize;i++){
                for(int j = 0;j<colSize;j++){
                    sum  = sum + this.getBoard(i,j);
                }
            }

            for (int i = 0;i<rowSize;i++){
                for(int j = 0;j<colSize;j++){
                    double v = (double) this.getBoard(i,j) / sum;
                    this.setBoard(i,j,v);
                }
            }
            /* normalizing finished*/

            attempts++;
            System.out.println("Attempt failed!");
            Catch = false;
        }

        else {
            attempts++;
            Catch = true;

        }
        return Catch;
    }

    void gameOver(){

        for(int i = 0;i<rowSize;i++){
            for(int j =0;j<colSize;j++){
                this.setBoard(i,j,0.0);
            }
        }

        printBoard();

        System.out.println("You have caught the ghost after "+ this.attempts + " attempts\n");
        exit(0);

    }


    public static void main(String[] args) {

        int N,M;
        System.out.println("Enter the number of rows amd columns accordingly: \n");
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Ghostcatch br = new Ghostcatch(N,M);
        br.printBoard();

        while(true){
            System.out.println("1. Time advance\n 2. Sense color\n 3. Catch the ghost\n");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    br.updateAdvanceTime();
                    System.out.println("The board after advancing time:\n");
                    br.printBoard();
                    break;
                case 2:
                    System.out.println("Enter the values of row and column accordingly to sense the color:\n");
                    int row = sc.nextInt();
                    int col = sc.nextInt();
                    br.updateColorProb(row,col);
                    System.out.println("The board after sensing color:\n");
                    br.printBoard();
                    break;
                case 3:
                    System.out.println("Enter the values of row and column accordingly to catch the ghost:\n");
                    int rowg = sc.nextInt();
                    int colg = sc.nextInt();
                    boolean catched = br.updateCatchghostProb(rowg,colg);
                    if(catched == false){
                        System.out.println("The board after trying to catch:\n");
                        br.printBoard();
                    }
                    else{
                        br.gameOver();
                    }

                    break;

            }
        }

    }
}
