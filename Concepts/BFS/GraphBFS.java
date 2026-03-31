// Input: adj[][] = [[2, 3, 1], [0], [0, 4], [0], [2]]
// Output: [0, 2, 3, 1, 4]
/*
GFG - BFS of Graph
Q: Given a connected undirected graph containing V vertices, 
represented by a 2-d adjacency list adj[][], where each adj[i] represents the list of vertices connected to vertex i. 
Perform a Breadth First Search (BFS) traversal starting from vertex 0, 
visiting vertices from left to right according to the given adjacency list, and return a list containing the BFS traversal of the graph.
Link: https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph
*/
import java.util.*;
public class GraphBFS{
    public static void main(String[] args){
        Solution sol=new Solution();
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2,3,1)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(0,4)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(2)));
        ArrayList<Integer> answer=sol.bfs(adj);
        for(int i=0;i<answer.size();i++){
            System.out.print(answer.get(i));
            if(i!=answer.size()-1)System.out.print("->");
        }
    }
}
class Solution{
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> ans=new ArrayList<>();
        boolean[] visited=new boolean[adj.size()];
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        while(!q.isEmpty()){
            int node=q.poll();
            if(!visited[node]){
                visited[node]=true;
                ans.add(node);
                for(int neigh:adj.get(node))
                    if(!visited[neigh])q.add(neigh);
            }
        }
        return ans;
    }
}
