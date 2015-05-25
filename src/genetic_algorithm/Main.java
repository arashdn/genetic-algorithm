package genetic_algorithm;

/**
 *
 * @author Arash
 */
public class Main 
{
    public static void main(String[] args) 
    {
        Polynomial pn = new Polynomial();
        pn.add(5.0, 2);
        pn.add(20.0, 1);
        pn.add(-3.0, 5);
        
        Chromosome.setPolynomial(pn);

        Gene.maxValue = 50;
        Gene.minValue = -50;
        Gene.points = 1;
        
        int generations = 100;
        
        GeneticAlgorithm ga = new GeneticAlgorithm(600, 75, 60, 1, 30,false);
        System.out.println(ga.toString());
        
        for (int i = 0; i < generations; i++)
        {
            ga.repeat();
        }
        
        
        
    }
    
}
