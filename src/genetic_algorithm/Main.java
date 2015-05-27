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
        pn.add(12.0, 1);
        pn.add(-3.0, 5);
        pn.add(12.0, 3);
        pn.add(7.0, 2);
        pn.add(13.0, 3);
        pn.add(-9.0, 4);
        
        Chromosome.setPolynomial(pn);

        Gene.maxValue = 50;
        Gene.minValue = -50;
        Gene.points = 1;
        
        int generations = 100;
        
        Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
        GeneticAlgorithm.setAddExisting(false);
        
        GeneticAlgorithm ga = new GeneticAlgorithm(600, 75, 30, 1, 30);
        System.out.println(ga.toString());
        
        for (int i = 0; i < generations; i++)
        {
            ga.repeat();
        }
        
        
        
    }
    
}
