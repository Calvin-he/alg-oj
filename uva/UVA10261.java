import java.io.BufferedInputStream;
import java.util.Scanner;
/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=39&page=show_problem&problem=1202
 * 
 * @author hexian
 *
 */

class Main {
	int length;
	int[] cars = new int[500];
	int sizeOfCars;
	
	int[] sum = new int[500];
	int[][] dp = new int[500][];
	
	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}
	
	void readIn(){
		for(int i=0; i<dp.length; i++){
			 dp[i] = new int[10020];
		}
		for(int j=0; j<=this.length; j++){
			dp[0][j] = 0;
		}
		dp[0][0] = 1;
		
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int numCase = stdin.nextInt();
		
		while(numCase>0){
			cars[0] = sum[0] = 0;
			sizeOfCars = 1;
			length = stdin.nextInt() * 100;
			int temp = stdin.nextInt();
			while(temp != 0){
				cars[sizeOfCars] = temp;
				sum[sizeOfCars] = sum[sizeOfCars-1] + temp;
				temp = stdin.nextInt();
				sizeOfCars++;
			}
			solve();
			numCase--;
			if(numCase!=0)
				System.out.println();
		}
	}
	
	void solve(){
		int i,j;
		int maxSize=0, maxLength=0;
		for(i=1; i<this.sizeOfCars; i++){
			for(j=0; j<=this.length; j++){
				if((j>=cars[i] && dp[i-1][j-cars[i]]==1) || 
						(sum[i]-j<=length && dp[i-1][j]==1)){
					dp[i][j] = 1;
					if(maxSize<i){
						maxSize = i;
						maxLength= j;
					}
				}else{
					dp[i][j] = 0;
				}
			}
		}
		
		System.out.println(maxSize);
		printResult(maxSize, maxLength);
	}
	
	void printResult(int i, int leftLength){
		if(i==0) return;
		if(leftLength>=cars[i] && dp[i-1][leftLength-cars[i]]==1){
			printResult(i-1, leftLength-cars[i]);
			System.out.println("port");
		}else{
			printResult(i-1, leftLength);
			System.out.println("starboard");
		}
		
	}
}
