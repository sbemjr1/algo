package boj.class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형 {
	static int N, ans;
	static int[][] arr, arr2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		arr2 = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arr2[i][j] = arr[i][j];
			}
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j <= i; j++) {
				arr2[i + 1][j] = Integer.max(arr2[i + 1][j], arr[i + 1][j] + arr2[i][j]);
				arr2[i + 1][j + 1] = Integer.max(arr2[i + 1][j + 1], arr[i + 1][j + 1] + arr2[i][j]);
			}
		}
		
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			ans = Integer.max(ans, arr2[N - 1][i]);
		}
		
		System.out.println(ans);
	}

}
