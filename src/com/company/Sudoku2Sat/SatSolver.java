package com.company.Sudoku2Sat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SatSolver {

    public static Formula formula2solve;
    public static List<Clause> clause2solve;

    public static boolean solve(Formula formula) {

        formula2solve = formula;

        if (formula.getClauses().isEmpty()) return true;

        for (Clause c : formula.getClauses()) {
            if (c.getLiterals().isEmpty()) return false;
        }

        Comparator<Clause> clauseLengthComparator = new Comparator<Clause>() {
            @Override
            public int compare(Clause o1, Clause o2) {
                return Integer.compare(o1.getLiterals().size(), o2.getLiterals().size());
            }
        };

        List<Clause> clauses = new ArrayList<>(formula.getClauses());
        Collections.sort(clauses, clauseLengthComparator);

        clause2solve = clauses;

        return processSolve();
    }

    public static boolean processSolve() {

        return true;
    }




}
