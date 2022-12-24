import java.text.DecimalFormat;

public class Interface
{
	private Node origin;

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

	// ADDING PONIT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public float addPoint(Function function, int v1, int v2)
	{
		if (v1 == 0 || v2 == 0)
		{
			return Float.NaN;
		}

		String xAxis = v1 > 0 ? "right" : "left";
		String yAxis = v2 > 0 ? "up" : "down";

		Node newNode = new Node(function, v1, v2);
		Node xAxisNode = new Node(new V1Axis(), v1, 0);
		Node yAxisNode = new Node(new V2Axis(), 0, v2);

		xAxisNode = insertAxis(xAxisNode, xAxis);
		yAxisNode = insertAxis(yAxisNode, yAxis);

		if (getPoint(v1, v2) != null)
		{
			newNode = insertNodeLayer(getPoint(v1, v2), newNode);
		}
		else
		{
			newNode = insertNode(newNode, xAxisNode, yAxis);
			newNode = insertNode(newNode, yAxisNode, xAxis);
		}

		return newNode.getValue();
	}
	
	private Node insertAxis(Node node, String position)
	{
		Node prevPtr = null;
		Node currPtr = origin;

		if (position.compareTo("right") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[0] < node.getVariables()[0])
			{
				prevPtr = currPtr;
				currPtr = currPtr.right;

				if (currPtr != null && currPtr.getVariables()[0] == node.getVariables()[0])
				{
					return currPtr;
				}
			}

			prevPtr.right = node;
			node.left = prevPtr;
			node.right = currPtr;

			if (currPtr != null)
			{
				currPtr.left = node;
			}
		}
		else if (position.compareTo("left") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[0] > node.getVariables()[0])
			{
				prevPtr = currPtr;
				currPtr = currPtr.left;

				if (currPtr != null && currPtr.getVariables()[0] == node.getVariables()[0])
				{
					return currPtr;
				}
			}

			prevPtr.left = node;
			node.right = prevPtr;
			node.left = currPtr;
			
