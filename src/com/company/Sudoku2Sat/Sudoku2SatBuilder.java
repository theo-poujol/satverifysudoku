package com.company.Sudoku2Sat;

import java.util.ArrayList;
import java.util.List;

public class Sudoku2SatBuilder {

    private static List<Clause> clauses = new ArrayList<>();

    public static Formula Sudoku2Sat(int[][] sudoku) {

        AtLeastOneOccurence();
//        RowBuild();
//        ColBuild();
//        BoxBuild();
        newRow();
        newCol();
        AtMaxOneOccurence();
        singletonClause(sudoku);

        return new Formula(clauses);
    }

    public static void RowBuild() {
        for (int i = 0; i < 9; i++) {
            for (int n = 1; n < 10; n++) {
                for (int jsec = 1; jsec < 9; jsec++) {
                    for (int j = 0; j < jsec; j++) {
                        String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
                        String l2 = Integer.toString(i) + Integer.toString(jsec) + Integer.toString(n);
                        clauses.add(new Clause(new Literal(l1, n, false), new Literal(l2, n, false)));
                    }
                }
            }
        }
    }

    public static void ColBuild() {
        for (int j = 0; j < 9; j++) {
            for (int n = 1; n < 10; n++) {
                for (int isec = 1; isec < 9; isec++) {
                    for (int i = 0; i < j; i++) {
                        String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
                        String l2 = Integer.toString(isec) + Integer.toString(j) + Integer.toString(n);
                        clauses.add(new Clause(new Literal(l1, n, false), new Literal(l2, n, false)));
                    }
                }

            }
        }
    }

    public static void AtLeastOneOccurence() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<Literal> clause = new ArrayList<Literal>();
                for (int n = 1; n < 10; n++) {
                    String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
                    Literal l = new Literal(l1,n,true);
                    clause.add(l);
                }
                clauses.add(new Clause(clause));
            }
        }
    }

    public static void AtMaxOneOccurence() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int n2 = 1; n2 < 10; n2++) {
                    for (int n1 = 0; n1 < n2 ; n1++) {
                        String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n1);
                        String l2 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n2);
                        Literal literal1 = new Literal(l1, n1, false);
                        Literal literal2 = new Literal(l2, n2, false);
                        clauses.add(new Clause(literal1, literal2));
                    }
                }
            }
        }
    }

    public static void BoxBuild() {
//        int cpt = 0;
//        for (int k = 0; k < 9; k+= 3) {
//            List<Literal> clause = new ArrayList<Literal>();
//            for (int i = cpt; i < cpt + 3; i++) {
//                for (int n =1; n < 10; n++) {
//                    for (int jsec = cpt; jsec < cpt + 3; jsec++) {
//                        for (int j = cpt+1; j < jsec; j++) {
//                            String l1 = Integer.toString(i) + Integer.toString(j) + Integer.toString(n);
//                            String l2 = Integer.toString(i) + Integer.toString(jsec) + Integer.toString(n);
//                            Literal literal1 = new Literal(l1, n, false);
//                            Literal literal2 = new Literal(l2, n, false);
//                            clause.add(literal1);
//                            clause.add(literal2);
//                        }
//                    }
//                }
//            }
//            clauses.add(new Clause(clause));
//            if (cpt + 3 < 9) cpt += 3;
//
//        }

//        int block = 9 / 3;
//        for (int b_i = 0; b_i < 9; b_i += block) {
//            for (int b_j = 0; b_j < 9; b_j += block) {
//                // for all numbers
//                for (int n = 1; n < 10; n++) {
//                    // all <(i1,j1),(i2,j2)> pairs
//                    for (int i1 = 0; i1 < block; i1++) {
//                        for (int j1 = 0; j1 < block; j1++) {
//                            for (int i2 = 0; i2 < block; i2++) {
//                                for (int j2 = 0; j2 < block; j2++) {
//                                    if (i1 != i2 || j1 != j2) {
//                                        String l1 = Integer.toString(b_i) + Integer.toString(i1) + Integer.toString(b_j) + Integer.toString(j1) + Integer.toString(n);
//                                        String l2 = Integer.toString(b_i) + Integer.toString(i2) + Integer.toString(b_j) + Integer.toString(j2) + Integer.toString(n);
//                                        Literal literal1 = new Literal(l1, n, false);
//                                        Literal literal2 = new Literal(l2, n, false);
//                                        clauses.add(new Clause(literal1, literal2));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

    }

    public static void newRow() {

        for(int x = 0; x < 9; x++) {
            for (int z = 1; z < 10; z++) {
                List<Literal> clause = new ArrayList<Literal>();
                for (int y = 0; y < 9; y++) {
                    String l_name = x + Integer.toString(y) + z;
                    clause.add(new Literal(l_name, z, true));
                }
                clauses.add(new Clause(clause));
            }
        }
    }

    public static void newCol() {
        for(int y = 0; y < 9; y++) {
            for (int z = 1; z < 10; z++) {
                List<Literal> clause = new ArrayList<Literal>();
                for (int x = 0; x < 9; x++) {
                    String l_name = x + Integer.toString(y) + z;
                    clause.add(new Literal(l_name, z, true));
                }
                clauses.add(new Clause(clause));
            }
        }
    }

    public static void singletonClause(int[][] sudoku) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] > 0) {
//                    int val = sudoku[i][j] - 1;
                    String l = i + Integer.toString(j) + sudoku[i][j];
                    clauses.add(new Clause(new Literal(l,sudoku[i][j], true)));
                }
            }
        }
    }

}
