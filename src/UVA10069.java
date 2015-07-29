import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=39&page=show_problem&problem=1010
 */
class Main {
	String X, Z;
	BigInteger dp[][];

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void solve() {
		int i, j;
		for (i = 1; i <= X.length(); i++) {
			for (j = 1; j <= Z.length(); j++) {
				if(X.charAt(i-1) != Z.charAt(j-1)){
					dp[i][j] = dp[i-1][j];
				}else{
					dp[i][j] = dp[i-1][j].add(dp[i-1][j-1]);
				}
			}
		}
		System.out.println(dp[X.length()][Z.length()]);
	}

	void readIn() {
		dp = new BigInteger[10001][101];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new BigInteger[101];
			dp[i][0] = BigInteger.ONE;
		}
		for(int i=1; i<dp[0].length; i++){
			dp[0][i] = BigInteger.ZERO;
		}

		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int N = stdin.nextInt();
		while (N > 0) {
			X = stdin.next();
			Z = stdin.next();
			solve();
			--N;
		}

		stdin.close();
	}
}
