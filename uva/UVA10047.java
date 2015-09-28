import java.io.BufferedInputStream;
import java.util.ArrayDeque;
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
	public static final int DIRX_DELTA[] = { -1, 0, 1, 0 };
	public static final int DIRY_DELTA[] = { 0, 1, 0, -1 };
	public static final int COLOR_GREEN = 0;

	class Status {
		final int x, y;
		final int dir;
		final int color;
		int seconds;

		Status(int x, int y, int dir, int color) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.color = color;
			this.seconds = MAX_INT;
		}

	}

	int M, N;
	int sx, sy;
	Status statuses[][][][] = new Status[25][25][NUM_DI][NUM_COLOR];
	char[][] grids = new char[25][];
	ArrayDeque<Status> queue = new ArrayDeque<Status>(100);

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		int i, j, k, m;
		for (i = 0; i < 25; i++) {
			statuses[i] = new Status[25][][];
			grids[i] = new char[25];
			for (j = 0; j < 25; j++) {
				statuses[i][j] = new Status[NUM_DI][];
				for (k = 0; k < NUM_DI; k++) {
					statuses[i][j][k] = new Status[NUM_COLOR];
					for (m = 0; m < NUM_COLOR; m++) {
						statuses[i][j][k][m] = new Status(i, j, k, m);
					}
				}
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
					grids[i][j] = line.charAt(j);
					if (grids[i][j] == 'S') {
						sx = i;
						sy = j;
					}

					for (k = 0; k < NUM_DI; k++)
						for (m = 0; m < NUM_COLOR; m++) {
							statuses[i][j][k][m].seconds = MAX_INT;
						}
				}
			}
			
			if(numCase!=0){
				System.out.println();
			}
			solve(++numCase);
			M = stdin.nextInt();
			N = stdin.nextInt();
		}
		stdin.close();
	}

	void solve(int numCase) {
		Status status = statuses[sx][sy][DI_NORTH][COLOR_GREEN];
		status.seconds = 0;
		queue.clear();
		queue.offer(status);
		while (!queue.isEmpty()) {
			status = queue.remove();
//			System.out.printf("%5d %5d %5d %5d %5d\n", status.x, status.y, status.dir, status.color, status.seconds);
			if (grids[status.x][status.y] == 'T' && status.color == COLOR_GREEN) {
				System.out.printf("Case #%d\nminimum time = %d sec\n", numCase,
						status.seconds);
				return;
			}
			// System.out.println(curGrid);
			addNextStatusesToQueue(status);
		}
		System.out.printf("Case #%d\ndestination not reachable\n", numCase);
	}

	void addNextStatusesToQueue(Status curStatus) {
		// turn right
		int ndir = (curStatus.dir + 1) % NUM_DI;
		Status status = statuses[curStatus.x][curStatus.y][ndir][curStatus.color];
		if (curStatus.seconds + 1 < status.seconds) {
			status.seconds = curStatus.seconds + 1;
			queue.offer(status);
		}
		// turn left
		ndir = (curStatus.dir + 3) % NUM_DI;
		status = statuses[curStatus.x][curStatus.y][ndir][curStatus.color];
		if (curStatus.seconds + 1 < status.seconds) {
			status.seconds = curStatus.seconds + 1;
			queue.offer(status);
		}
		//forward
		int nx = curStatus.x + DIRX_DELTA[curStatus.dir];
		int ny = curStatus.y + DIRY_DELTA[curStatus.dir];
		int ncolor = (curStatus.color + 1) % NUM_COLOR;
		if (nx >= 0 && nx < M && ny >= 0 && ny < N && grids[nx][ny] != '#') {
			status = statuses[nx][ny][curStatus.dir][ncolor];
			if (curStatus.seconds + 1 < status.seconds) {
				status.seconds = curStatus.seconds + 1;
				queue.offer(status);
			}
		}
	}
}
