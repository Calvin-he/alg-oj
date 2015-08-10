import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=39&page=show_problem&problem=1142
 * 
 * @author hexian
 *
 */
class Main {
	static class Station {
		int dist, price;
	}

	static final int FULL_TANK = 200;
	static final int HALF_TANK = 100;

	int distance;
	Station[] stations = new Station[101];
	int numStations;

	int[] dp[] = new int[100][];

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}

	void readIn() {
		for (int i = 0; i < stations.length; i++) {
			stations[i] = new Station();
		}
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[FULL_TANK + 1];
		}

		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int numCase = Integer.parseInt(stdin.nextLine().trim());
		while (stdin.hasNext()) {
			String line = stdin.nextLine().trim();
			if (line.length() == 0) {
				continue;
			}
			int spaceIdx = line.indexOf(' ');
			if (spaceIdx == -1) {
				if (distance > 0) {
					solve();
					System.out.println();
				}
				numStations = 0;
				distance = Integer.parseInt(line.trim());
			} else {
				stations[numStations].dist = Integer.parseInt(line.substring(0,
						spaceIdx).trim());
				if (stations[numStations].dist <= distance) {
					stations[numStations].price = Integer.parseInt(line
							.substring(spaceIdx + 1).trim());
					numStations++;
				}
			}
		}
		solve();
		stdin.close();
	}

	void solve() {
		int i, j, k;
		if (isPossible()) {
			for (i = 0; i <= FULL_TANK; i++) {
				int remain = i + stations[0].dist - HALF_TANK;
				if (remain >= 0) {
					dp[0][i] = remain * stations[0].price;
				} else {
					dp[0][i] = 0;
				}
			}

			for (i = 1; i < numStations; i++) {
				int curDist = stations[i].dist - stations[i - 1].dist;
				for (j = 0; j <= FULL_TANK; j++) {
					int min = Integer.MAX_VALUE;
					for (k = 0; k <= j; k++) {
						int preLeft = k + curDist;
						if (preLeft <= FULL_TANK) {
							int temp = dp[i - 1][preLeft] + (j - k)
									* stations[i].price;
							if (temp < min) {
								min = temp;
							}
						}
					}
					dp[i][j] = min;
				}
			}

			int lastDist = distance - stations[numStations - 1].dist;
			System.out.println(dp[numStations - 1][lastDist + HALF_TANK]);

		} else {
			System.out.println("Impossible");
		}
	}

	boolean isPossible() {
		if (numStations == 0) {
			return false;
		}

		if (stations[0].dist > HALF_TANK)
			return false;
		for (int i = 1; i < numStations; i++) {
			if (stations[i].dist - stations[i - 1].dist > FULL_TANK) {
				return false;
			}
		}
		if (distance - stations[numStations - 1].dist > HALF_TANK) {
			return false;
		}
		return true;
	}

}
