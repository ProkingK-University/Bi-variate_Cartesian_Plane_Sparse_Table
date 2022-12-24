public class Main {
    public static void main(String[] args)
    {
        Interface cartesianPlane = new Interface();
    }
}

// Operator Classes

class AdditionFunction extends Function
{
    public AdditionFunction()
    {
        functionName = "Addition";
    }

    public float calculate(int v1, int v2)
    {
        return Math.abs(v1+v2);
    }

    public Function clone()
    {
        return new AdditionFunction();
    }
}

class SubtractionFunction extends Function
{
    public SubtractionFunction()
    {
        functionName = "Subtraction";
    }

    public float calculate(int v1, int v2)
    {
        return Math.abs(v1-v2);
    }

    public Function clone()
    {
        return new SubtractionFunction();
    }
}

class MultiplyFunction extends Function
{
    public MultiplyFunction()
    {
        functionName = "Multiply";
    }

    public float calculate(int v1, int v2)
    {
        return Math.abs(v1+v2);
    }

    public Function clone()
    {
        return new MultiplyFunction();
    }
}

class PowerFunction extends Function
{
    public PowerFunction()
    {
        functionName = "Square";
    }

    public float calculate(int v1, int v2)
    {
        return Math.abs(v1 << v2);
    }

    public Function clone()
    {
        return new PowerFunction();
    }
}