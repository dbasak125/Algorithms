 /**
 * Program to calculate the Minimum Spanning Tree of a connected Graph
 * 
 * @author dbasak
 * @email  dbasak@buffalo.edu
 * @date   29-Oct-2016
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Prims {
	
	private static int a,b,c,v,e;
	private static String[] edge;
	private static int[] weight;
	private static int[] keymap;
	private static int[] _heap;
	private static int s;
	private static int cnt=0;
	private static String _mst = "";
	private static int d;
	
	
	private static int extractMin() {
		int ret=_heap[1];
		_heap[1]=_heap[s];
		keymap[_heap[1]]=1;
		s-=1;
		if(s>1) {
			heapifyDown(1);
		}
		return ret;
	}
	
	private static void heapifyUp(int i) {
		int j,c;
		while(i>1) {
			j=i/2;
			if(weight[_heap[i]] < weight[_heap[j]]) {
				c=_heap[j];
				_heap[j]=_heap[i];
				_heap[i]=c;
				keymap[_heap[i]]=i;
				keymap[_heap[j]]=j;
				i=j;
			} else
				break;
		}
	}

	private static void heapifyDown(int i) {
		int j=0,c=0;
		while(2*i<=s) {
			if((2*i==s) || weight[_heap[2*i]] <= weight[_heap[2*i+1]])
				j=2*i;
			else
				j=2*i+1;
			if(weight[_heap[j]] < weight[_heap[i]]) {
				c=_heap[j];
				_heap[j]=_heap[i];
				_heap[i]=c;
				keymap[_heap[i]]=i;
				keymap[_heap[j]]=j;
				i=j;
			} else
				break;
		}
	}
	
	private static void decreaseKey(int v, int key_value) {
		weight[v]=key_value;
		heapifyUp(keymap[v]);
	}
	
	public static void main(String args[]) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
			Scanner sc = new Scanner(br);
			
			HashMap<Integer, ArrayList<Integer>> _map= new HashMap<Integer, ArrayList<Integer>>();
			ArrayList<Integer> adj;
			v=sc.nextInt();
			e=sc.nextInt();
			edge=new String[v+1];
			weight=new int[v+1];
			keymap=new int[v+1];
			_heap = new int[v+1];
			s = v;
			
			for(int j=0; j<e; j++) {
				a=sc.nextInt();
				b=sc.nextInt();
				c=sc.nextInt();
				adj = new ArrayList<Integer>();
				if(_map.get(a)==null) {
					adj.add(b);	
					adj.add(c);	
				} else {
					adj=_map.get(a);
					adj.add(b);	
					adj.add(c);
				}
				_map.put(a, adj);
				adj = new ArrayList<Integer>();
				if(_map.get(b)==null) {
					adj.add(a);	
					adj.add(c);
				} else {
					adj=_map.get(b);
					adj.add(a);	
					adj.add(c);
				}
				_map.put(b, adj);
			}
			
			for(int i=1; i<=s; i++) {
				_heap[i]=i;
				weight[i]=(int)10e5+1;
				keymap[i]=i;
			}
			weight[1]=0;
			
			int nnode;
			int nweight;
			int mstWeight=0;
			
			while(s>0) {
				cnt++;
				
				//extract least weighted node, and record weight in mstWeight
				d=extractMin();
				keymap[d]=0;
				mstWeight+=weight[d];
				
				//put edge details in mst result set
				if(cnt>1)
					_mst+=edge[d]+"\n";
				
				//explore all neighbors of the extracted node,
				//decrease key_value in <_heap> if new edge weight is less that current known weight
				for(int i=0; i<_map.get(d).size(); i+=2) {
					nnode=_map.get(d).get(i);
					nweight=_map.get(d).get(i+1);
					if(keymap[nnode]>0 && nweight < weight[nnode]) {
						decreaseKey(nnode, nweight);
						edge[nnode]=d+" "+nnode;
					}
				}
			}
			_mst=mstWeight+"\n"+_mst;
			
			bw.write(_mst);
			bw.flush();
			br.close();
			bw.close();
			sc.close();
		} catch (Exception Exception) {
			Exception.printStackTrace();
			System.out.println("Error!!");
		}
	}
}
