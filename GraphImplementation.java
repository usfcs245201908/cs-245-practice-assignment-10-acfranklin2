import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph {
	
	private int[][] matrix;

	public GraphImplementation(int vertices) {
		matrix = new int[vertices][vertices];
	}

	public void addEdge(int v1, int v2)
	{
		try{
			if((v1 >= matrix[0].length) || (v2 >= matrix[0].length) || (v1 < 0) || (v2 < 0))
				throw new Exception("This didn't work.");
		}
		catch(Exception e){};
		matrix[v1][v2] = 1;
	}

	public List<Integer> topologicalSort(){
		int vertices = matrix[0].length;
		int[] sum = new int[vertices];
		for(int i = 0; i < vertices; i++)
			for(int j = 0; j < vertices; j++)
				if(matrix[j][i] == 1)
					sum[i]++;
		List<Integer> topOrder = new ArrayList<Integer>();
		for(int count = 0; count < vertices; count++) {
			int n = findZero(sum);
			topOrder.add(n);
			sum[n] = -1;
			for(int c2 = 0; c2 < neighbors(n).size(); c2++)
				sum[neighbors(n).get(c2)]--;
		}
		return(topOrder);
	}

	public int findZero(int[] arr)
	{
		for(int k = 0; k < arr.length; k++)
			if(arr[k] == 0)
				return(k);
		return(-1);
	}

	public List<Integer> neighbors(int vertex)
	{
		//This figures out which vertices are directly connected to the root vertex in question.
		try {
			if(vertex > matrix[0].length)
				throw new Exception("Vertex number does not fit into matrix.");
		}
		catch(Exception e){};
		List<Integer> neighbors = new ArrayList<Integer>();
		for(int i = 0; i < matrix[0].length; i++) 
			if((matrix[vertex][i] == 1) && (i != vertex))
				neighbors.add(i);
		return(neighbors);
	}
}