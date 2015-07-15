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
	int auxSize = 0;

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
		
		this.aux[0] = new Record(this.elephants[0], 1);
		this.auxSize = 1;
		int si = 1;
		while (si < this.size) {
			Elephant cur = this.elephants[si];
			int i;
			for(i = auxSize-1; i>=0; --i){
				if(aux[i].elephant.s > cur.s){
					break;
				}
			}
			if(i>=0){
				if(aux[i].elephant.w < cur.w){
					aux[i+1] = new Record(cur, aux[i].n+1);
					if(i==auxSize-1){
						auxSize++;
					}
				}else{
					aux[i] = new Record(cur, aux[i].n);
				}
			}
			
			System.out.printf("Round %d's record:\n", si);
			printRecords();	
			++si;
			
		}

		System.out.println(this.aux[auxSize-1].n);
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
					+ this.elephants[i].s + " " + this.elephants[i].seq);
		}
	}
	
	void printRecords(){
		for(int i=0; i<auxSize; i++){
			System.out.println(String.valueOf(this.aux[i].elephant.w) + " "
					+ this.aux[i].elephant.s + " " + this.aux[i].elephant.seq);
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
		Elephant elephant;
		int n;

		Record(Elephant e, int n) {
			this.elephant = e;
			this.n = n;
		}
	}

}
