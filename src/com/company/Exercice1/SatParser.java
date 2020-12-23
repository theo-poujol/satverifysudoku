package com.company.Exercice1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SatParser {


    private ArrayList<SatFormula> formulas;
    private boolean[] affects;
    private int nb_var, nb_clauses;


    public SatParser() {
        this.formulas = new ArrayList<>();
    }

    public void parseAffects(String filename) throws Exception {
        String path = "src/com/company/Ressources/Affectations/" + filename;
        BufferedReader in = new BufferedReader(new FileReader(path));

        String line;
        while ((line = in.readLine()) != null) {

            String[] str_affects = line.split(" ");

            if (this.nb_var != str_affects.length)
                throw new Exception("Le nombre de var du fichier d'affectation ne correspond pas avec celui du fichier de formules");

            this.affects = new boolean[str_affects.length];

            int[] int_affects = convertStr_Int(str_affects);


            for (int j = 0; j < int_affects.length; j++) {
                if (int_affects[j] < 0) this.affects[j] = false;
                else this.affects[j] = true;
            }
        }

        for (SatFormula formula : this.formulas)
            formula.setAffects(this.affects);
    }

    public void parseFormulas(String filename) throws Exception {

        String path = "src/com/company/Ressources/Formules/" + filename;
        BufferedReader in = new BufferedReader(new FileReader(path));

        String line;
        while ((line = in.readLine()) != null) {

            String[] str_formulas = line.split(" ");

            if (str_formulas[0].equals("p")) {
                this.nb_clauses = Integer.parseInt(str_formulas[3].trim());
                this.nb_var = Integer.parseInt(str_formulas[2].trim());
            }

            else {

                int[] int_formula = convertStr_Int(str_formulas);

                SatFormula satFormula = new SatFormula(int_formula.length - 1);
//                satFormula.setAffects(this.affects);


                for (int i = 0; i < int_formula.length - 1; i++) {
                    satFormula.getFormula()[i] = int_formula[i];
                }

                this.formulas.add(satFormula);
            }


        }
    }


    public int[] convertStr_Int(String[] array_to_convert) {
        int[] int_affects = new int[array_to_convert.length];

        int i = 0;
        for (String a : array_to_convert) {
            int_affects[i] = Integer.parseInt(a.trim());
            i++;
        }

        return int_affects;
    }

    public ArrayList<SatFormula> getFormula() {
        return formulas;
    }

    public void setFormula(ArrayList<SatFormula> formulas) {
        this.formulas = formulas;
    }

    public boolean[] getAffects() {
        return affects;
    }

    public void setAffects(boolean[] affects) {
        this.affects = affects;
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


    @Override
    public String toString() {
        return ("" +
                "Formule : " + this.formulas.toString() + '\n' +
        "Affectations : " + Arrays.toString(this.affects) + '\n' +
                "Nombre de variables : " + this.nb_var + '\n' +
                "Nombre de clauses : " + this.nb_clauses +
                "");
    }
}
