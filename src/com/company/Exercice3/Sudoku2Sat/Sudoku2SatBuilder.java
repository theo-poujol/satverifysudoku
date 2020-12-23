package com.company.Exercice3.Sudoku2Sat;

import java.util.ArrayList;
import java.util.List;

public class Sudoku2SatBuilder {

    /** Taille du sudoku **/
    public static int SIZE;

    /** Clauses de la formule **/
    private static List<Clause> clauses = new ArrayList<>();

    /** Constructeur  **/
    public static Formula Sudoku2Sat(int[][] sudoku, int col) {

        SIZE = col;

        /** Réduction grâce aux contraintes du sudoku **/
        AtLeastOneOccurrence();
        RowConstraint();
        ColConstraint();
        AtMaxOneOccurence();
        singletonClause(sudoku);

        /** On retourne une nouvelle formule, induite de la réduction de Sudoku vers SAT **/
        return new Formula(clauses);
    }


    /** 1ère contrainte : Dans chaque cellule du sudoku, nous devons retrouver au moins une occurrence d’un chiffre.  **/

    public static void AtLeastOneOccurrence() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                List<Literal> clause = new ArrayList<Literal>();
                for (int n = 1; n < SIZE +1; n++) {
                    String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
                    Literal l = new Literal(l1,n,true);
                    clause.add(l);
                }
                clauses.add(new Clause(clause));
            }
        }
    }

    /** 2ème contrainte : Dans chaque cellule du sudoku, nous devons retrouver au plus une occurrence d’un chiffre.
     *  Afin de ne pas avoir 2 chiffres pour la même case.  **/

    public static void AtMaxOneOccurence() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int n = 1; n < SIZE +1; n++) {
                    if (n != SIZE) {
                        String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
                        String l2 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n+1);
                        Literal literal1 = new Literal(l1, n, false);
                        Literal literal2 = new Literal(l2, n+1, false);
                        clauses.add(new Clause(literal1, literal2));
                    }

                    for (int n2 = 1; n2 < SIZE +1; n2++) {
                        if (n2 != n) {
                            String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
                            String l2 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n2);
                            Literal literal1 = new Literal(l1, n, false);
                            Literal literal2 = new Literal(l2, n2, false);
                            clauses.add(new Clause(literal1, literal2));
                        }
                    }
                }
            }
        }
    }

    /** 3ème contrainte : Il est impossible d’avoir 2 chiffres sur la même ligne **/

    public static void RowConstraint() {

        for(int x = 0; x < SIZE; x++) {
            for (int z = 1; z < SIZE +1; z++) {
                List<Literal> clause = new ArrayList<Literal>();
                for (int y = 0; y < SIZE; y++) {
                    String l_name = x + Integer.toString(y) + z;
                    clause.add(new Literal(l_name, z, true));
                }
                clauses.add(new Clause(clause));
            }
        }
    }

    /** 4ème contrainte : Il est impossible d’avoir 2 chiffres sur la même colonne **/

    public static void ColConstraint() {
        for(int y = 0; y < SIZE; y++) {
            for (int z = 1; z < SIZE +1; z++) {
                List<Literal> clause = new ArrayList<Literal>();
                for (int x = 0; x < SIZE; x++) {
                    String l_name = x + Integer.toString(y) + z;
                    clause.add(new Literal(l_name, z, true));
                }
                clauses.add(new Clause(clause));
            }
        }
    }

    /** 5ème contrainte : Il est impossible de changer le numéro d’une cellule du sudoku de base. **/

    public static void singletonClause(int[][] sudoku) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (sudoku[i][j] > 0) {
//                    int val = sudoku[i][j] - 1;
                    String l = i + Integer.toString(j) + sudoku[i][j];
                    clauses.add(new Clause(new Literal(l,sudoku[i][j], true)));
                }
            }
        }
    }

}