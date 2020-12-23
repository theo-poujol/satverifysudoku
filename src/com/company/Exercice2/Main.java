package com.company.Exercice2;



import static com.company.Exercice2.ZoneVide2SAT.*;

public class Main {

    public static void main(String[] args) throws Exception {


        int[][] g = generateGraph(10);

        int graphG[][] = {{0, 1, 0, 0, 1, 1},  //1
                {1, 0, 1, 1, 0, 0},  //2
                {0, 1, 0, 1, 0, 0},  //3
                {0, 1, 1, 0, 1, 0},  //4
                {1, 0, 0, 1, 0, 0},  //5
                {1, 0, 0, 0, 0, 0}}; //6


        long debut = System.nanoTime();
        reductionIStoSAT(g,5,"ZoneVideSAT.txt");
        bruteForce("ZoneVideSAT.txt");
        long fin = System.nanoTime();
        System.out.println("réduction et brute force en " + (fin-debut)/1_000_000_000F + "s");

       /* long debut2 = System.nanoTime();
        reductionIStoSAT(graphG,3,"Result.txt");
        //MiniSat Solver
        long fin2 = System.nanoTime();
        System.out.println("réduction et MiniSat en " + (fin2-debut2)/1_000_000_000F + "s");*/
    }
}
