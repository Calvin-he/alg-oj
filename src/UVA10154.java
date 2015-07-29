import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&
 * category=39&page=show_problem&problem=1095
 * 
 *  I know, up on top you are seeing great sights,
	But down at the bottom, we, too, should have rights.
	We turtles can't stand it. Our shells will all crack!
	Besides, we need food. We are starving!" groaned Mack.
	Mack, in an effort to avoid being cracked, has enlisted your advice as to the order in which turtles
	should be dispatched to form Yertle's throne. Each of the ve thousand, six hundred and seven turtles
	ordered by Yertle has a different weight and strength. Your task is to build the largest stack of turtles
	possible.
	Input
	Standard input consists of several lines, each containing a pair of integers separated by one or more
	space characters, specifying the weight and strength of a turtle. The weight of the turtle is in grams.
	The strength, also in grams, is the turtle's overall carrying capacity, including its own weight. That is,
	a turtle weighing 300g with a strength of 1000g could carry 700g of turtles on its back. There are at
	most 5,607 turtles.
	Output
	Your output is a single integer indicating the maximum number of turtles that can be stacked without
	exceeding the strength of any one.
	SampleInput
	300 1000
	1000 1200
	200 600
	100 101
	SampleOutput
	3
**/
class Main {
	static class Turtle {
		int weight;
		int strength;

		Turtle(int w, int s) {
			weight = w;
			strength = s;
		}
	}


	Turtle[] turtles = new Turtle[5607];
	int size = 0;

	int[] dbt = new int[5608]; //包含i个turtles的最小重量
	int maxNum=0;

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
		myWork.solve();
	}

	void readIn() {
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int i = 0, w, s;
		while (stdin.hasNextInt()) {
			w = stdin.nextInt();
			s = stdin.nextInt();
			turtles[i] = new Turtle(w, s);
			i++;
		}
		size = i;
	}

	void solve() {
		Arrays.sort(turtles, 0, size, new Comparator<Turtle>() {
			public int compare(Turtle o1, Turtle o2) {
				return o1.strength - o2.strength;
			}
		});
		maxNum = 0;
		dbt[maxNum] = 0;
		int i, j, w;
		for (i = 0; i < size; i++) {
			dbt[maxNum+1] = Integer.MAX_VALUE;
			for(j=maxNum; j>=0; j--){
				w = dbt[j] + turtles[i].weight;
				if(w<turtles[i].strength && w<dbt[j+1]){
					dbt[j+1] = w;
					if(j==maxNum){
						maxNum++;
					}
				}
			}
		}
		System.out.println(maxNum);
	}
}
