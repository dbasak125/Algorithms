package test;

class OneAway {
	public static void main(String[] args) {
		oneAway("pale","bale");
	}

	public static void oneAway(String str1, String str2) {
		if(Math.abs(str1.length()-str2.length())>1) {
			System.out.println("no");
			return;
		}

		int cntr = str1.length() <= str2.length()? str2.length():str1.length();
		int edit=0;

		for(int i=0, j=0; i<cntr; i++, j++) {
			if(str1.charAt(i) == str2.charAt(j)) {
				continue;
			} else if(str1.charAt(i) != str2.charAt(j) && str1.length() == str2.length()) {
				edit++;
				if(edit==2) {
					System.out.println("no");
					return;
				}
			} else if(str1.charAt(i) != str2.charAt(j) && str1.length() != str2.length()) {
				j--;
				edit++;
				if(edit==2) {
					System.out.println("no");
					return;
				}
			}
		}
		System.out.println("yes");
		return;
	}
}

/*

mclaren
mcl ren

equal and diff
	next inequality triggers no

noneq and diff
	introduce space in shorter string and continue checking inequality
	next inequality triggers no

all passes 
	then yes

*/