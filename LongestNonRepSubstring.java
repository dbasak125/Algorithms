package longestNonRepSubstring;

import java.util.*;

public class LongestNonRepSubstring {
	public static int lengthOfLongestSubstring(String s) {
        if(s.length()==0) return 0;
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int opt[] = new int[ch.length], max=1;
        map.put(ch[0],0);
        for(int i=1; i<s.length(); i++) {
            opt[i] = map.getOrDefault(ch[i],-1) < opt[i-1]?opt[i-1]:map.get(ch[i])+1;
            map.put(ch[i],i);
            max = i+1-opt[i] > max?i+1-opt[i]:max;
        }
        return max;
    }
	
	public static void main(String[] argc) {
		System.out.println(lengthOfLongestSubstring(new String("debaditya")));
	}
}
