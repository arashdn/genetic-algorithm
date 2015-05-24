package genetic_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Arash
 */
public class GeneticAlgorithm
{
    final int populationSize;
    int crossOverRate;// Probability in persent
    int mutationRate;
    int elitismRate;
    int TournamentNumber;
    
    
    int repeatCount;

    public int getRepeatCount()
    {
        return repeatCount;
    }

    public Chromosome[] getCurrentGeneration()
    {
        return currentGeneration;
    }

    public Chromosome[] getNextGeneration()
    {
        return nextGeneration;
    }
    
    
    Chromosome [] currentGeneration;
    Chromosome [] nextGeneration;

    public int getPopulationSize()
    {
        return populationSize;
    }

//    public void setPopulationSize(int populationSize)
//    {
//        this.populationSize = populationSize;
//    }

    public int getCrossOverRate()
    {
        return crossOverRate;
    }

    public void setCrossOverRate(int crossOverRate)
    {
        this.crossOverRate = crossOverRate;
    }

    public int getMutationRate()
    {
        return mutationRate;
    }

    public void setMutationRate(int mutationRate)
    {
        this.mutationRate = mutationRate;
    }

    public int getElitismRate()
    {
        return elitismRate;
    }

    public void setElitismRate(int ElitismRate)
    {
        this.elitismRate = ElitismRate;
    }

    public int getTournamentNumber()
    {
        return TournamentNumber;
    }

    public void setTournamentNumber(int TournamentNumber)
    {
        this.TournamentNumber = TournamentNumber;
    }

    public GeneticAlgorithm(int populationSize, int crossOverRate, int mutationRate, int ElitismRate, int TournamentNumber)
    {
        this.populationSize = populationSize;
        this.crossOverRate = crossOverRate;
        this.mutationRate = mutationRate;
        this.elitismRate = ElitismRate;
        this.TournamentNumber = TournamentNumber;
        
        repeatCount = 0;
        
        currentGeneration = new Chromosome[populationSize];//Also randomize it;
        nextGeneration = new Chromosome[populationSize];
        
        for (int i = 0; i < populationSize; i++)
        {
            currentGeneration[i] =new Chromosome(Chromosome.getPolynomial().getSize());
        }
        
    }
    
    public Double getFitnessAverage()
    {
        double res = 0;
        for (int i = 0; i < populationSize; i++)
        {
            res += currentGeneration[i].getFitness();
        }
        res /= populationSize;
        return res;
    }
    
    public Chromosome getBestChromosome()
    {
        double maxFitness = Double.MIN_VALUE;
        Chromosome temp = null;
        for (int i = 0; i < populationSize; i++)
        {
            if(currentGeneration[i].getFitness()>maxFitness)
            {
                maxFitness = currentGeneration[i].getFitness();
                temp = currentGeneration[i];
            }//if
        }//for
        
        return temp;
    }
    
    private Chromosome[] select()
    {
        Chromosome[] temp = new Chromosome[TournamentNumber];
        
        //choose populationSize random uniqe number
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < populationSize; i++)
        {
            nums.add(i);
        }
        Collections.shuffle(nums);
        for (int i = 0; i < TournamentNumber; i++)
        {
            temp[i]=new Chromosome(currentGeneration[nums.get(i)]);
        }
        Arrays.sort(temp);
        Chromosome[] res = new Chromosome[2];
        res[0] = new Chromosome(temp[TournamentNumber-1]);
        res[1] = new Chromosome(temp[TournamentNumber-2]);
        String s = "";
        for (int i = TournamentNumber-1; i > 0; i--)
        {
            s+= temp[i].getFitness()+"  ";
        }
        System.out.println("Selected:"+res[0]+","+res[1]+"  in:"+s);
        return res;
    }
    
    
    public void repeat()
    {
        repeatCount++;
        
        System.out.println("\n--------------------------------------------\nRepeat: "+repeatCount);
        
        Arrays.sort(currentGeneration,Collections.reverseOrder());
        int elitism = (populationSize*elitismRate)/100;
        System.out.println("elitisim count: "+elitism);
        System.out.println("current:  "+this.toString());
        
        for (int i = 0; i < elitism ; i++)
        {
            nextGeneration[i] = new Chromosome(currentGeneration[i]);
            System.out.println("Elitisim: "+nextGeneration[i].toString());
        }


        
        int cop = 0;//Cross Over Probability
        int mp;//mutation Probability
        Random rnd = new Random();
        Chromosome [] sel;
        Chromosome [] temp;
        Chromosome ch1 , ch2;
        for (int i = elitism; i < populationSize; i++)
        {
            cop = rnd.nextInt(100);
            sel = new Chromosome[2];
            sel = select();
            if(cop <= crossOverRate)
            {
                temp = (Chromosome[]) ( sel[0].crossOver(sel[1]) );
                ch1 = temp[0];
                ch2 = temp[1];
                System.out.println("CO= "+ch1+","+ ch2 + "From: "+sel[0]+","+sel[1]);
                //System.out.println("CO: "+ch2);
                
            }
            else
            {
                ch1 = new Chromosome(sel[0]);
                ch2 = new Chromosome(sel[1]);
            }
            
            mp = rnd.nextInt(100);
            if(mp< mutationRate)
            {
                ch1.mutate();
            }
            nextGeneration[i]= new Chromosome(ch1);
            System.out.println("added: "+nextGeneration[i]);
            if(++i<populationSize)
            {
                nextGeneration[i]= new Chromosome(ch2);
                System.out.println("added: "+nextGeneration[i]);
            }
        }//for
        System.out.println("next gen:");
        for (int i = 0; i < populationSize; i++)
        {
            System.out.print(nextGeneration[i]+"  ");
            currentGeneration[i] = new Chromosome(nextGeneration[i]);
        }
        System.out.println("after copy:"+this.toString());
    }//repeat

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < populationSize; i++)
        {
            s += currentGeneration[i].toString()+"   ";
        }
        return "\n-------------------\nGeneticAlgorithm{" + 
                "populationSize=" + populationSize + 
                ", crossOverRate=" + crossOverRate + 
                ", mutationRate=" + mutationRate + 
                ", elitismRate=" + elitismRate + 
                ", TournamentNumber=" + TournamentNumber + 
                ", repeatCount=" + repeatCount +
                ", currentGeneration=\n" + s + 
                "\nAvg fitness: "+String.format("%.04f", getFitnessAverage())+ 
                " Best : "+getBestChromosome().toString()+"  F: "+'}';
    }
    
    
    
}
