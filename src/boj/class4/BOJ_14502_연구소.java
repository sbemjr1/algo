package boj.class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int N, M, cnt, ans;
	static int[][] map, map_copy;
	static int[] sel;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static class Point {
		int r,c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		map_copy = new int[N][M];
		ans = Integer.MIN_VALUE;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		sel = new int[3];
		// 조합 생성
		combination(0, 0);

		System.out.println(ans);
	}

	private static void combination(int k, int idx) {
		if (k == 3) {
			copyMap();
			cnt = 0;
			// 시뮬레이션
			// 해당 위치가 0이 아닌 경우 return
			for (int i = 0; i < 3; i++) {
				int r = sel[i] / M;
				int c = sel[i] % M;
				
				if (map_copy[r][c] > 0) {
					return;
				}
				map_copy[r][c] = 1;
			}
			//bfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map_copy[i][j] == 2) {
						bfs(i,j);
					}
				}
			}
			// 안전영역 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map_copy[i][j] == 0) {
						cnt++;
					}
				}
			}
			ans = Integer.max(cnt, ans);
//			if (cnt > ans) {
//				for (int i = 0; i < map_copy.length; i++) {
//					for (int is : map_copy[i]) {
//						System.out.print(is);
//					}
//					System.out.println();
//				}
//				ans = cnt;
//			}
			return;
		}
		if (idx == N * M) {
			return;
		}

		sel[k] = idx;
		combination(k + 1, idx + 1); // 현재 위치를 선택한 경우
		combination(k, idx + 1); // 현재 위치를 선택하지 않은 경우
	}

	private static void bfs(int i, int j) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(i, j));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map_copy[nr][nc] == 0) {
					q.offer(new Point(nr, nc));
					map_copy[nr][nc] = 2;
				}
			}
		}
	}

	private static void copyMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map_copy[r][c] = map[r][c];
			}
		}
	}

}
