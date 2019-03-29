
public class MagicSquare {
	
	public static void main(String[] args){
		MagicSquare square = new MagicSquare(3);
	}
	
	public MagicSquare(int n){
		int[][] sq = new int[n][n];
		if(solve(sq, 0, new boolean[n * n])) {
			System.out.println("Done.");
		}else{
			System.out.println("Fix it.");
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(sq[i][j] + ", ");
				if(j == n -1) System.out.println();
			}
		}
	}
	
	public boolean isSolved(int[][] sq){
		int n = sq.length;
		int checkSum = ((n * n * n) + n ) / 2;
		int rowSum = 0, colSum = 0, diag1Sum = 0, diag2Sum = 0;
		
		
		//Spalten
		for(int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				colSum += sq[i][j];
				if(j == n-1){
					if(colSum != checkSum){
						return false;
					}						
					colSum = 0;
				}
			}
		}
		
		//Zeilen
		for (int i = 0; i < n; n++){
			for(int j = 0; j < n; j++){
				rowSum += sq[j][i];
				if(j == n-1){
					if(rowSum != checkSum){
						return false;
					}
					rowSum = 0;
				}
			}
		}
		
		for(int i = 0; i < n; i++){
			diag1Sum += sq[i][i];
		}
		
		if(diag1Sum != checkSum) return false;
		
		int count = n - 1;
		for(int i = 0; i < n; i++){
			diag2Sum += sq[count][i];
			count--;
		}
		
		if(diag2Sum != checkSum) return false;
		
		
		return true;
	}

	
	public boolean solve(int[][] sq, int pos, boolean[] used){
		
		int n = sq.length;
		int col = pos % n;
		int row = (pos - col) / n;
		
		//Basisfall
		if(pos >= used.length) {
			return isSolved(sq);
		}else{		
			//Rekursion
			for(int i = 1; i < used.length; i++){
				if(used[i-1] == false) {
					sq[col][row] = i;
					used[i-1] = true;
					if(solve(sq, pos+1, used)){
						return true;
					}
					sq[col][row] = (int) (used.length * (Math.random())+1);
					used[i-1] = false;
				}			
			}
		}		
		return false;
	}
}
