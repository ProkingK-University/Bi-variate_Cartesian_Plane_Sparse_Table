import java.text.DecimalFormat;

public class Interface
{
	private Node origin;

	private String floatFormatter(float value)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	public Interface()
	{
		Origin originFunction = new Origin();
		origin = new Node(originFunction, 0, 0);
	}

	public Interface(Node[] arr)
	{
		Origin originFunction = new Origin();
		origin = new Node(originFunction, 0, 0);

		for(Node node : arr)
		{
			if (node != null)
			{
				addPoint(node.getFunction(), node.getVariables()[0], node.getVariables()[1]);
			}
		}
	}

	public Node getOrigin()
	{
		return origin;
	}

	public float addPoint(Function function, int v1, int v2)
	{
		if (v1 == 0 || v2 == 0)
		{
			return Float.NaN;
		}
		else
		{
			boolean alreadyExists = false;

			Node newAxis;
			Node tempNode;
			Node prevPtr = null;
			Node currPtr = origin;
			
			Node newNode = new Node(function, v1, v2);

			if (v1 > 0)
			{
				newAxis = new Node(new V1Axis(), v1, 0);

				while (currPtr != null && currPtr.getValue() < v1)
				{
					if (currPtr.getVariables()[0] == v1)
					{
						alreadyExists = true;
						break;
					}

					prevPtr = currPtr;
					currPtr = currPtr.right;
				}
	
				if (alreadyExists == false)
				{
					prevPtr.right = newAxis;
					newAxis.right = currPtr;
					newAxis.left = prevPtr;
	
					newAxis.up = newNode;
					newNode.down = newAxis;
				}
				else
				{
					tempNode = currPtr.up;

					prevPtr = null;
					currPtr = tempNode;

					if (tempNode.getVariables()[1] == v2)
					{
						while (currPtr != null && currPtr.getFunction().functionName.compareTo(function.functionName) < 0)
						{
							prevPtr = currPtr;
							currPtr = currPtr.prevVal;
						}

						if (prevPtr == null)
						{
							newNode.up = tempNode.up;
							newNode.down = tempNode.down;

							tempNode.up = null;
							tempNode.down = null;

							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
						else
						{
							prevPtr.prevVal = newNode;
							newNode.nextVal = prevPtr;
							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
					}
					else
					{
						currPtr.up = newNode;
						newNode.up = tempNode;
						tempNode.down = newNode;
						newNode.down = currPtr;
					}
				}
			}
			else
			{
				newAxis = new Node(new V1Axis(), v1, 0);

				while (currPtr != null && currPtr.getValue() > v1)
				{
					if (currPtr.getVariables()[0] == v1)
					{
						alreadyExists = true;
						break;
					}
					
					prevPtr = currPtr;
					currPtr = currPtr.left;
				}

				if (alreadyExists == false)
				{
					prevPtr.left = newAxis;
					newAxis.left = currPtr;
					newAxis.right = prevPtr;
	
					newAxis.down = newNode;
					newNode.up = newAxis;
				}
				else
				{
					tempNode = currPtr.down;

					prevPtr = null;
					currPtr = tempNode;

					if (tempNode.getVariables()[1] == v2)
					{
						while (currPtr != null && currPtr.getFunction().functionName.compareTo(function.functionName) < 0)
						{
							prevPtr = currPtr;
							currPtr = currPtr.prevVal;
						}

						if (prevPtr == null)
						{
							newNode.up = tempNode.up;
							newNode.down = tempNode.down;

							tempNode.up = null;
							tempNode.down = null;

							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
						else
						{
							prevPtr.prevVal = newNode;
							newNode.nextVal = prevPtr;
							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
					}
					else
					{
						currPtr.down = newNode;
						newNode.down = tempNode;
						tempNode.up = newNode;
						newNode.up = currPtr;
					}

				}
			}

			if (v2 > 0)
			{
				newAxis = new Node(new V2Axis(), 0, v2);

				while (currPtr != null && currPtr.getValue() < v1)
				{
					if (currPtr.getVariables()[1] == v2)
					{
						alreadyExists = true;
						break;
					}
					
					prevPtr = currPtr;
					currPtr = currPtr.up;
				}
	
				if (alreadyExists == false)
				{
					prevPtr.up = newAxis;
					newAxis.up = currPtr;
					newAxis.down = prevPtr;
	
					newAxis.right = newNode;
					newNode.left = newAxis;
				}
				else
				{
					tempNode = currPtr.right;

					prevPtr = null;
					currPtr = tempNode;

					if (tempNode.getVariables()[0] == v1)
					{
						while (currPtr != null && currPtr.getFunction().functionName.compareTo(function.functionName) < 0)
						{
							prevPtr = currPtr;
							currPtr = currPtr.prevVal;
						}

						if (prevPtr == null)
						{
							newNode.right = tempNode.right;
							newNode.left = tempNode.left;

							tempNode.right = null;
							tempNode.left = null;

							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
						else
						{
							prevPtr.prevVal = newNode;
							newNode.nextVal = prevPtr;
							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
					}
					else
					{
						currPtr.right = newNode;
						newNode.right = tempNode;
						tempNode.left = newNode;
						newNode.left = currPtr;
					}
				}
			}
			else
			{
				newAxis = new Node(new V2Axis(), 0, v2);

				while (currPtr != null && currPtr.getValue() > v2)
				{
					if (currPtr.getVariables()[1] == v2)
					{
						alreadyExists = true;
						break;
					}
					
					prevPtr = currPtr;
					currPtr = currPtr.down;
				}
	
				if (alreadyExists == false)
				{
					prevPtr.down = newAxis;
					newAxis.down = currPtr;
					newAxis.up = prevPtr;
	
					newAxis.left = newNode;
					newNode.right = newAxis;
				}
				else
				{
					tempNode = currPtr.left;

					prevPtr = null;
					currPtr = tempNode;

					if (tempNode.getVariables()[0] == v1)
					{
						while (currPtr != null && currPtr.getFunction().functionName.compareTo(function.functionName) < 0)
						{
							prevPtr = currPtr;
							currPtr = currPtr.prevVal;
						}

						if (prevPtr == null)
						{
							newNode.left = tempNode.left;
							newNode.right = tempNode.right;

							tempNode.left = null;
							tempNode.right = null;

							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
						else
						{
							prevPtr.prevVal = newNode;
							newNode.nextVal = prevPtr;
							newNode.prevVal = currPtr;
							currPtr.nextVal = newNode;
						}
					}
					else
					{
						currPtr.left = newNode;
						newNode.left = tempNode;
						tempNode.right = newNode;
						newNode.right = currPtr;
					}
				}
			}

			return newNode.getValue();
		}
	}

	public Node removePoint(int v1, int v2)
	{
		return null;
	}

	public Node getPoint(int v1, int v2)
	{
		return null;
	}

	public Node[] toArray()
	{
		return null;
	}

	public float calculateValue(Function function, int v1, int v2)
	{
		return 0;
	}

	public float findMaxValue()
	{
		return 0;
	}

	public Node findMax()
	{
		return null;
	}

	public float findMinValue()
	{
		return 0;
	}

	public Node findMin()
	{
		return null;
	}

	public String printFunctionValues(String functionName)
	{
		return null;
	}

	public int removeAllFunctionPoints(String functionName)
	{
		return 0;
	}

	public int countNumberOfPoints()
	{
		return 0;
	}

	public int[] numPointsPerQuadrant()
	{
		int[] array = {0};

		return array;
	}

	public void clearAllData()
	{

	}
}