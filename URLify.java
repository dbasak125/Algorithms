package test;

class URLify {
	public static void main(String[] args) {
		urlify("My name is John ");
	}

	public static void urlify(String s) {
		int len = s.length();
		int spaceCount=0;
		
		for(int i=0; i<len; i++) {
			if(s.charAt(i)==' ') {
				spaceCount++;
			}
		}

		char[] resultStr = new char[len+2*spaceCount];

		for(int i=0, j=0; i<len; i++) {
			if(!(s.charAt(i)==' ')) {
				resultStr[j++] = s.charAt(i);
			} else {
				resultStr[j++] = '%';
				resultStr[j++] = '2';
				resultStr[j++] = '0';
			}
		}

		System.out.println(resultStr);
	}
}