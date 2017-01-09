package test;

import java.util.*;

//bfs/dfs for directed graph

public class BFS_DFS_Traversal {
	public static void main(String[] args) {
		Graph g = new Graph();
		
		g.add(0,1);
		g.add(0,2);
		g.add(1,2);
		g.add(2,0);
		g.add(2,3);
		g.add(3,3);
		
		System.out.println("bfs:");
		g.traversebfs(2);
		System.out.println("dfs:");
		g.traversedfs(2);
	}
}

class Graph {
	private Map<Integer, ArrayList<Integer>> _map = new HashMap<Integer, ArrayList<Integer>>();
	
	private boolean flag = false;
	
	public void add(int a, int b) {
		ArrayList<Integer> adj = new ArrayList<Integer>();
		if(_map.get(a) != null) {
			adj = _map.get(a);
			adj.add(b);
		} else {
			adj.add(b);
		}
		_map.put(a, adj);
		
		flag = !flag;
		
		//if(!flag) return;
		//else add(b,a);
	}
	
	public void traversebfs(int a) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		
		HashSet<Integer> s = new HashSet<Integer>();
		q.add(a);
		s.add(a);
		System.out.println(a);
		
		while(q.peek() != null) {
			for(int i : _map.get(q.peek())) {
				if(s.contains(i)) {
					continue;
				} else {
					s.add(i);
					System.out.println(i);
					q.add(i);
				}
			}
			q.poll();
		}
	}
	
	private static HashSet<Integer> visited = new HashSet<Integer>();
	
	public void traversedfs(int a) {
		System.out.println(a);
		Iterator<Integer> i = _map.get(a).listIterator();
		visited.add(a);
		while(i.hasNext()) {
			int x = i.next();
			if(!visited.contains(x))
				traversedfs(x);
		}
	}
}
