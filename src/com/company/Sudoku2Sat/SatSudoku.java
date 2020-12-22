package com.company.Sudoku2Sat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Sudoku extends Formula {

    public int[][] matrix;

    public Sudoku(int[][] sudoku, int col) throws IOException {
        super(Sudoku2SatBuilder.Sudoku2Sat(sudoku, col));
        this.matrix = sudoku;
        this.process();
        writeSudokuMiniSat();
    }

    public void process() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] != 0) {
                    String var = i + Integer.toString(j) + matrix[i][j];
                    this.getMap().get(var).setValue(true);
                }
            }
        }
    }

    public void writeSudokuMiniSat() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/company/minisatsudoku/test.cnf"));

        bw.write("p cnf " + this.getMap().size() + " " + this.getClauses().size());
        bw.newLine();

        for (Clause c : this.getClauses()) {
            StringBuilder literals = new StringBuilder();
            for (Literal l : c.getLiterals()) {

                if (!l.sign) literals.append("-").append(l.getVar().getName()).append(" ");
                else literals.append(l.getVar().getName()).append(" ");

            }
            literals.append("0");
            bw.write(String.valueOf(literals));
            bw.newLine();
        }

        bw.flush();
    }

}