			if (currPtr != null)
			{
				currPtr.right = node;
			}
		}
		else if (position.compareTo("up") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[1] < node.getVariables()[1])
			{
				prevPtr = currPtr;
				currPtr = currPtr.up;

				if (currPtr != null && currPtr.getVariables()[1] == node.getVariables()[1])
				{
					return currPtr;
				}
			}

			prevPtr.up = node;
			node.down = prevPtr;
			node.up = currPtr;

			if (currPtr != null)
			{
				currPtr.down = node;
			}
		}
		else if (position.compareTo("down") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[1] > node.getVariables()[1])
			{
				prevPtr = currPtr;
				currPtr = currPtr.down;

				if (currPtr != null && currPtr.getVariables()[1] == node.getVariables()[1])
				{
					return currPtr;
				}
			}
			
			prevPtr.down = node;
			node.up = prevPtr;
			node.down = currPtr;

			if (currPtr != null)
			{
				currPtr.up = node;
			}
		}

		return node;
	}

	private Node insertNode(Node node, Node axisNode, String position)
	{
		Node prevPtr = null;
		Node currPtr = axisNode;

		if (position.compareTo("right") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[0] < node.getVariables()[0])
			{
				prevPtr = currPtr;
				currPtr = currPtr.right;
			}

			if (currPtr == null)
			{
				prevPtr.right = node;
				node.left = prevPtr;
			}
			else
			{
				prevPtr.right = node;
				node.left = prevPtr;
				node.right = currPtr;
				currPtr.left = node;
			}
		}
		else if (position.compareTo("left") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[0] > node.getVariables()[0])
			{
				prevPtr = currPtr;
				currPtr = currPtr.left;
			}

			if (currPtr == null)
			{
				prevPtr.left = node;
				node.right = prevPtr;
			}
			else
			{
				prevPtr.left = node;
				node.right = prevPtr;
				node.left = currPtr;
				currPtr.right = node;
			}
		}
		else if (position.compareTo("up") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[1] < node.getVariables()[1])
			{
				prevPtr = currPtr;
				currPtr = currPtr.up;
			}

			if (currPtr == null)
			{
				prevPtr.up = node;
				node.down = prevPtr;
			}
			else
			{
				prevPtr.up = node;
				node.down = prevPtr;
				node.up = currPtr;
				currPtr.down = node;
			}
		}
		else if (position.compareTo("down") == 0)
		{
			while (currPtr != null && currPtr.getVariables()[1] > node.getVariables()[1])
			{
				prevPtr = currPtr;
				currPtr = currPtr.down;
			}

			if (currPtr == null)
			{
				prevPtr.down = node;
				node.up = prevPtr;
			}
			else
			{
				prevPtr.down = node;
				node.up = prevPtr;
				node.down = currPtr;
				currPtr.up = node;
			}
		}

		return node;
	}

	private Node insertNodeLayer(Node head, Node node)
	{
		Node prevPtr = null;
		Node currPtr = head;

		while (currPtr != null && currPtr.getFunction().functionName.compareTo(node.getFunction().functionName) < 0)
		{
			prevPtr = currPtr;
			currPtr = currPtr.prevVal;
		}

		if (currPtr == null)
		{
			prevPtr.prevVal = node;
			node.nextVal = prevPtr;
		}
		else if (prevPtr == null)
		{
			node.right = head.right;
			node.left = head.left;
			node.up = head.up;
			node.down = head.down;
			node.prevVal = head;
			head.nextVal = node;
		}
		else
		{
			prevPtr.prevVal = node;
			node.nextVal = prevPtr;
			node.prevVal = currPtr;
			currPtr.nextVal = node;
		}

		return node;
	}

	// REMOVING POINT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Node removePoint(int v1, int v2)
	{
		if (v1 == 0 || v2 == 0)
		{
			return null;
		}
		else
		{
			String corner = "";
			Node nodeToBeRemoved = getPoint(v1, v2);

			if (nodeToBeRemoved == null)
			{
				return null;
			}
			else
			{
				deleteNode(nodeToBeRemoved);
			}
			
			if (v1 > 0 && v2 > 0)
			{
				corner = "top right";
			}
			else if (v1 < 0 && v2 > 0)
			{
				corner = "top left";
			}
			else if (v1 > 0 && v2 < 0)
			{
				corner = "bottom right";
			}
			else
			{
				corner = "bottom left";
			}

			deleteAxis(nodeToBeRemoved, corner);

			return nodeToBeRemoved;
		}
	}
	
	private void deleteNode(Node node)
	{
		Node prev = node.left;
		Node next = node.right;
		Node top = node.up;
		Node bottom = node.down;

		if (node.prevVal == null)
		{
			if (prev != null)
			{
				prev.right = next;
			}

			if (next != null)
			{
				next.left = prev;
			}

			if (top != null)
			{
				top.down = bottom;
			}

			if (bottom != null)
			{
				bottom.up = top;
			}
		}
		else
		{
			if (prev != null)
			{
				prev.right = node.prevVal;
			}

			if (next != null)
			{
				next.left = node.prevVal;
			}

			if (top != null)
			{
				top.down = node.prevVal;
			}

			if (bottom != null)
			{
				bottom.up = node.prevVal;
			}
			
			node.prevVal.right = next;
			node.prevVal.left = prev;
			node.prevVal.up = top;
			node.prevVal.down = bottom;

			node.prevVal.nextVal = null;
			node.prevVal = null;
		}
	}

	private void deleteAxis(Node axis, String corner)
	{
		if (corner == "top right" || corner == "bottom left")
		{
			while (axis != null && axis.getVariables()[1] != 0)
			{
			  axis = (corner == "top right") ? axis.down : axis.up;
			}

			if (axis.up == null && axis.down == null)
			{
				deleteNode(axis);
			}
		  
			while (axis != null && axis.getVariables()[0] != 0)
			{
				axis = (corner == "top right") ? axis.left : axis.right;
			}

			if (axis.right == null && axis.left == null)
			{
				deleteNode(axis);
			}
		}
		else if (corner == "top left" || corner == "bottom right")
		{
			while (axis != null && axis.getVariables()[1] != 0)
			{
				axis = (corner == "top left") ? axis.down : axis.up;
			}

			if (axis.up == null && axis.down == null)
			{
				deleteNode(axis);
			}
		
			while (axis != null && axis.getVariables()[0] != 0)
			{
				axis = (corner == "top left") ? axis.right : axis.left;
			}

			if (axis.right == null && axis.left == null)
			{
				deleteNode(axis);
			}
		}
	}

	// UTILITY FUNCTIONS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Node getPoint(int v1, int v2)
	{
		if (v1 == 0 || v2 == 0)
		{
			return null;
		}
		else
		{
			String corner = "";
			Node currPtr = origin;
			
			if (v1 > 0)
			{
				corner = (v2 > 0) ? "top right" : "bottom right";
			}
			else
			{
				corner = (v2 > 0) ? "top left" : "bottom left";
			}
			
			return locateNode(currPtr, v1, v2, corner);
		}
	}
	
	private Node locateNode(Node node, int xAxis, int yAxis, String corner)
	{
		while (node != null && node.getVariables()[0] != xAxis)
		{
			if (corner.equals("top right") || corner.equals("bottom right"))
			{
				node = node.right;
			}
			else
			{
				node = node.left;
			}
		}

		if (node == null)
		{
			return null;
		}
		else
		{
			while (node != null && node.getVariables()[1] != yAxis)
			{
				if (corner.equals("top right") || corner.equals("top left"))
				{
					node = node.up;
				}
				else
				{
					node = node.down;
				}
			}

			if (node == null)
			{
				return null;
			}
			else
			{
				return node;
			}
		}
	}

	public int countNumberOfPoints()
	{
		int[] array = numPointsPerQuadrant();

		int totalNumOfNodes = array[0] + array[1] + array[2] + array[3];

		return totalNumOfNodes;
	}

	public int[] numPointsPerQuadrant()
	{
		int[] numOfNodesInQuadrantArray = new int[4];

		numOfNodesInQuadrantArray[0] = numNodesInQuadrant("top right");
		numOfNodesInQuadrantArray[1] = numNodesInQuadrant("top left");
		numOfNodesInQuadrantArray[2] = numNodesInQuadrant("bottom left");
		numOfNodesInQuadrantArray[3] = numNodesInQuadrant("bottom right");

		return numOfNodesInQuadrantArray;
	}
	
	private int numNodesInQuadrant(String corner)
	{
		int numOfNodes = 0;

		Node horiCurrPtr = null;
		Node vertCurrPtr = null;
		Node depthCurrPtr = null;

		boolean inTop = corner.equals("top right") || corner.equals("top left");
		boolean inRight = corner.equals("top right") || corner.equals("bottom right");

		vertCurrPtr = inTop ? origin.up : origin.down;

		while (vertCurrPtr != null)
		{
			horiCurrPtr = inRight ? vertCurrPtr.right : vertCurrPtr.left;
			
			while (horiCurrPtr != null)
			{
				if (horiCurrPtr.prevVal == null)
				{
					numOfNodes++;
				}
				else
				{
					depthCurrPtr = horiCurrPtr;
					
					while (depthCurrPtr != null)
					{
						numOfNodes++;
						depthCurrPtr = depthCurrPtr.prevVal;
					}
				}

				horiCurrPtr = inRight ? horiCurrPtr.right : horiCurrPtr.left;
			}

			vertCurrPtr = inTop ? vertCurrPtr.up : vertCurrPtr.down;
		}

		return numOfNodes;
	}

	public Node[] toArray()
	{
		int index = 0;
		
		Node currVertPtr = null;
		Node currHorizPtr = origin;
		Node[] arrayOfNodes = new Node[countNumberOfPoints()];

		while (currHorizPtr.right != null)
		{
			currHorizPtr = currHorizPtr.right;
		}

		while (currHorizPtr != null)
		{
			currVertPtr = currHorizPtr;

			while (currVertPtr.up != null)
			{
				currVertPtr = currVertPtr.up;
			}

			while (currVertPtr != null)
			{
				if (currVertPtr.getVariables()[0] != 0 && currVertPtr.getVariables()[1] != 0)
				{
					Node currDepthPtr = currVertPtr;

					while (currDepthPtr != null)
					{
						arrayOfNodes[index++] = currDepthPtr;
						currDepthPtr = currDepthPtr.prevVal;
					}
				}
				
				currVertPtr = currVertPtr.down;
			}
			
			currHorizPtr = currHorizPtr.left;
		}

		return arrayOfNodes;
	}

	public float calculateValue(Function function, int v1, int v2)
	{
		if (function == null)
		{
			return Float.NaN;
		}
		else
		{
			return function.calculate(v1, v2);
		}
	}

	public Node findMax()
	{
		Node[] nodesArray = toArray();

		if (nodesArray.length == 0)
		{
			return null;
		}
		else
		{
			Node node = null;
			Node max = nodesArray[0];

			for (int i = 0; i < nodesArray.length; i++)
			{
				node = nodesArray[i];
				
				if (max.getValue() < node.getValue())
				{
					max = node;
				}
				else if (max.getValue() == node.getValue())
				{
					if (max.getVariables()[0] > node.getVariables()[0])
					{
						max = node;
					}
					else if (max.getVariables()[0] == node.getVariables()[0])
					{
						if (max.getVariables()[1] < node.getVariables()[1])
						{
							max = node;
						}
						else if (max.getVariables()[1] == node.getVariables()[1])
						{
							Node currPtr = node;

							while (currPtr.prevVal != null)
							{
								currPtr = currPtr.prevVal;
							}
							
							max = currPtr;
						}
					}
				}
			}

			return max;
		}
	}
	
	public Node findMin()
	{
		Node[] nodesArray = toArray();

		if (nodesArray.length == 0)
		{
			return null;
		}
		else
		{
			Node node = null;
			Node min = nodesArray[0];

			for (int i = 0; i < nodesArray.length; i++)
			{
				node = nodesArray[i];
				
				if (min.getValue() > node.getValue())
				{
					min = node;
				}
				else if (min.getValue() == node.getValue())
				{
					if (min.getVariables()[0] > node.getVariables()[0])
					{
						min = node;
					}
					else if (min.getVariables()[0] == node.getVariables()[0])
					{
						if (min.getVariables()[1] < node.getVariables()[1])
						{
							min = node;
						}
						else if (min.getVariables()[1] == node.getVariables()[1])
						{
							Node currPtr = node;

							while (currPtr.prevVal != null)
							{
								currPtr = currPtr.prevVal;
							}
							
							min = currPtr;
						}
					}
				}
			}

			return min;
		}
	}

	public float findMaxValue()
	{
		Node highestNode = findMax();

		if (highestNode == null)
		{
			return Float.NaN;
		}
		else
		{
			return highestNode.getValue();
		}
	}


	public float findMinValue()
	{
		Node lowestNode = findMin();

		if (lowestNode == null)
		{
			return Float.NaN;
		}
		else
		{
			return lowestNode.getValue();
		}
	}


	public String printFunctionValues(String functionName)
	{
		String functionValues = "";

		Node currVertPtr = null;
		Node currHorizPtr = origin;

		while (currHorizPtr.left != null)
		{
			currHorizPtr = currHorizPtr.left;
		}

		while (currHorizPtr != null)
		{
			currVertPtr = currHorizPtr;

			while (currVertPtr.down != null)
			{
				currVertPtr = currVertPtr.down;
			}

			while (currVertPtr != null)
			{
				if (currVertPtr.getVariables()[0] != 0 && currVertPtr.getVariables()[1] != 0)
				{
					Node currDepthPtr = currVertPtr;

					while (currDepthPtr != null)
					{
						if (currDepthPtr.getFunction().functionName.equals(functionName) == true)
						{
							functionValues += floatFormatter(currDepthPtr.getValue()) + ";";
						}
						
						currDepthPtr = currDepthPtr.prevVal;
					}
				}
				
				currVertPtr = currVertPtr.up;
			}
			
			currHorizPtr = currHorizPtr.right;
		}

		functionValues = functionValues.substring(0, functionValues.length()-1);

		return functionValues;
	}

	private String floatFormatter(float value)
	{
		DecimalFormat df = new DecimalFormat("#.##");

		return df.format(value);
	}

	public int removeAllFunctionPoints(String functionName)
	{
		int numOfNodesRemoved = 0;

		Node currVertPtr = null;
		Node currHorizPtr = origin;

		while (currHorizPtr.left != null)
		{
			currHorizPtr = currHorizPtr.left;
		}

		while (currHorizPtr != null)
		{
			currVertPtr = currHorizPtr;

			while (currVertPtr.down != null)
			{
				currVertPtr = currVertPtr.down;
			}

			while (currVertPtr != null)
			{
				if (currVertPtr.getVariables()[0] != 0 && currVertPtr.getVariables()[1] != 0)
				{
					Node currDepthPtr = currVertPtr;

					while (currDepthPtr != null)
					{
						if (currDepthPtr.getFunction().functionName.equals(functionName) == true)
						{
							removePoint(currDepthPtr.getVariables()[0], currDepthPtr.getVariables()[1]);
							numOfNodesRemoved++;
						}
						
						currDepthPtr = currDepthPtr.prevVal;
					}
				}
				
				currVertPtr = currVertPtr.up;
			}
			
			currHorizPtr = currHorizPtr.right;
		}

		return numOfNodesRemoved;
	}

	public void clearAllData()
	{
		origin.up = null;
		origin.down = null;
		origin.right = null;
		origin.left = null;
	}
}