import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 *  <problem_url>
 * 
 * @author hexian
 *
 */
class Main {

	public static void main(String args[]) {

		Main myWork = new Main();
		myWork.readIn();
	}
	
	void readIn(){
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		int numCase = stdin.nextInt();
		for(int cur=1; cur<=numCase; cur++){
			
			
			solve();
		}
		stdin.close();
	}
	
	void solve(){
		
	}
}
