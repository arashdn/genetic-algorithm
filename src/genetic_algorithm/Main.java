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
        pn.add(3.0, 1);
        pn.add(-3.0, 2);
        Chromosome.setPolynomial(pn);
//        Chromosome ch = new Chromosome(pn.getSize());
//        ch.setGene(0, new Gene(true));
//        ch.setGene(1, new Gene(true));
//        ch.setGene(2, new Gene(true));
//        
//        System.out.println(ch.toString());
//        System.out.println(""+ch.getFitness());
//        
//        Chromosome ch2 = new Chromosome(pn.getSize());
//        ch2.setGene(0, new Gene(true));
//        ch2.setGene(1, new Gene(true));
//        ch2.setGene(2, new Gene(true));
//        
//        System.out.println(ch2.toString());
//        System.out.println(""+ch2.getFitness());
//        
//        Chromosome [] co = (Chromosome[]) ch.crossOver(ch2);
//        System.out.println("After CO:\n"+co[0].toString());
//        System.out.println(""+co[1].toString());
//        
//        System.out.println("Mutate CH : "+ch.mutate().toString());
        
//        if(ch.getFitness()>1000000.0)
//            System.out.println("Answered");
        
        GeneticAlgorithm ga = new GeneticAlgorithm(40, 75, 10, 5, 5);
        System.out.println(ga.toString());
        
        ga.repeat();
        ga.repeat();
        ga.repeat();
        ga.repeat();
        ga.repeat();
        ga.repeat();
        ga.repeat();
        ga.repeat();
        ga.repeat();
        
    }
    
}
