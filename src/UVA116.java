import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * 
 * @author hexiang
 * 
 *         https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8
 *         &category=39&page=show_problem&problem=52
 *
 */

class Main {

	int[][] Matrix = new int[10][];
	int nRow = 0, nCol = 0;

	int[][] preRowMatrix = new int[10][];
	int[][] minWeightPathMatrix = new int[10][];

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		int i, j;
		for (i = 0; i < Matrix.length; i++) {
			Matrix[i] = new int[100];
			minWeightPathMatrix[i] = new int[100];
			preRowMatrix[i] = new int[100];
		}
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		while (stdin.hasNextInt()) {
			nRow = stdin.nextInt();
			nCol = stdin.nextInt();
			for (i = 0; i < nRow; i++) {
				for (j = 0; j < nCol; j++) {
					Matrix[i][j] = stdin.nextInt();
				}
			}
			solve();
		}
		stdin.close();
	}

	void solve() {
		int r, c;
		// compute from right to left for the reason:
		// " If there is more than one path of minimal weight the path that is lexicographically smallest should be output. "
		for (r = 0; r < nRow; r++) {
			minWeightPathMatrix[r][nCol - 1] = Matrix[r][nCol - 1];
			preRowMatrix[r][nCol - 1] = -1;
		}

		for (c = nCol - 2; c >= 0; c--) {
			for (r = 0; r < nRow; r++) {
				int preRow = preMinRow(r, c);
				minWeightPathMatrix[r][c] = minWeightPathMatrix[preRow][c + 1]
						+ Matrix[r][c];
				preRowMatrix[r][c] = preRow;
			}
		}

		int firstRow = 0;
		for (r = 1; r < nRow; r++) {
			if (minWeightPathMatrix[r][0] < minWeightPathMatrix[firstRow][0]) {
				firstRow = r;
			}
		}

		r = firstRow;
		System.out.print(r + 1);
		for (c = 0; c < nCol - 1; c++) {
			r = preRowMatrix[r][c];
			System.out.print(" " + (r + 1));
		}
		System.out.println();
		System.out.println(minWeightPathMatrix[firstRow][0]);
	}

	int preMinRow(int row, int col) {
		int min = row;
		int preCol = col + 1;
		int up = (row - 1 >= 0) ? (row - 1) : (nRow - 1);
		if (minWeightPathMatrix[up][preCol] < minWeightPathMatrix[min][preCol]
				|| (up < min && minWeightPathMatrix[up][preCol] == minWeightPathMatrix[min][preCol])) {
			min = up;
		}

		int down = (row + 1 < nRow) ? (row + 1) : 0;
		if (minWeightPathMatrix[down][preCol] < minWeightPathMatrix[min][preCol]
				|| (down < min && minWeightPathMatrix[down][preCol] == minWeightPathMatrix[min][preCol])) {
			min = down;
		}
		return min;
	}
}
