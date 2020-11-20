package com.company.SatVerifieur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SatParser {

    private ArrayList<SatFormula> formulas;
    private boolean[] affects;
    private int nb_var, nb_clauses;


    public SatParser() {
        this.formulas = new ArrayList<>();
    }

    public void parseAffects(String filename) throws IOException {
        String path = "src/com/company/affectations/" + filename;
        BufferedReader in = new BufferedReader(new FileReader(path));

        String line;
        while ((line = in.readLine()) != null) {

            String[] str_affects = line.split(" ");

            this.nb_var = str_affects.length;
            this.affects = new boolean[str_affects.length];

            int[] int_affects = convertStr_Int(str_affects);

            System.out.println("LA " + Arrays.toString(int_affects));
            for (int j = 0; j < int_affects.length; j++) {
                if (int_affects[j] < 0) this.affects[j] = false;
                else this.affects[j] = true;
            }
        }
    }

    public void parseFormulas(String filename) throws Exception {

        String path = "src/com/company/formulas/" + filename;
        BufferedReader in = new BufferedReader(new FileReader(path));

        String line;
        while ((line = in.readLine()) != null) {

            String[] str_formulas = line.split(" ");

            if (str_formulas[0].equals("p")) {
                this.nb_clauses = Integer.parseInt(str_formulas[3].trim());

                if (this.nb_var != Integer.parseInt(str_formulas[2].trim()))
                    throw new Exception("Le nombre de var du fichier d'affectation ne correspond pas avec celui du fichier de formules");
            }

            else {
                int[] int_formula = convertStr_Int(str_formulas);

                SatFormula satFormula = new SatFormula(int_formula.length - 1);
                satFormula.setAffects(this.affects);

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

    public ArrayList<SatFormula> getFormulas() {
        return formulas;
    }

    public void setFormulas(ArrayList<SatFormula> formulas) {
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
                "Formules " + this.formulas.toString() + '\n' +
        "Affects " + Arrays.toString(this.affects) + '\n' +
                "Nombre var " + this.nb_var + '\n' +
                "Nombre clauses " + this.nb_clauses +
                "");
    }
}
