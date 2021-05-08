package connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Connect4 {
	public static int[][] board;

	public static void main(String[] args) {
		FastReader fs = new FastReader();
		board = new int[6][7]; // rows, columns
		while (true) {
			int col;
			boolean placed;
			System.out.println("player 1 turn");
			do {
				col = fs.nextInt();
				placed = place(1, col);
			} while (!placed);
			printboard();
			if (win() == 1) {
				break;
			}
			System.out.println("player 2 turn");
			do {
				col = fs.nextInt();
				placed = place(2, col);
			} while (!placed);
			printboard();
			if (win() == 2) {
				break;
			}
			if (fullboard()) {
				System.out.println("tie");
				System.exit(0);
			}
		}
		System.out.println("player " + win() + " wins");

	}

	public static boolean place(int player, int col) {
		if (col <= -1 || col >= 7) {
			return false;
		}
		for (int i = 5; i >= 0; i--) {
			if (board[i][col] == 0) {
				board[i][col] = player;
				return true;
			}
		}
		return false;
	}

	public static int win() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] != 0) {
					if (i <= 2 && board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j]
							&& board[i + 2][j] == board[i + 3][j]) {
						return board[i][j];
					}
					if (j <= 3 && board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2]
							&& board[i][j + 2] == board[i][j + 3]) {
						return board[i][j];
					}
					if (i <= 2 && j <= 3 && board[i][j] == board[i + 1][j + 1]
							&& board[i + 1][j + 1] == board[i + 2][j + 2]
							&& board[i + 2][j + 2] == board[i + 3][j + 3]) {
						return board[i][j];
					}
					if (i >= 3 && j <= 3 && board[i][j] == board[i - 1][j + 1]
							&& board[i - 1][j + 1] == board[i - 2][j + 2]
							&& board[i - 2][j + 2] == board[i - 3][j + 3]) {
						return board[i][j];
					}
				}
			}
		}
		return 0;
	}

	public static boolean fullboard() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static void printboard() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
