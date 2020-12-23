package com.company.Exercice3.Sudoku2Sat;

import java.util.*;

/** Représente une clause présente dans la formule SAT **/
public class Clause {

    /** Liste de littéraux **/
    private List<Literal> literals;

    /** Liste de variables **/
    private Set<Variable> variables;


    public Clause(List<Literal> literals) {
        this.literals = literals;
        this.variables = new HashSet<>();


        for (Literal l : this.literals) {
            this.variables.add(l.getVar());
        }
    }

    public Clause(Literal... literal) {
        this(Arrays.asList(literal));
    }

    public List<Literal> getLiterals() {
        return literals;
    }

    public void setLiterals(List<Literal> literals) {
        this.literals = literals;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public void setVariables(Set<Variable> variables) {
        this.variables = variables;
    }
}