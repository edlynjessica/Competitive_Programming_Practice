// Input: adj[][] = [[2, 3, 1], [0], [0, 4], [0], [2]]
// Output: [0, 2, 4, 3, 1]

/* Q: GFG - DFS of Graph
Given a connected undirected graph containing V vertices represented by a 2-d adjacency list adj[][], 
  where each adj[i] represents the list of vertices connected to vertex i. 
  Perform a Depth First Search (DFS) traversal starting from vertex 0, 
  visiting vertices from left to right as per the given adjacency list, and return a list containing the DFS traversal of the graph.
  Link: https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph
*/

public class GraphDFSRecursive{
    public static void main(String[] args){
        Solution sol=new Solution();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2, 3, 1)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(0, 4)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(2)));
        ArrayList<Integer> answer = sol.dfs(adj);
        for(int i=0;i<answer.size();i++){
            System.out.print(answer.get(i));
            if(i!=answer.size()-1)System.out.print("->");
        }
    }
}
  
class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        int n=adj.size();
        ArrayList<Integer> ans=new ArrayList<>();
        boolean[] visited=new boolean[n];
        dfsTraversal(0,ans,adj,visited);
        return ans;
    }
    public void dfsTraversal(int node,ArrayList<Integer> ans,ArrayList<ArrayList<Integer>> adj,boolean[] visited){
        visited[node]=true;
        ans.add(node);
        for(int neighbour:adj.get(node)){
            if(!visited[neighbour]) dfsTraversal(neighbour,ans,adj,visited);
        }
    }
}
