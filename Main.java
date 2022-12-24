public class Main {
    public static void main(String[] args)
    {
        System.out.println("Testing Node Class: \n");
        
        Node point = new Node( new AdditionFunction(), 1, 2);

        System.out.println("Function type: " + point.getFunction().functionName);
        System.out.println("Coordinates: X = " + point.getVariables()[0] + " and Y = " + point.getVariables()[1]);
        System.out.println("Value: " + point.getValue());

        point.setFunction(new MultiplyFunction());

        System.out.println("New function type: " + point.getFunction().functionName);
        
        System.out.println("Node links: ");

        String[] links = point.getNodeLinks();

        for (int i = 0; i < links.length; i++)
        {
            System.out.println(links[i]);
        }

        point.up = new Node( new AdditionFunction(), 2, 3);
        point.left = new Node( new MultiplyFunction(), 3, 1);

        System.out.println("New node links: ");

        links = point.getNodeLinks();

        for (int i = 0; i < links.length; i++)
        {
            System.out.println(links[i]);
        }


        System.out.println("Testing Interface: \n");
        
        Interface cartesianPlane = new Interface();

        for (int x = -2; x < 3; x++)
        {
            for (int y = -2; y < 3; y++)
            {
                if (x < y)
                {
                    cartesianPlane.addPoint(new AdditionFunction(), x, y);
                }
                else if (x > y)
                {
                    cartesianPlane.addPoint(new SubtractionFunction(), x, y);
                }
                else
                {
                    cartesianPlane.addPoint(new MultiplyFunction(), x, y);
                }
            }
        }

        System.out.println("Number of nodes: " + cartesianPlane.countNumberOfPoints());
        System.out.println("Nodes with Addition function: ");
        System.out.println(cartesianPlane.printFunctionValues("Addition"));

        for (int x = -2; x < 3; x++)
        {
            for (int y = -2; y < 3; y++)
            {
                if (x == y)
                {
                    cartesianPlane.addPoint(new PowerFunction(), x, y);
                }
            }
        }

        System.out.println("Number of nodes: " + cartesianPlane.countNumberOfPoints());
        System.out.println("Nodes with Power function: ");
        System.out.println(cartesianPlane.printFunctionValues("Power"));

        Node node1 = cartesianPlane.getPoint(1, 2);
        Node node2 = cartesianPlane.getPoint(2, 2);
        Node node3 = cartesianPlane.getPoint(2, 1);

        System.out.println("Node at x=1 and y=2: " + "Value: " + node1.getValue() + " and Function: " + node1.getFunction().functionName);
        System.out.println("Node at x=2 and y=2: " + "Value: " + node2.getValue() + " and Function: " + node2.getFunction().functionName);
        System.out.println("Node at x=3 and y=1: " + "Value: " + node3.getValue() + " and Function: " + node3.getFunction().functionName);

        node1 = cartesianPlane.removePoint(-2, -1);
        node2 = cartesianPlane.removePoint(2, 2);
        node3 = cartesianPlane.removePoint(-1, 2);

        System.out.println("Number of nodes: " + cartesianPlane.countNumberOfPoints());

        System.out.println("Node removed: " + "Value: " + node1.getValue() + " and Function: " + node1.getFunction().functionName);
        System.out.println("Node removed: " + "Value: " + node2.getValue() + " and Function: " + node2.getFunction().functionName);
        System.out.println("Node removed: " + "Value: " + node3.getValue() + " and Function: " + node3.getFunction().functionName);
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
        return v1 + v2;
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
        return v1 - v2;
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
        return v1 * v2;
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
        functionName = "Power";
    }

    public float calculate(int v1, int v2)
    {
        return v1 << v2;
    }

    public Function clone()
    {
        return new PowerFunction();
    }
}