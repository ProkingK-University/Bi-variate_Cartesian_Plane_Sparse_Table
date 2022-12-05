import java.text.DecimalFormat;

public class Node {

	private int v1;
	private int v2;

	public Node left;
	public Node right;

	public Node up;
	public Node down;

	public Node nextVal;
	public Node prevVal;

	private Function nodeFunction;

	private String floatFormatter(float value)
	{
		DecimalFormat df = new DecimalFormat("#.##");

		return df.format(value);
	}

	public Node(Function function, int v1, int v2)
	{
		this.v1 = v1;
		this.v2 = v2;
		
		left = null;
		right = null;
		
		up = null;
		down = null;

		nextVal = null;
		prevVal = null;

		nodeFunction = function.clone();
	}

	public Function setFunction(Function function)
	{
		Function original = nodeFunction;
		nodeFunction = function;

		return original;
	}

	public float getValue()
	{
		if (nodeFunction == null)
		{
			return Float.NEGATIVE_INFINITY;
		}
		else
		{
			return nodeFunction.calculate(v1, v2);
		}
	}

	public int[] getVariables()
	{
		int[] arrayOfValues = {v1, v2};

		return arrayOfValues;
	}

	public Function getFunction()
	{
		return nodeFunction;
	}

	public String[] getNodeLinks()
	{
		String[] nodeLinks = new String[6];

		if (up != null)
		{
			nodeLinks[0] = "U[" + up.v1 + "][" + up.v2 + "]{" + up.floatFormatter(up.getValue());
		}
		else
		{
			nodeLinks[0] = "U[][]{}";
		}

		if (down != null)
		{
			nodeLinks[1] = "D[" + down.v1 + "][" + down.v2 + "]{" + down.floatFormatter(down.getValue());
		}
		else
		{
			nodeLinks[1] = "D[][]{}";
		}

		if (right != null)
		{
			nodeLinks[2] = "R[" + right.v1 + "][" + right.v2 + "]{" + right.floatFormatter(right.getValue());
		}
		else
		{
			nodeLinks[2] = "R[][]{}";
		}

		if (left != null)
		{
			nodeLinks[3] = "L[" + left.v1 + "][" + left.v2 + "]{" + left.floatFormatter(left.getValue());
		}
		else
		{
			nodeLinks[3] = "L[][]{}";
		}

		if (prevVal != null)
		{
			nodeLinks[4] = "P[" + prevVal.v1 + "][" + prevVal.v2 + "]{" + prevVal.floatFormatter(prevVal.getValue());
		}
		else
		{
			nodeLinks[4] = "P[][]{}";
		}

		if (nextVal != null)
		{
			nodeLinks[5] = "N[" + nextVal.v1 + "][" + nextVal.v2 + "]{" + nextVal.floatFormatter(nextVal.getValue());
		}
		else
		{
			nodeLinks[5] = "N[][]{}";
		}

		return nodeLinks;
	}

}