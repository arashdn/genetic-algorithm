package genetic_algorithm;

public interface IGene <T>
{
    public T getValue();
    public void setValue(T value);
    public void random();
}
