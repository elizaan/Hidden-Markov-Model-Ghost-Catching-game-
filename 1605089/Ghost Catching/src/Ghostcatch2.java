import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Ghostcatch2 {

    int[][] board;
    int rowSize;
    int colSize;
    int ghostx;
    int ghosty;
    int attempts;
    int totalParticle;
    double corner1;
    double corner2;
    double general1;
    double general2;
    double border1;
    double border2;

    Ghostcatch2(int m, int n){
        totalParticle = 0;
        this.board = new int[n][m];
        this.colSize = n;
        this.rowSize = m;
        this.attempts = 0;
        corner1 = 0.48;
        corner2 = 0.02;
        border1 = 0.96/3.0;
        border2 = 0.04/3.0;
        general1 = 0.24;
        general2 = 0.008;

        for(int i =0;i<m;i++){
            for(int j =0;j<n;j++){
                this.board[i][j] = 1000/(m*n);
                totalParticle = totalParticle + this.board[i][j];
            }
        }

        ghostx = (n-1)/2;
        ghosty = (m-1)/2;
    }

    void setBoard(int i, int j, int value)
    {
        this.board[i][j] = value;
    }

    int getBoard(int i, int j){
        return this.board[i][j];
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

    void printBoard(){
        for(int i =0;i<rowSize;i++){
            for(int j =0;j<colSize;j++){
                System.out.printf("%d",this.getBoard(i,j));
                System.out.print(" ");
            }
            System.out.println();
        }


        //System.out.println("ghost tumi koi: " +this.getGhostx()+ ","+ this.getGhosty());
    }

    void ghostMove(){

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

    void particleMove(int i, int j){


        Random rand2 = new Random();
        double moveProbability = rand2.nextDouble();

        if(i ==0 && j ==0){

            if(moveProbability>=0 && moveProbability< corner1){

                this.setBoard(0,0,this.getBoard(0,0)-1);
                this.setBoard(1,0,this.getBoard(1,0)+1);
                //move down
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){

                this.setBoard(0,0,this.getBoard(0,0)-1);
                this.setBoard(0,1,this.getBoard(0,1)+1);
                //move right
            }
            else if(moveProbability>=2*corner1 && moveProbability< (2*corner1+corner2)) {

                this.setBoard(0,0,this.getBoard(0,0)-1);
                this.setBoard(1,1,this.getBoard(1,1)+1);
                //move diag

            }
            else {
                this.setBoard(0,0,this.getBoard(0,0)-1);
                this.setBoard(0,0,this.getBoard(0,0)+1);
                //self
            }

        }

        else if (i == 0 && j == colSize -1){


            if(moveProbability>=0 && moveProbability< corner1){

                this.setBoard(0,colSize-1,this.getBoard(0,colSize-1)-1);
                this.setBoard(1,colSize-1,this.getBoard(1,colSize-1)+1);
                //move down
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){

                this.setBoard(0,colSize-1,this.getBoard(0,colSize-1)-1);
                this.setBoard(0,colSize-2,this.getBoard(0,colSize-2)+1);
                //move left
            }
            else if(moveProbability>= 2*corner1 && moveProbability< (2*corner1+corner2)) {

                this.setBoard(0,colSize-1,this.getBoard(0,colSize-1)-1);
                this.setBoard(1,colSize-2,this.getBoard(1,colSize-2)+1);
                //move diag

            }
            else {

                this.setBoard(0,colSize-1,this.getBoard(0,colSize-1)-1);
                this.setBoard(0,colSize-1,this.getBoard(0,colSize-1)+1);
                //self
            }

        }

        else if (i == rowSize-1 && j == 0){


            if(moveProbability>=0 && moveProbability< corner1){

                this.setBoard(rowSize-1,0,this.getBoard(rowSize-1,0)-1);
                this.setBoard(rowSize-2,0,this.getBoard(rowSize-2,0)+1);
                //move up
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){

                this.setBoard(rowSize-1,0,this.getBoard(rowSize-1,0)-1);
                this.setBoard(rowSize-1,1,this.getBoard(rowSize-1,1)+1);
                //move right
            }
            else if(moveProbability>= 2*corner1 && moveProbability< (2*corner1+corner2)) {

                this.setBoard(rowSize-1,0,this.getBoard(rowSize-1,0)-1);
                this.setBoard(rowSize-2,1,this.getBoard(rowSize-2,1)+1);
                //move diag

            }
            else {

                this.setBoard(rowSize-1,0,this.getBoard(rowSize-1,0)-1);
                this.setBoard(rowSize-1,0,this.getBoard(rowSize-1,0)+1);
                //self
            }


        }

        else if(i == rowSize-1 && j == colSize -1){


            if(moveProbability>=0 && moveProbability< corner1){

                this.setBoard(rowSize-1,colSize-1,this.getBoard(rowSize-1,colSize-1)-1);
                this.setBoard(rowSize-2,colSize-1,this.getBoard(rowSize-2,colSize-1)+1);
                //move up
            }
            else if(moveProbability>=corner1 && moveProbability< 2*corner1){

                this.setBoard(rowSize-1,colSize-1,this.getBoard(rowSize-1,colSize-1)-1);
                this.setBoard(rowSize-1,colSize-2,this.getBoard(rowSize-1,colSize-2)+1);
                //move left
            }
            else if(moveProbability>= 2*corner1 && moveProbability< (2*corner1+corner2)){

                this.setBoard(rowSize-1,colSize-1,this.getBoard(rowSize-1,colSize-1)-1);
                this.setBoard(rowSize-2,colSize-2,this.getBoard(rowSize-2,colSize-2)+1);
                //move diag

            }
            else {

                this.setBoard(rowSize-1,colSize-1,this.getBoard(rowSize-1,colSize-1)-1);
                this.setBoard(rowSize-1,colSize-1,this.getBoard(rowSize-1,colSize-1)+1);
                //self
            }

        }

        else if((i>0 && i<rowSize-1) && j == 0){

            if(moveProbability>=0 && moveProbability< border1){

                this.setBoard(i,0,this.getBoard(i,0)-1);
                this.setBoard(i-1,0,this.getBoard(i-1,0)+1);
                //move up
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){

                this.setBoard(i,0,this.getBoard(i,0)-1);
                this.setBoard(i+1,0,this.getBoard(i+1,0)+1);
                //move down
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){

                this.setBoard(i,0,this.getBoard(i,0)-1);
                this.setBoard(i,1,this.getBoard(i,1)+1);
                //move right
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){

                this.setBoard(i,0,this.getBoard(i,0)-1);
                this.setBoard(i-1,1,this.getBoard(i-1,1)+1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){

                this.setBoard(i,0,this.getBoard(i,0)-1);
                this.setBoard(i+1,1,this.getBoard(i+1,1)+1);
                //move diag2
            }
            else {

                this.setBoard(i,0,this.getBoard(i,0)-1);
                this.setBoard(i,0,this.getBoard(i,0)+1);
                //self
            }


        }

        else if((i>0 && i<rowSize-1) && j == colSize-1){

            if(moveProbability>=0 && moveProbability< border1){

                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)-1);
                this.setBoard(i-1,colSize-1,this.getBoard(i-1,colSize-1)+1);
                //move up
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){

                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)-1);
                this.setBoard(i+1,colSize-1,this.getBoard(i+1,colSize-1)+1);
                //move down
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){

                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)-1);
                this.setBoard(i,colSize-2,this.getBoard(i,colSize-2)+1);
                //move left
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){

                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)-1);
                this.setBoard(i+1,colSize-2,this.getBoard(i+1,colSize-2)+1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){

                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)-1);
                this.setBoard(i-1,colSize-2,this.getBoard(i-1,colSize-2)+1);
                //move diag2
            }
            else {

                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)-1);
                this.setBoard(i,colSize-1,this.getBoard(i,colSize-1)+1);
                //self
            }


        }

        else if (i == 0 && (j>0 && j< colSize-1)){

            if(moveProbability>=0 && moveProbability< border1){

                this.setBoard(0,j,this.getBoard(0,j)-1);
                this.setBoard(1,j,this.getBoard(1,j)+1);
                //move down
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){

                this.setBoard(0,j,this.getBoard(0,j)-1);
                this.setBoard(0,j+1,this.getBoard(0,j+1)+1);
                //move right
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){

                this.setBoard(0,j,this.getBoard(0,j)-1);
                this.setBoard(0,j-1,this.getBoard(0,j-1)+1);
                //move left
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){

                this.setBoard(0,j,this.getBoard(0,j)-1);
                this.setBoard(1,j+1,this.getBoard(1,j+1)+1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){

                this.setBoard(0,j,this.getBoard(0,j)-1);
                this.setBoard(1,j-1,this.getBoard(1,j-1)+1);
                //move diag2
            }
            else {

                this.setBoard(0,j,this.getBoard(0,j)-1);
                this.setBoard(0,j,this.getBoard(0,j)+1);
                //self
            }

        }

        else if(i == rowSize-1 && (j>0 && j< colSize-1)){

            if(moveProbability>=0 && moveProbability< border1){

                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)-1);
                this.setBoard(rowSize-2,j,this.getBoard(rowSize-2,j)+1);
                //move up
            }
            else if(moveProbability>= border1 && moveProbability< 2*border1){

                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)-1);
                this.setBoard(rowSize-1,j+1,this.getBoard(rowSize-1,j+1)+1);
                //move right
            }
            else if(moveProbability>= 2*border1 && moveProbability< 3*border1){

                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)-1);
                this.setBoard(rowSize-1,j-1,this.getBoard(rowSize-1,j-1)+1);
                //move left
            }
            else if(moveProbability>= 3*border1 && moveProbability< (3*border1 + border2) ){

                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)-1);
                this.setBoard(rowSize-2,j+1,this.getBoard(rowSize-2,j+1)+1);
                //move diag1
            }
            else if(moveProbability>= (3*border1 + border2) && moveProbability< (3*border1+2*border2)){

                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)-1);
                this.setBoard(rowSize-2,j-1,this.getBoard(rowSize-2,j-1)+1);
                //move diag2
            }
            else {

                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)-1);
                this.setBoard(rowSize-1,j,this.getBoard(rowSize-1,j)+1);
                //self
            }

        }

        else {

            if(moveProbability>=0 && moveProbability < general1){

                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i-1,j,this.getBoard(i-1,j)+1);
                //move up
            }

            else if(moveProbability>= general1 && moveProbability< 2*general1){

                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i+1,j,this.getBoard(i+1,j)+1);
                //move down
            }

            else if(moveProbability>= 2*general1 && moveProbability< 3*general1){

                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i,j+1,this.getBoard(i,j+1)+1);
                //move right
            }
            else if(moveProbability>= 3*general1 && moveProbability< 4*general1){

                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i,j-1,this.getBoard(i,j-1)+1);
                //move left
            }
            else if(moveProbability>= 4*general1 && moveProbability< (4*general1+general2))
            {
                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i-1,j+1,this.getBoard(i-1,j+1)+1);
                //move diag1
            }
            else if(moveProbability>= (4*general1+general2) && moveProbability< (4*general1+2*general2))
            {
                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i+1,j+1,this.getBoard(i+1,j+1)+1);
                //move diag2
            }
            else if(moveProbability>= (4*general1+2*general2) && moveProbability< (4*general1+3*general2))
            {
                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i+1,j-1,this.getBoard(i+1,j-1)+1);
                //move diag3
            }
            else if(moveProbability> (4*general1+3*general2) && moveProbability<= (4*general1+4*general2))
            {
                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i-1,j-1,this.getBoard(i-1,j-1)+1);
                //move diag4
            }
            else {

                this.setBoard(i,j,this.getBoard(i,j)-1);
                this.setBoard(i,j,this.getBoard(i,j)+1);
                //self
            }


        }


    }

    void updateAdvanceTime(){
        int[][] temp = new int[rowSize][colSize];


        for(int i =0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                temp[i][j] = this.getBoard(i,j);
            }
        }

        for (int i = 0; i<rowSize; i++){
            for(int j = 0;j<colSize; j++){

            }
        }

        ghostMove();

        for (int i =0; i<rowSize ;i++){
            for(int j = 0; j<colSize;j++){
                int particles = temp[i][j];
                for(int k = 1; k<=particles; k++){

                    particleMove(i,j);

                }
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
        System.out.println("The sensed color is: " + colorfound);

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

                    this.setBoard(i,j,this.getBoard(i,j)*1);
                }
                else {
                    this.setBoard(i,j,this.getBoard(i,j)*0);
                }


            }
        }

        /* normalizing */
        int sum = 0;
        for (int i = 0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                sum  = sum + this.getBoard(i,j);
            }
        }

        for (int i = 0;i<rowSize;i++){
            for(int j = 0;j<colSize;j++){
                int v = this.getBoard(i,j) * totalParticle / sum;
                this.setBoard(i,j,v);
            }
        }
        /* normalizing finished*/

    }

    boolean updateCatchghostProb(int r, int c){

        boolean Catch = false;

        if(this.getGhostx() != r || this.getGhosty() != c){
            this.setBoard(r,c,0);

            /* normalizing */
            int sum = 0;
            for (int i = 0;i<rowSize;i++){
                for(int j = 0;j<colSize;j++){
                    sum  = sum + this.getBoard(i,j);
                }
            }

            for (int i = 0;i<rowSize;i++){
                for(int j = 0;j<colSize;j++){
                    int v =  (this.getBoard(i,j) * totalParticle) / sum;
                    this.setBoard(i,j,v);
                }
            }
            /* normalizing finished*/

            attempts++;
            System.out.println("Attempt failed!\n");
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
                this.setBoard(i,j,0);
            }
        }

        printBoard();

        System.out.println("You have caught the ghost after "+ this.attempts + " attempts\n");
        exit(0);

    }

    public static void main(String[] args) {
        int M,N;
        System.out.println("Enter the number of rows amd columns accordingly: \n");
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        Ghostcatch2 br = new Ghostcatch2(M,N);
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
