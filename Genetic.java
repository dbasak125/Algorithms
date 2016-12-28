import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Genetic {
    public static int minMutation(String start, String end, String[] bank) {
    	int len = bank.length;
    	if(len==0) {
    		return -1;
    	}
    	Map<String, Integer> _dist = new HashMap<String, Integer>();
    	String[] bnk = new String[len+1];
    	bnk[0] = start;
    	_dist.put(start,0);
    	for(int i=0; i<len; i++) {
    		bnk[i+1] = bank[i];
    		_dist.put(bank[i], -1);
    	}
    	ArrayList<String> adj;
    	Map<String, ArrayList<String>> _map = new HashMap<String, ArrayList<String>>();
    	for(int i=0; i<len+1; i++) {
            for(int j=i+1; j<len+1; j++) {
                int flag=0;
                char[] a=bnk[i].toCharArray(), b=bnk[j].toCharArray();
                for(int k=0; k<8; k++) {
                    flag = a[k]!=b[k]?flag+1:flag;
                }
                if(flag==1) {
                	adj = new ArrayList<String>();
    				if(_map.get(bnk[i])==null) {
    					adj.add(bnk[j]);
    				} else {
    					adj=_map.get(bnk[i]);
    					boolean t = adj.contains(bnk[j])?true:adj.add(bnk[j]);	
    				}
    				_map.put(bnk[i], adj);
    				adj = new ArrayList<String>();
    				if(_map.get(bnk[j])==null) {
    					adj.add(bnk[i]);
    				} else {
    					adj=_map.get(bnk[j]);
    					boolean t = adj.contains(bnk[i])?true:adj.add(bnk[i]);
    				}
    				_map.put(bnk[j], adj);
                }                
            }
        }
    	
    	//write bfs to find depth of mutations 
    	LinkedList<String> q = new LinkedList<String>();
        q.add(start);
        _dist.put(start, 0);
        while(q.peek() != null && _map.get(q.peek()) != null) {
        	int dist = _dist.get(q.peek());
        	for(String s : _map.get(q.poll())) {
        		if(_dist.get(s) == -1) {
	        		q.add(s);
	        		_dist.put(s, dist+1);
        		}
        	}
        }
    	
    	if(_dist.get(end) != null)
    		return _dist.get(end);
    	else
    		return -1;
    }
    
    public static void main (String[] argc) {
    	String[] str = {"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};
    	int i = minMutation("AAAACCCC","CCCCCCCC",str);
    	System.out.println(i);
    }
}