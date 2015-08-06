import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&category=39&problem=1212&mosmsg=Submission+received+with+ID+15886010
 * 
 * reverse the array at first
 * 
 * @author hexian
 *
 */
class Main {
	int K, N;
	int chopsticks[] = new int[5001];
	int dp[][] = new int[5001][];

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[1001];
		}

		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int numCase = stdin.nextInt();
		while (numCase > 0) {
			K = stdin.nextInt() + 8;
			N = stdin.nextInt();
			for (int i = N; i > 0; i--) {
				chopsticks[i] = stdin.nextInt();
			}
			solve();
			numCase--;
		}
	}

	void solve() {
		int i, j;
		for (i = 1; i <= N; i++) {
			for (j = 1; j <= K; j++) {
				if (j * 3 <= i) {
					int temp = dp[i - 2][j - 1]
							+ (chopsticks[i] - chopsticks[i - 1])
							* (chopsticks[i] - chopsticks[i - 1]);
					dp[i][j] = dp[i - 1][j] < temp ? dp[i - 1][j] : temp;
				}else{
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
