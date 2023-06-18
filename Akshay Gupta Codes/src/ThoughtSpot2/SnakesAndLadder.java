/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThoughtSpot2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author guptaakshay
 */

class SnakesAndLadder {
   public int snakesAndLadders(int[][] board) {
		int n = board.length;
		int src = 1;
        int[] srcPos = convert(n, src);
        int dest = n * n;
		int[] destPos = convert(n, dest);
		int count = 0;
		boolean[][] vis = new boolean[n][n];
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		vis[srcPos[0]][srcPos[1]] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				int curr = q.poll();
				int[] currPos = convert(n, curr);

				if (currPos[0] == destPos[0] && currPos[1] == destPos[1]) {
					return count;
				}

				for (int i = 1; i <= 6; i++) {
					int next = curr + i;
					if (next <= n * n) {
                        int[] nextPos = convert(n, next);
						if (board[nextPos[0]][nextPos[1]] != -1) {
							next = board[nextPos[0]][nextPos[1]];
                            nextPos = convert(n, next);
						}
                        if(!vis[nextPos[0]][nextPos[1]]){
                            vis[nextPos[0]][nextPos[1]] = true;
                            q.add(next);                            
                        }
					}
				}
			}
			count++;
		}
		return -1;
	}

	private int[] convert(int n, int position) {
		int rowFromEnd = position / n;
		if (position % n == 0) {
			rowFromEnd--;
		}
		int rowFromBeginning = (n - 1) - rowFromEnd;

		int col = (position % n) - 1;
		if (position % n == 0) {
			col += n;
		}

		if (rowFromEnd % 2 != 0) {
			col = (n - 1) - col; // from left
		}
		return new int[] { rowFromBeginning, col };
	}
}