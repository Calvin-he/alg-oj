import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
	Elephant[] elephants = new Elephant[1010];
	int size = 0;
	int[] aux = new int[1010];

	static class Elephant {
		int w, s, seq;

		Elephant(int w, int s, int seq) {
			this.w = w;
			this.s = s;
			this.seq = seq;
		}

		boolean higherThan(Elephant o) {
			return (w > o.w && s < o.s);
		}
	}

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
		myWork.solve();
	}

	void solve() {
		Arrays.sort(elephants, 0, size, new Comparator<Elephant>() {
			public int compare(Elephant o1, Elephant o2) {
				return o1.w - o2.w;
			}
		});
		// printElephants();
		this.aux[0] = 1;
		int si = 1, i;
		while (si < this.size) {
			aux[si] = 1;
			for (i = 0; i < si; ++i) {
				if (elephants[si].higherThan(elephants[i]) && aux[si] <= aux[i]) {
					aux[si] = aux[i] + 1;
				}
			}
			++si;
		}
		printRecords();
	}

	void readIn() {
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int w, s, i = 0;
		while (stdin.hasNextInt()) {
			w = stdin.nextInt();
			s = stdin.nextInt();
			this.elephants[i] = new Elephant(w, s, i + 1);
			++i;
		}
		this.size = i;
		stdin.close();
	}

	void printElephants() {
		for (int i = 0; i < size; i++) {
			System.out.println(String.valueOf(this.elephants[i].w) + " "
					+ this.elephants[i].s + " " + this.elephants[i].seq);
		}
	}

	void printRecords() {
		int maxi = 0, i;
		for (i = 0; i < size; i++) {
			if (aux[i] > aux[maxi]) {
				maxi = i;
			}
		}
		int[] arr = new int[aux[maxi]];
		arr[aux[maxi] - 1] = elephants[maxi].seq;
		for (i = maxi - 1; i >= 0; i--) {
			if (aux[i] == aux[maxi] - 1
					&& elephants[maxi].higherThan(elephants[i])) {
				maxi = i;
				arr[aux[maxi] - 1] = elephants[maxi].seq;
				if (aux[maxi] == 1) {
					break;
				}
			}
		}
		System.out.println(arr.length);
		for (i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
