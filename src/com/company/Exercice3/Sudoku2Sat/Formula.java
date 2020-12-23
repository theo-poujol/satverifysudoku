package com.company.Exercice3.Sudoku2Sat;

import java.util.*;


/** Représente la formule créée suite à la réduction de Sudoku vers SAT **/
public class Formula {

    /** Liste de clauses **/
    private List<Clause> clauses;

    /** Liste de variables **/
    private Set<Variable> variables;

    /** Map associant le nom d’une variable à sa variable  **/
    private Map<String, Variable> map;

    public Formula(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public Formula(Formula formula) {

        this.clauses = formula.getClauses();

        this.variables = new HashSet<>();

        for (Clause c : this.clauses) {
            this.variables.addAll(c.getVariables());
        }

        this.map = new HashMap<>();

        for (Variable v : this.variables) this.map.put(v.getName(), v);
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public Map<String, Variable> getMap() { return this.map; }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Clause c : this.clauses) {
            str.append('(').append(c.toString()).append(')').append('^');
        }
        return str.substring(0, str.length()-1);
    }
}