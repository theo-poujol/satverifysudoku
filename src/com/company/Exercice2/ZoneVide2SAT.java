package com.company.Exercice2;

import com.company.Exercice1.SatFormula;
import com.company.Exercice1.SatParser;
import com.company.Exercice1.SatVerify;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implementation du mini projet 2
 *
 * @version 22/12/2020
 */
public class ZoneVide2SAT {
    // Graphe de test                1  2  3  4  5  6
    public static int graphG[][] = {{0, 1, 0, 0, 1, 1},  //1
                                    {1, 0, 1, 1, 0, 0},  //2
                                    {0, 1, 0, 1, 0, 0},  //3
                                    {0, 1, 1, 0, 1, 0},  //4
                                    {1, 0, 0, 1, 0, 0},  //5
                                    {1, 0, 0, 0, 0, 0}}; //6




    public static int[][] generateGraph(int taille)
    {
        int[][] graph = new int[taille][taille];
        for (int i = 0; i < taille; i++)
        {
            for (int j = 0; j < i; j++)
            {
                graph[i][j] = (int)Math.round(Math.random());
            }
        }
        for (int i = 0; i < taille; i++)
        {
            graph[i][i] = 0;
        }
        for (int j = 0; j < taille; j++) {
            for (int i = 0; i < j; i++) {
                graph[i][j] = graph[j][i];
            }
        }

        return graph;
    }

    /**
     * Effectue une reduction vers SAT.
     */

    public static void reductionIStoSAT(int[][] graph, int k,String file) throws IOException {
        int nbSommet = graph[0].length;         //On initialise le nombre sommet grâce à la matrice d'adjacence
        int tailleClause = nbSommet - (k - 1);    //On définit la taille des clauses de "combinaison"
        FileWriter fileForSAT = new FileWriter("src/com/company/Ressources/Formules/"+file); // On ecrit ou on crée le fichier Result.txt s'il n'existe pas
        BufferedWriter writer = new BufferedWriter(fileForSAT); // On instancie un bufferedWriter afin d'écrire dans le fichier vu juste au-dessus
        CoefBinomial coefBinomial = new CoefBinomial(); //Cet objet va nous servir à calculer le nombre de clauses dans notre FNC
        int arcs = arcs(graph); //Cette fonction nous permet de connaître le nombre d'arcs d'un graph
        int clauses = arcs+coefBinomial.compute(tailleClause,nbSommet); // le nombre de clause est égale aux nombres d'arcs + plus le coefficient binomial de k parmi le nombre de sommets
        writer.write("p cnf " + nbSommet + " "+clauses+'\n' );// on écrit la première ligne du format DIMACS CNF avec les informations calculées plus haut

        // On fait les clauses pour chaque arc
        for (int i = 0; i < graph[0].length; i++) {
            for (int j = 0; j < i; j++) {
                if (graph[i][j] == 1)
                    writer.write(-(i+1) +" "+-(j+1)+" 0"+'\n');
            }
        }

        // On fait les clauses combinatoires
        Combinaison combinaison = new Combinaison();
        ArrayList<String> combi = combinaison.compute(nbSommet,tailleClause);// On récupère toutes les combinaisons en fonction du nombre de sommets et du k mis en paramètre
        for (String s : combi)
        {
            String[] line = s.split(",");      //on prépare la clause au format DIMACS
            StringBuilder str = new StringBuilder();
            for (String s2 :line)
            {
                str.append(s2).append(" ");
            }
            str.append("0").append('\n');
            writer.write(str.toString());// on rajoute la clause dans le fichier

        }

        writer.close();// on ferme le buffer



    }

    private static class CoefBinomial {
        private int fact(int i) {
            if (i <= 1) {
                return 1;
            }
            return i * fact(i - 1);
        }

        public int compute(int j, int i) {
            int ncr = fact(i) / (fact(j) * fact(i - j));
            return ncr;
        }
    }// permet d'avoir le coefficient binomial de deux entier

    private static class Combinaison {

        public ArrayList<String> compute(int n, int k){
            ArrayList<String> ret = new ArrayList<>();
            ArrayList<String> arr = new ArrayList<>();
            for(int i = 1; i <= n; i++ )
            {
               arr.add(Integer.toString(i));
            }
            combinations2(arr, k, 0, new String[k],ret);
            return ret;
        }

        private void combinations2(ArrayList<String> arr, int len, int startPosition, String[] result, ArrayList<String> listofAll){
            if (len == 0){
                listofAll.add(Arrays.toString(result).replace("[","").replace("]","").replace(" ",""));
                return;
            }
            for (int i = startPosition; i <= arr.size()-len; i++){
                result[result.length - len] = arr.get(i);
                combinations2(arr, len-1, i+1, result,listofAll);
            }
        }
    }// renvoie le tableau de string de toutes les combinaisons possibles de i dans j

    public static int arcs(int[][] graph) {
        int arcs = 0;
        for (int i = 0; i < graph[0].length; i++) {
            for (int j = 0; j < i; j++) {
                if (graph[i][j] == 1)
                    arcs++;
            }
        }
        return arcs;
    } // renvoie le nombre d'arcs d'un graph à partir de la matrice d'adjacence

    public static void bruteForce(String File) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("src/com/company/Ressources/Formules/"+File));
        String line = br.readLine();
        int nbVar = Integer.parseInt(line.split(" ")[2]);
        int nbClause = Integer.parseInt(line.split(" ")[3]);
//
//        SatParser satParser = new SatParser();
//        satParser.parseFormulas(File);
//        ArrayList<SatFormula> formulas = satParser.getFormulas();
//        int parseVar = satParser.getNb_var();
//        int parseClause = satParser.getNb_clauses();

        for (int i = 0; i < Math.pow(2, nbVar); i++)
        {
            String bin = Integer.toBinaryString(i);
            while (bin.length() < nbVar)
                bin = "0" + bin;
            char[] chars = bin.toCharArray();
            boolean[] boolArray = new boolean[nbVar];
            for (int j = 0; j < chars.length; j++) {
                boolArray[j] = chars[j] == '0' ? true : false;
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/company/Ressources/Affectations/affectsZoneVide.txt"));
            StringBuilder str = new StringBuilder();

            SatParser satParser = new SatParser();
            satParser.parseFormulas(File);

            for (int index = 0; index < boolArray.length; index++)
            {
                if (boolArray[index])
                {
                    str.append(index+1).append(" ");
                }
                else
                {
                    str.append(-(index +1)).append(" ");
                }
            }

            str.deleteCharAt(str.length()-1);
            bw.write(str.toString());
            bw.close();

           satParser.parseAffects("affectsZoneVide.txt");

            SatVerify satVerify = new SatVerify(satParser.getFormula(),nbVar,nbClause);

            if (satVerify.verify())
            {
                System.out.println("affectation qui satisfait la formule : "+str.toString());
                return;
            }
        }
        System.out.println("Aucune affectations trouvées");
    }

}
    

