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

        Node[] arrayOfNodes = cartesianPlane.toArray();

        for (int i = 0; i < arrayOfNodes.length; i++)
        {
            if (i != arrayOfNodes.length-1)
            {
                System.out.print(arrayOfNodes[i].getValue() + ";");
            }
            else
            {
                System.out.println(arrayOfNodes[i].getValue());
            }
        }

        cartesianPlane = new Interface(arrayOfNodes);

        System.out.println("Value: " + cartesianPlane.calculateValue(new PowerFunction(), 2, 3));

        System.out.println("Max value: " + cartesianPlane.findMaxValue());
        System.out.println("Min value: " + cartesianPlane.findMinValue());

        int numOfRemovedNodes = cartesianPlane.removeAllFunctionPoints("Addition");

        System.out.println(numOfRemovedNodes);
        System.out.println(cartesianPlane.printFunctionValues("Subtraction"));

        int[] numOfNodesPerQuadrant = cartesianPlane.numPointsPerQuadrant();

        for (int i = 0; i < numOfNodesPerQuadrant.length; i++)
        {
            if (i != numOfNodesPerQuadrant.length-1)
            {
                System.out.print(numOfNodesPerQuadrant[i]+ " ");
            }
            else
            {
                System.out.println(numOfNodesPerQuadrant[i]);
            }
        }

        cartesianPlane.clearAllData();

        Node originNode = cartesianPlane.getOrigin();

        if (originNode.up == null && originNode.down == null && originNode.right == null &&originNode.left == null)
        {
            System.out.println("Cleared");
        }
        else
        {
            System.out.println("Error");
        }

        System.out.println("\nCompleted");
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
        return (float) Math.pow(v1, v2);
    }

    public Function clone()
    {
        return new PowerFunction();
    }
}

// Expected output

/*
Testing Node Class: 

Function type: Addition
Coordinates: X = 1 and Y = 2
Value: 3.0
New function type: Multiply
Node links: 
U[][]{}
D[][]{}
R[][]{}
L[][]{}
P[][]{}
N[][]{}
New node links: 
U[2][3]{5}
D[][]{}
R[][]{}
L[3][1]{3}
P[][]{}
N[][]{}
Testing Interface: 

Number of nodes: 16
Nodes with Addition function: 
-3;-1;0;0;1;3
Number of nodes: 20
Nodes with Power function: 
0,25;-1;1;4
Node at x=1 and y=2: Value: 3.0 and Function: Addition
Node at x=2 and y=2: Value: 4.0 and Function: Multiply
Node at x=3 and y=1: Value: 1.0 and Function: Subtraction
Number of nodes: 17
Node removed: Value: -3.0 and Function: Addition
Node removed: Value: 4.0 and Function: Multiply
Node removed: Value: 1.0 and Function: Addition
4.0;1.0;3.0;4.0;3.0;1.0;1.0;2.0;3.0;0.0;1.0;-1.0;1.0;0.0;-1.0;4.0;0.25
Value: 8.0
Max value: 4.0
Min value: -1.0
4
1;3;2;4;3;1
4 0 5 4
Cleared

Completed
*/