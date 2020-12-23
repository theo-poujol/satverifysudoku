package com.company;

import com.company.Exercice1.SatVerifieur.SatParser;
import com.company.Exercice1.SatVerifieur.SatVerify;

public class Main {

    public static void main(String[] args) throws Exception {


        /* EXERCICE 1 */

        /** Création de l'analyseur **/
        SatParser satParser = new SatParser();
        /** Analsye des affectations **/
        satParser.parseAffects("a50.txt");
        /** Analsye de la formule **/
        satParser.parseFormulas("50-20480.txt");


        System.out.println("------ [EXERCICE 1] ------");

        /** Affichage de la formule **/
        System.out.println(satParser.toString());
        System.out.println("");


        /** Création du vérificateur **/
        SatVerify satVerify = new SatVerify(satParser.getFormulas(), satParser.getNb_var(), satParser.getNb_clauses());

        /** Vérification de la satisfaisabilité **/
        long start = System.nanoTime();
        System.out.println("La formule est-elle satisfaisable? : " + satVerify.verify());
        System.out.println((System.nanoTime()-start)/1000000F + " ms") ;


        /** EXERCICE 2 **/


//        System.out.println("");
//        System.out.println("------ [EXERCICE 2] ------");
//
//
//        // Création du sudoku
//        int[][] sudoku = {
//                {0, 0, 0,  8, 1, 0,  0, 0, 5},
//                {0, 0, 0,  0, 2, 0,  0, 3, 0},
//                {8, 0, 0,  0, 0, 5,  0, 4, 9},
//
//                {4, 0, 0,  1, 0, 0,  0, 6, 3},
//                {0, 2, 0,  0, 0, 0,  0, 9, 0},
//                {3, 7, 0,  0, 0, 2,  0, 0, 8},
//
//                {7, 8, 0,  2, 0, 0,  0, 0, 6},
//                {0, 4, 0,  0, 5, 0,  0, 0, 0},
//                {6, 0, 0,  0, 9, 8,  0, 0, 0},
//        };
//
//
//        int[][] grid = Utils.generateSudoku(45);
////        for (int[] row : grid) {
////            System.out.println(Arrays.toString(row));
////        }
//
//        // Début du timer
//        long debut = System.nanoTime();
//
//        // Création de la formule SAT grâce à la réduction Sudoku vers SAT
//        SatSudoku satSudoku = new SatSudoku(sudoku, sudoku[0].length);
//
//        System.out.println("Temps pris pour effectuer la réduction pour un sudoku de taille : " + sudoku[0].length);
//        System.out.println((System.nanoTime() - debut)/1000000000F + " s");
//
//
//        // Écrire de la formule, au format DIMACS CNF, dans le fichier test.cnf
//        satSudoku.writeSudokuMiniSat();


    }
}
