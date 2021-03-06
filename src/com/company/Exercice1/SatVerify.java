package com.company.Exercice1;

import java.util.ArrayList;
import java.util.Arrays;

public class SatVerify {

    private ArrayList<SatFormula> formulas;
    private int nb_var, nb_clauses;

    public SatVerify(ArrayList<SatFormula> formulas, int nb_var, int nb_clauses) {
        this.formulas = formulas;
        this.nb_var = nb_var;
        this.nb_clauses = nb_clauses;
    }


    public boolean verify() {
        for (SatFormula formula : this.formulas) {
            boolean res = this.verifyFormula(formula);
            if (!res) return false;
        }
        return true;
    }

    public boolean verifyFormula(SatFormula formula) {
        for (int i = 0; i < formula.getFormula().length; i++ ) {
            int v = 0;
            if (formula.getFormula()[i] > 0) {
                v = formula.getFormula()[i]-1;
                if (formula.getAffects()[v]) {
                    formula.setResult(true);
                    return true;
                }
            }
            else {
                v = (-formula.getFormula()[i] -1);

                if (!formula.getAffects()[v]) {
                    formula.setResult(true);
                    return true;
                }
            }
        }

        formula.setResult(false);
        return false;
    }

    public ArrayList<SatFormula> getFormula() {
        return formulas;
    }

    public void setFormula(ArrayList<SatFormula> formula) {
        this.formulas = formula;
    }

    public int getNb_var() {
        return nb_var;
    }

    public void setNb_var(int nb_var) {
        this.nb_var = nb_var;
    }

    public int getNb_clauses() {
        return nb_clauses;
    }

    public void setNb_clauses(int nb_clauses) {
        this.nb_clauses = nb_clauses;
    }


}
