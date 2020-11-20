package com.company;

import com.company.SatVerifieur.SatParser;
import com.company.SatVerifieur.SatVerify;
import com.company.Sudoku2Sat.SatSolver;
import com.company.Sudoku2Sat.Sudoku;
import com.company.Sudoku2Sat.Sudoku2SatBuilder;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {


        /* EXERCICE 1 */

        SatParser satParser = new SatParser();
        satParser.parseAffects("a2.txt");
        satParser.parseFormulas("f1.txt");

        System.out.println(satParser.toString());

        SatVerify satVerify = new SatVerify(satParser.getFormulas(), satParser.getNb_var(), satParser.getNb_clauses());
        System.out.println("Pour l'affectation " + Arrays.toString(satParser.getAffects()));
        System.out.println("Les formules sont-elles satisfaisables? : " + satVerify.verify());

        int[][] sdk = {
                {0, 0, 0,  8, 1, 0,  0, 0, 5},
                {0, 0, 0,  0, 2, 0,  0, 3, 0},
                {8, 0, 0,  0, 0, 5,  0, 4, 9},

                {4, 0, 0,  1, 0, 0,  0, 6, 3},
                {0, 2, 0,  0, 0, 0,  0, 9, 0},
                {3, 7, 0,  0, 0, 2,  0, 0, 8},

                {7, 8, 0,  2, 0, 0,  0, 0, 6},
                {0, 4, 0,  0, 5, 0,  0, 0, 0},
                {6, 0, 0,  0, 9, 8,  0, 0, 0},
        };

        /* EXERCICE 2 */

        System.out.println("Sudoku avant");
        for (int[] row : sdk) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        Sudoku sudoku = new Sudoku(sdk);

        System.out.println("taille " + sudoku.getClauses().size());

    }
}
