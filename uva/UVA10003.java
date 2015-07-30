import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=39&page=show_problem&problem=944
 * 
 * @author hexian
 *
 */

class Main {
	int length;
	int[] cuts = new int[52];
	int size;
	int[] dpt[] = new int[52][];

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		for (int i = 0; i < this.dpt.length; i++) {
			this.dpt[i] = new int[1000];
			Arrays.fill(this.dpt[i], 0);
		}

		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		while ((this.length = stdin.nextInt())!=0) {
			this.size = stdin.nextInt() + 2;
			cuts[0] = 0;
			cuts[this.size - 1] = this.length;
			for (int i = 1; i <= size - 2; i++) {
				this.cuts[i] = stdin.nextInt();
			}
			this.solve();
		}
		stdin.close();
	}

	void solve() {
		Arrays.sort(cuts, 1, size-1);
		int i, d;
		for (d = 0; d < size - 2; d++) {
			for (i = 1; i < size - 1 - d; i++) {
				dpt[i][i + d] = calCost(i, i + d);
			}
		}
		System.out.printf("The minimum cutting is %d.\n", dpt[1][size - 2]);
	}

	int calCost(int s, int e) {
		int min = Integer.MAX_VALUE;
		for (int k = s; k <= e; k++) {
			int temp = dpt[s][k - 1] + dpt[k + 1][e];
			if (temp < min) {
				min = temp;
			}
		}
		return min + cuts[e + 1] - cuts[s - 1];
	}
}
