package boj.class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는수열 {
	static int N;
	static ArrayList<Integer> list;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			flag = true;
			if (list.isEmpty()) {
				list.add(a);
			} else {
				for (int j = 0; j < list.size(); j++) {
					if (a <= list.get(j)) {
						list.remove(j);
						list.add(j, a);
						flag = false;
						break;
					}
				}
				if (flag) {
					list.add(a);
				}
			}
//
//			System.out.println(list.toString());
		}
		System.out.println(list.size());
	}

}
