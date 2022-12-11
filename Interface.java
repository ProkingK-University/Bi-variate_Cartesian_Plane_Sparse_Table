import javax.lang.model.util.ElementScanner14;
import javax.swing.text.Position;

//import java.text.DecimalFormat;

public class Interface
{
	private Node origin;

	/*private String floatFormatter(float value)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}*/

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

	private Node insertAxis(Node node, String position)
	{
		boolean alreadyExists = false;

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
					alreadyExists = true;
					break;
				}
			}

			if (alreadyExists == false)
			{
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
			else
			{
				return currPtr;
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
					alreadyExists = true;
					break;
				}
			}

			if (alreadyExists == false)
			{
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
			else
			{
				return currPtr;
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
					alreadyExists = true;
					break;
				}
			}

			if (alreadyExists == false)
			{
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
			else
			{
				return currPtr;
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
					alreadyExists = true;
					break;
				}
			}

			if (alreadyExists == false)
			{
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
			else
			{
				return currPtr;
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

	public float addPoint(Function function, int v1, int v2)
	{
		Node newNode = null;
		
		if (v1 == 0 || v2 == 0)
		{
			return Float.NaN;
		}
		else if (getPoint(v1, v2) != null)
		{
			newNode = new Node(function, v1, v2);

			newNode = insertNodeLayer(getPoint(v1, v2), newNode);

			return newNode.getValue();
		}
		else
		{
			String xAxis = "";
			String yAxis = "";

			newNode = new Node(function, v1, v2);

			Node xAxisNode = new Node(new V1Axis(), v1, 0);
			Node yAxisNode = new Node(new V2Axis(), 0, v2);

			if (v1 > 0 && v2 > 0)
			{
				xAxis = "right";
				yAxis = "up";
			}
			else if (v1 < 0 && v2 > 0)
			{
				xAxis = "left";
				yAxis = "up";
			}
			else if (v1 > 0 && v2 < 0)
			{
				xAxis = "right";
				yAxis = "down";
			}
			else
			{
				xAxis = "left";
				yAxis = "down";
			}

			xAxisNode = insertAxis(xAxisNode, xAxis);
			yAxisNode = insertAxis(yAxisNode, yAxis);

			newNode = insertNode(newNode, xAxisNode, yAxis);
			newNode = insertNode(newNode, yAxisNode, xAxis);

			return newNode.getValue();
		}
	}

// REMOVING POINT
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void deleteNode(Node node)
	{
		if (node.prevVal == null)
		{
			Node prev = node.left;
			Node next = node.right;
			Node top = node.up;
			Node bottom = node.down;

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
				bottom.up = top;
			}

			if (bottom != null)
			{
				top.down = bottom;
			}
		}
		else
		{
			node.prevVal.right = node.right;
			node.prevVal.left = node.left;
			node.prevVal.up = node.up;
			node.prevVal.down = node.down;

			node.prevVal.nextVal = null;
			node.prevVal = null;
		}
	}

	private void deleteAxis(Node axis, String corner)
	{
		if (corner == "top right")
		{
			while (axis.getVariables()[0] != 0)
			{
				axis = axis.down;
			}

			if (axis.up == null && axis.down == null)
			{
				deleteNode(axis);
			}

			while (axis.getVariables()[1] != 0)
			{
				axis = axis.left;
			}

			if (axis.right == null && axis.left == null)
			{
				deleteNode(axis);
			}
		}
		else if (corner == "bottom right")
		{
			while (axis.getVariables()[0] != 0)
			{
				axis = axis.up;
			}

			if (axis.up == null && axis.down == null)
			{
				deleteNode(axis);
			}

			while (axis.getVariables()[1] != 0)
			{
				axis = axis.left;
			}

			if (axis.right == null && axis.left == null)
			{
				deleteNode(axis);
			}
		}
		else if (corner == "bottom left")
		{
			while (axis.getVariables()[0] != 0)
			{
				axis = axis.up;
			}

			if (axis.up == null && axis.down == null)
			{
				deleteNode(axis);
			}

			while (axis.getVariables()[1] != 0)
			{
				axis = axis.right;
			}

			if (axis.right == null && axis.left == null)
			{
				deleteNode(axis);
			}
		}
		else if (corner == "top left")
		{
			while (axis.getVariables()[0] != 0)
			{
				axis = axis.down;
			}

			if (axis.up == null && axis.down == null)
			{
				deleteNode(axis);
			}

			while (axis.getVariables()[1] != 0)
			{
				axis = axis.right;
			}

			if (axis.right == null && axis.left == null)
			{
				deleteNode(axis);
			}
		}
	}

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
		return;
	}
}