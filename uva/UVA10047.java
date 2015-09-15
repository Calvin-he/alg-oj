import java.io.BufferedInputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&
 * category=40&page=show_problem&problem=988
 * 
 * @author hexian
 *
 */
class Main {
	public static final int NUM_COLOR = 5;
	public static final int NUM_DI = 4;
	public static final int MAX_INT = 9999999;
	public static final int DI_NORTH = 0;
	public static final int DI_EAST = 1;
	public static final int DI_SOUTH = 2;
	public static final int DI_WEST = 3;

	class Grid {
		int direcions[][] = new int[NUM_DI][NUM_COLOR];
		char ch;

		Grid() {
			for (int i = 0; i < NUM_DI; i++) {
				direcions[i] = new int[NUM_COLOR];
			}
		}

		boolean blocked() {
			return ch == '#';
		}

		@Override
		public String toString() {
			return String.valueOf(ch);
		}

	}

	class ProcessingGrid {
		final int x, y;
		final int di;
		final int color;
		final int seconds;

		ProcessingGrid(int x, int y, int di, int seconds, int color) {
			this.x = x;
			this.y = y;
			this.di = di;
			this.seconds = seconds;
			this.color = color;
		}

		boolean terminated() {
			return grids[x][y].ch == 'T' && color == 0;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("x=" + x);
			sb.append("; y=" + y);
			sb.append("; di=" + di);
			sb.append("; seconds=" + seconds);
			return sb.toString();
		}

		ProcessingGrid[] nextProcessingGrids() {
			ProcessingGrid nextGrids[] = new ProcessingGrid[4];
			int size = 0;
			int nextSeconds = seconds + 1;

			int left = (di - 1) >= 0 ? (di - 1) : 3;
			if (MAX_INT == grids[x][y].direcions[left][color]) {
				grids[x][y].direcions[left][color] = nextSeconds;
				nextGrids[size++] = new ProcessingGrid(x, y, left, nextSeconds,
						color);
			}
			int right = (di + 1) < 4 ? (di + 1) : 0;
			if (MAX_INT == grids[x][y].direcions[right][color]) {
				grids[x][y].direcions[right][color] = nextSeconds;
				nextGrids[size++] = new ProcessingGrid(x, y, right,
						nextSeconds, color);
			}

			int nx = x, ny = y;
			switch (di) {
			case DI_NORTH:
				nx--;
				break;
			case DI_EAST:
				ny++;
				break;
			case DI_SOUTH:
				nx++;
				break;
			case DI_WEST:
				ny--;
				break;
			}
			int ncolor = (color + 1 < NUM_COLOR) ? (color + 1) : 0;
			if (nx >= 0 && nx < M && ny >= 0 && ny < N
					&& !grids[nx][ny].blocked()) {
				if (MAX_INT == grids[nx][ny].direcions[di][ncolor]) {
					grids[nx][ny].direcions[di][color] = nextSeconds;
					nextGrids[size++] = new ProcessingGrid(nx, ny, di,
							nextSeconds, ncolor);
				}
			}
			return nextGrids;

		}

	}

	int M, N;
	int sx, sy, tx, ty;
	Grid[][] grids = new Grid[25][];
	Queue<ProcessingGrid> queue = new ArrayDeque<ProcessingGrid>(100);

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		int i, j;
		for (i = 0; i < grids.length; i++) {
			grids[i] = new Grid[25];
			for (j = 0; j < grids[i].length; j++) {
				grids[i][j] = new Grid();
			}
		}

		Scanner stdin = new Scanner(new BufferedInputStream(System.in));

		int numCase = 0;
		M = stdin.nextInt();
		N = stdin.nextInt();
		while (M != 0) {
			stdin.nextLine();
			for (i = 0; i < M; i++) {
				String line = stdin.nextLine();
				for (j = 0; j < N; j++) {
					Grid g = grids[i][j];
					g.ch = line.charAt(j);
					if (g.ch == 'S') {
						sx = i;
						sy = j;
					}

					for (int k = 0; k < NUM_DI; k++)
						for (int m = 0; m < NUM_COLOR; m++) {
							g.direcions[k][m] = MAX_INT;
						}
				}
			}

			solve(++numCase);
			M = stdin.nextInt();
			N = stdin.nextInt();
		}
		stdin.close();
	}

	void solve(int numCase) {
		queue.clear();
		grids[sx][sy].direcions[DI_NORTH][0] = 0;
		queue.add(new ProcessingGrid(sx, sy, DI_NORTH, 0, 0));
		ProcessingGrid curGrid = null;
		while (!queue.isEmpty()) {
			curGrid = queue.remove();
			// System.out.println(curGrid);
			ProcessingGrid[] nextGrids = curGrid.nextProcessingGrids();

			int i = 0;
			while (nextGrids[i] != null) {
				if (nextGrids[i].terminated()) {
					System.out.printf("Case #%d\nminimum time = %d sec\n",
							numCase, nextGrids[i].seconds);
					return;
				} else {
					queue.add(nextGrids[i]);
				}
				i++;

			}
		}
		System.out.printf("Case #%d\ndestination not reachable\n", numCase);
	}
}
