import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {
	static int T;
	static int k;
	static int magnet[][];
	static int magnetNumber;
	static int direction;
	static List<Magnet> rotateMagnetList;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			k = Integer.parseInt(br.readLine());

			magnet = new int[5][9];
			rotateMagnetList = new ArrayList<>();
			answer = 0;

			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				magnetNumber = Integer.parseInt(st.nextToken());
				direction = Integer.parseInt(st.nextToken());
				rotateMagnetList.clear();

				if (direction == 1) {
					magnetCheck(magnetNumber, direction);

					// 기준 자석 먼저 회전
					rotate(magnetNumber, 1);

					// 연결된 자석 회전
					for (int m = 0; m < rotateMagnetList.size(); m++) {
						rotate(rotateMagnetList.get(m).number, rotateMagnetList.get(m).direction);
					}

				} else if (direction == -1) {
					magnetCheck(magnetNumber, direction);

					// 기준 자석 먼저 회전
					rotate(magnetNumber, -1);

					// 연결된 자석 회전
					for (int m = 0; m < rotateMagnetList.size(); m++) {
						rotate(rotateMagnetList.get(m).number, rotateMagnetList.get(m).direction);
					}

				}
			}

			if (magnet[1][1] == 1)
				answer += 1;
			if (magnet[2][1] == 1)
				answer += 2;
			if (magnet[3][1] == 1)
				answer += 4;
			if (magnet[4][1] == 1)
				answer += 8;

			System.out.println("#" + t + " " + answer);

		}
	}

	static void rotate(int magnetNumber, int direction) {
		if (direction == 1) {
			int temp = magnet[magnetNumber][8];
			for (int i = 8; i >= 2; i--) {
				magnet[magnetNumber][i] = magnet[magnetNumber][i - 1];
			}
			magnet[magnetNumber][1] = temp;

		} else if (direction == -1) {
			int temp = magnet[magnetNumber][1];
			for (int i = 1; i <= 7; i++) {
				magnet[magnetNumber][i] = magnet[magnetNumber][i + 1];
			}
			magnet[magnetNumber][8] = temp;
		}

	}

	static void magnetCheck(int magnetNumber, int direction) {

		int state = direction;
		switch (magnetNumber) {
		case 1:
			// 1번과 2번 비교
			if (magnet[1][3] != magnet[2][7]) {
				if (state == 1) {
					rotateMagnetList.add(new Magnet(2, -1));
					state = -1;
				} else {
					rotateMagnetList.add(new Magnet(2, 1));
					state = 1;
				}

				// 2번과 3번 비교
				if (magnet[2][3] != magnet[3][7]) {
					if (state == 1) {
						rotateMagnetList.add(new Magnet(3, -1));
						state = -1;
					} else {
						rotateMagnetList.add(new Magnet(3, 1));
						state = 1;
					}
					// 3번과 4번 비교
					if (magnet[3][3] != magnet[4][7])
						if (state == 1) {
							rotateMagnetList.add(new Magnet(4, -1));
							state = -1;
						} else {
							rotateMagnetList.add(new Magnet(4, 1));
							state = 1;
						}
				}
			}
			break;
		case 2:
			// 2번과 1번 자석과 비교
			if (magnet[1][3] != magnet[2][7]) {
				if (state == 1) {
					rotateMagnetList.add(new Magnet(1, -1));
				} else {
					rotateMagnetList.add(new Magnet(1, 1));
				}
			}

			// 2번과 3번 비교
			if (magnet[2][3] != magnet[3][7]) {
				if (state == 1) {
					rotateMagnetList.add(new Magnet(3, -1));
					state = -1;
				} else {
					rotateMagnetList.add(new Magnet(3, 1));
					state = 1;
				}

				// 3번과 4번 비교
				if (magnet[3][3] != magnet[4][7]) {
					if (state == 1) {
						rotateMagnetList.add(new Magnet(4, -1));
					} else {
						rotateMagnetList.add(new Magnet(4, 1));
					}
				}
			}
			break;
		case 3:
			// 3번과 4번 비교
			if (magnet[3][3] != magnet[4][7]) {
				if (state == 1) {
					rotateMagnetList.add(new Magnet(4, -1));
				} else {
					rotateMagnetList.add(new Magnet(4, 1));
				}
			}

			// 3번과 2번 비교
			if (magnet[3][7] != magnet[2][3]) {
				if (state == 1) {
					rotateMagnetList.add(new Magnet(2, -1));
					state = -1;
				} else {
					rotateMagnetList.add(new Magnet(2, 1));
					state = 1;
				}

				// 2번과 1번 비교
				if (magnet[2][7] != magnet[1][3]) {
					if (state == 1) {
						rotateMagnetList.add(new Magnet(1, -1));
					} else {
						rotateMagnetList.add(new Magnet(1, 1));
					}
				}
			}
			break;
		case 4:
			// 4번과 3번 비교
			if (magnet[4][7] != magnet[3][3]) {
				if (state == 1) {
					rotateMagnetList.add(new Magnet(3, -1));
					state = -1;
				} else {
					rotateMagnetList.add(new Magnet(3, 1));
					state = 1;
				}

				// 3번과 2번 비교
				if (magnet[3][7] != magnet[2][3]) {
					if (state == 1) {
						rotateMagnetList.add(new Magnet(2, -1));
						state = -1;
					} else {
						rotateMagnetList.add(new Magnet(2, 1));
						state = 1;
					}

					// 2번과 1번 비교
					if (magnet[2][7] != magnet[1][3]) {
						if (state == 1) {
							rotateMagnetList.add(new Magnet(1, -1));
						} else {
							rotateMagnetList.add(new Magnet(1, 1));
						}
					}
				}
			}
			break;
		}

	}

	static class Magnet {
		int number;
		int direction;

		public Magnet(int number, int direction) {
			this.number = number;
			this.direction = direction;
		}
	}

}
