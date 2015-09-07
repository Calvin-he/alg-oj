import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&
 * category=40&page=show_problem&problem=1102
 * 
 * @author hexian
 *
 */
class Main {

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int num;
		while ((num = stdin.nextInt()) != 0) {
			solve(num);
		}
		stdin.close();
	}

	void solve(int num) {
		int i, x, y;
		for (i = 1; i * i < num; i++)
			;
		int t = num - (i - 1) * (i - 1);
		if (i % 2 == 0) {
			if (t < i) {
				x = t;
				y = i;
			} else {
				x = i;
				y = i - (t - i);
			}
		} else {
			if (t < i) {
				x = i;
				y = t;
			} else {
				x = i - (t - i);
				y = i;
			}
		}

		System.out.printf("%d %d\n", x, y);
	}
}
