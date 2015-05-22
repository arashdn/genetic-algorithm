
package genetic_algorithm;

public interface IChromosome
{
    
    public int getSize();
    public void setSize(int s);
    
    public IGene[] getGenes();
    public void setGenes(IGene[] genes);
    
    public IGene getGene(int i);
    public void setGene(int i , IGene val);
    
    public double getFitness();
    
    public IChromosome[] crossOver(IChromosome c2);
    public IChromosome mutate();
    
}
