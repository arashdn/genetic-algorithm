package genetic_algorithm;

import java.util.Random;

public class Gene implements IGene<Double>
{

    Double value;
    public static int minValue = -400;
    public static int maxValue = 400;
    public static int points = 2;

    public Gene()
    {
        random();
    }
    
    public Gene(boolean rand)
    {
        if(rand)
            random();
    }
    
    public Gene(Double value)
    {
        this.value = value;
    }
    
    

    @Override
    public Double getValue()
    {
        return value;
    }

    @Override
    public void setValue(Double value)
    {
        this.value = value;
    }
    

    @Override
    public void random()
    {
        
        int pt = 1;
        for (int i = 0; i < points; i++)
        {
            pt *= 10;
        }
        Random r = new Random();
        value  = (double)r.nextInt((maxValue-minValue)*pt);
        value /= pt;
        value += minValue;
    }
    
    
}
