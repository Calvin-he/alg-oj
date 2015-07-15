import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
	public static void main(String args[]) // entry point from OS
	{

		Main myWork = new Main(); // create a dinamic instance
		myWork.readIn();
		myWork.solve();
	}

	Elephant[] elephants = new Elephant[1010];
	int size = 0;
	Record[] aux = new Record[1010];

	void solve() {
		Arrays.sort(elephants, 0, size, new Comparator<Elephant>() {
			public int compare(Elephant o1, Elephant o2) {
				if (o1.w == o2.w) {
					return o2.s - o1.s;
				} else {
					return o1.w - o2.w;
				}
			}
		});

		printElephants();

		this.aux[0] = new Record(0, 1);
		int si = 1;
		while (si < this.size) {
			Elephant pre = this.elephants[this.aux[si - 1].idx];
			int pren = this.aux[si - 1].n;
			if (this.elephants[si].w > pre.w) {
				if (this.elephants[si].s < pre.s) {
					this.aux[si] = new Record(si, pren + 1);
				} else if (this.elephants[si].s == pre.s) {
					this.aux[si] = this.aux[si - 1];
				} else {
					//this.elephants[si].s > pre.s
					int j;
					for (j = si - 2; j >= 0; --j) {
						if ( this.elephants[si].s < this.elephants[this.aux[j].idx].s) {
							break;
						}
					}
					if (j >= 0) {
						this.aux[si] = new Record(si, this.aux[j].n+1);
					} else {
						this.aux[si] = this.aux[si - 1];
					}
				}
			} else {
				if (this.elephants[si].s <= pre.s) {
					this.aux[si] = this.aux[si - 1];
				} else {
					System.out.println("error");
				}
			}
			++si;
		}

		System.out.println(this.aux[si - 1].n);
	}

	void readIn() {
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int w, s, i = 0;
		while (stdin.hasNextInt()) {
			w = stdin.nextInt();
			s = stdin.nextInt();
			this.elephants[i] = new Elephant(w, s, i+1);
			++i;
		}
		this.size = i;
		stdin.close();
	}

	void printElephants() {
		for (int i = 0; i < size; i++) {
			System.out.println(String.valueOf(this.elephants[i].w) + " "
					+ this.elephants[i].s);
		}
	}

	static class Elephant {
		int w, s, seq;

		Elephant(int w, int s, int seq) {
			this.w = w;
			this.s = s;
			this.seq = seq;
		}
	}

	static class Record {
		int idx;
		int n;

		Record(int idx, int n) {
			this.idx = idx;
			this.n = n;
		}
	}

}
