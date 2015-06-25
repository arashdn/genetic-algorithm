
package genetic_algorithm;

import java.util.ArrayList;

class Mononomial
{
    int power;
    double coefficient;
    String name;

    public Mononomial( double coefficient,int power, String name)
    {
        this.power = power;
        this.coefficient = coefficient;
        this.name = name;
    }

    public Mononomial( double coefficient,int power)
    {
        this.power = power;
        this.coefficient = coefficient;
        this.name = "X";
    }
    
    public Mononomial()
    {
    }
    
    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public double getCoefficient()
    {
        return coefficient;
    }

    public void setCoefficient(double coefficient)
    {
        this.coefficient = coefficient;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    
    
    
}

public class Polynomial
{
    ArrayList<Mononomial> lst;

    public Polynomial()
    {
        lst = new ArrayList<>();
    }
    
    public int getSize()
    {
        return lst.size();
    }
    
    public void add(Double coeff , int pow , String name)
    {
        Mononomial m = new Mononomial(coeff, pow, name);
        lst.add(m);
    }
    
    public void add(Double coeff , int pow)
    {
        Mononomial m = new Mononomial(coeff, pow, "x"+(getSize()+1));
        lst.add(m);
    }
    
    public double getValue(double [] vars) throws ArithmeticException
    {
        if (vars.length != getSize())
                throw new ArithmeticException("Input size is not as same as polynomial size");
        double res = 0;
        for (int i = 0; i < getSize(); i++)
        {
            res += Math.pow(vars[i], lst.get(i).getPower())*lst.get(i).getCoefficient();
        }
        return res;
    }
    
    
    

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < getSize(); i++)
        {
            s += lst.get(i).getCoefficient()+"("+lst.get(i).getName()+")^"+lst.get(i).getPower()+"  +";
        }
        return s;
    }
    
    
    
}
