import java.util.*;

class StringPermutationLexicoOrder {
	static Map<String, Integer> _map = new HashMap<String, Integer>();
	
	//test module
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i=0; i<t; i++) {
		    String s = sort(sc.next());
		    perm(s,0,s.length());
		    System.out.print("\n");
		}
		sc.close();
	}
	
	//calculate permutations using recursion
	static void perm(String x, int i, int j) {
		if(j==1 && _map.getOrDefault(x,-1)==-1) {
	    	System.out.print(x+" ");
	    	_map.put(x,1);
	    } else {
    	    for(int k=i; k<i+j; k++) {
    	    	x = i<k?x.substring(0,i)+x.charAt(k)+x.substring(i+1,k)+x.charAt(i)+x.substring(k+1):x;
    	    	perm(x,i+1,j-1);
    	    }
	    }
	}
	
	//string merge-sort
	static String sort(String s) {
	    if(s.length()<2) return s;
	    char[] al = sort(s.substring(0,s.length()/2)).toCharArray();
	    char[] ar = sort(s.substring(s.length()/2)).toCharArray();
	    char[] merg = new char[al.length+ar.length];
	    int i=0, j=0, k=0;
	    while(i<al.length && j<ar.length) {
	        if(al[i]<ar[j]) {
	            merg[k++] = al[i++];
	        } else if(al[i]>ar[j]) {
	            merg[k++] = ar[j++];
	        } else {
	            merg[k++] = al[i++];
	            merg[k++] = ar[j++];
	        }
	    }
	    while(i<al.length) merg[k++]=al[i++];
	    while(j<ar.length) merg[k++]=ar[j++];
	    return new String(merg);
	}
}
