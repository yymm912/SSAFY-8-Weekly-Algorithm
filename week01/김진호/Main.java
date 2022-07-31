
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, K, S, Y;
	static ArrayList<StudentInfo> students = new ArrayList<>();

	static int answer;

	static boolean checkExistGrade(ArrayList<StudentInfo> students, int Y) {
		if (students.size() == 0)
			return true;
		else {
			for (StudentInfo studentInfo : students) {
				if (studentInfo.getGrade() == Y) {
					return false;
				}
			}
		}
		return true;
	}

	static void setExistIndex(ArrayList<StudentInfo> students, int S, int Y) {
		StudentInfo studentInfo = new StudentInfo();
		int idx = 0;
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getGrade() == Y) {
				idx = i;
				// 학년에 해당하는 index를 찾으면 빈 객체 생성
				studentInfo = students.get(i);

				// 여자의 카운드 증가
				if (S == 0) {
					studentInfo.setFemale();
				}
				// 남자의 카운트 증가
				else {
					studentInfo.setMale();
				}
			}
		}
		students.set(idx, studentInfo);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			StudentInfo studentInfo = new StudentInfo();
			st = new StringTokenizer(bf.readLine());
			S = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());

			// 배열에 넣기 위한 객체
			studentInfo.setSex(S);
			studentInfo.setGrade(Y);

			// 학년이 존재 하지 않으면 배열 추가
			if (checkExistGrade(students, Y)) {
				students.add(studentInfo);
			} else {
				// 학년이 이미 존재한다면 해당 인덱스에 추가
				setExistIndex(students, S, Y);
			}
		}

		for (StudentInfo studentInfo : students) {
			int femaleCnt = studentInfo.getFemale();
			int maleCnt = studentInfo.getMale();

			if (femaleCnt % K == 0) {
				answer += femaleCnt / K;
			} else {
				answer += femaleCnt / K + 1;
			}

			if (maleCnt % K == 0) {
				answer += maleCnt / K;
			} else {
				answer += maleCnt / K + 1;
			}
		}
		System.out.println(answer);

	}
}

class StudentInfo {
	private int female = 0;
	private int male = 0;
	private int grade;

	public StudentInfo() {

	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setSex(int sex) {
		if (sex == 0) {
			this.female++;
		} else {
			this.male++;
		}
	}

	public int getFemale() {
		return female;
	}

	public void setFemale() {
		this.female++;
	}

	public int getMale() {
		return male;
	}

	public void setMale() {
		this.male++;
	}

	@Override
	public String toString() {
		return "StudentInfo{" + "female=" + female + ", male=" + male + ", grade=" + grade + '}';
	}
}
