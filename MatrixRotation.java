package test;

class MatrixRotation {
	public static void main(String[] args) {
		matrixRotation(new int[][]{{1,2,3,10,11},{4,5,6,12,13},{7,8,9,14,15},{10,11,12,16,17},{13,14,15,18,19}});
		//matrixRotation(new int[][]{{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}});
	}

	public static void matrixRotation(int[][] square) {
		if(square.length != square[0].length || square.length == 0) {
			System.out.println("incorrect input");
			return;
		}
		
		for(int i=0; i<square.length; i++) {
			for(int j=0; j<square.length; j++) {
				System.out.print(square[i][j]+"\t");
			}
			System.out.print("\n\n");
		}
		
		System.out.println("\n");

		int start = 0;

		for(int j = 0; j<square.length/2; j++) {
			for(int i = 0; i < square.length - 1 - 2*j; i++) {
				start = square[j][j+i];
				square[j][j+i] = square[square.length-1-j-i][j];
				square[square.length-1-j-i][j] = square[square.length-1-j][square.length-1-j-i];
				square[square.length-1-j][square.length-1-j-i] = square[j+i][square.length-1-j];
				square[j+i][square.length-1-j] = start;
			}
		}
		for(int i=0; i<square.length; i++) {
			for(int j=0; j<square.length; j++) {
				System.out.print(square[i][j]+"\t");
			}
			System.out.print("\n\n");
		}
	}
}