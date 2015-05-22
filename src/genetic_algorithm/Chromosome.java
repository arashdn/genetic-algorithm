package genetic_algorithm;

import java.util.Random;


public class Chromosome implements IChromosome
{
    int size;
    IGene [] genes = null;
    private static Polynomial polynomial = null;

    public static Polynomial getPolynomial()
    {
        return polynomial;
    }

    public static void setPolynomial(Polynomial polynomial) 
    {
        Chromosome.polynomial = polynomial;
    }
    
    
    
    

    public Chromosome(int s)
    {
        setSize(s);
    }

    
    @Override
    public int getSize()
    {
        return size;
    }
    
    @Override
    public void setSize(int size)
    {
        this.size = size;
        genes = new IGene[size];
    }
    
    @Override
    public IGene[] getGenes()
    {
        return genes;
    }

    @Override
    public void setGenes(IGene[] genes)
    {
        this.genes = genes;
    }
    
    @Override
    public IGene getGene(int i) throws NullPointerException , ArrayIndexOutOfBoundsException
    {
        if(genes == null)
            throw new NullPointerException("Genes are empty");
        if(i>=size || i<0)
            throw new ArrayIndexOutOfBoundsException("I out of range");
        return genes[i];
    }
    public void setGene(int i , IGene val) throws NullPointerException , ArrayIndexOutOfBoundsException
    {
        if(genes == null)
            throw new NullPointerException("Genes are empty");
        if(i>=size || i<0)
            throw new ArrayIndexOutOfBoundsException("I out of range");
        genes[i] = val;
    }
    


    @Override
    public double getFitness() throws IllegalArgumentException
    {
        if(getSize() != polynomial.getSize())
            throw new IllegalArgumentException("Sizes are not same");
        double [] vals = new double[size];
        for (int i = 0; i < size; i++)
        {
            vals[i] = (double)getGene(i).getValue();
        }
        double res = polynomial.getValue(vals);
        return Math.abs(1/res);
    }

    @Override
    public IChromosome[] crossOver(IChromosome c2)
    {
        int cop = new Random().nextInt(size-2);//cross over point
        cop++;
        Chromosome [] res = new Chromosome[2];
        res[0] = new Chromosome(size);
        res[1] = new Chromosome(size);
        for (int i = 0; i < cop; i++)
        {
            res[0].setGene(i, new Gene((double)c2.getGene(i).getValue()));
            res[1].setGene(i, new Gene((double)getGene(i).getValue()));
        }
        for (int i = cop; i < size; i++)
        {
            res[0].setGene(i, new Gene((double)getGene(i).getValue()));
            res[1].setGene(i, new Gene((double)c2.getGene(i).getValue()));
        }
        return res;
    }

    @Override
    public IChromosome mutate()
    {
        Chromosome ch = new Chromosome(size);
        for (int i = 0; i < size; i++)
        {
            ch.setGene(i, new Gene((double)getGene(i).getValue()));
        }
        int m = new Random().nextInt(size);
        ch.getGene(m).random();
        return ch;
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < size; i++)
        {
            s += "["+genes[i].getValue()+"]";
        }
        return "Chromosome{" + "size=" + size + ", genes= " + s + '}';
    }
    
    
    
}
