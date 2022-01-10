package baseball;

import nextstep.utils.*;

public class Application {

    public int makeRandomNumber() {  //정답이 될 난수 생성
        int a, b, c;
        a = Randoms.pickNumberInRange(1, 9);
        b = a;
        while (a == b) {
            b = Randoms.pickNumberInRange(1, 9);
        }
        c = b;
        while ((c == a) || (c == b)) {
            c = Randoms.pickNumberInRange(1, 9);
        }
        int k = 0;
        k += a * 100 + b * 10 + c;
        return k;
    }

    public int getInput() {  //올바른 문자열을 입력 받아서 숫자로 변환
        while (true) {
            System.out.print("숫자를 입력해 주세요 : ");
            String str = Console.readLine();
            if (isRightNumber(str)) {
                return Integer.valueOf(str);
            } else {
                //System.out.println("Error : 서로 다른 숫자(1~9)로 이루어진 세자릿수 숫자를 입력하세요.");
                System.out.println("Error");
            }
        }
    }

    public boolean test(int answer, int n) { // 테스트 실행
        if (n == answer) {
            System.out.println("3스트라이크");
            System.out.println("세개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }
        int firstArr[] = split(answer);
        int secondArr[] = split(n);
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < 3; i++) {
            if (firstArr[i] == secondArr[i]) {
                strike++;
            } else {
                for (int j = 1; j < 3; j++) {
                    if (firstArr[(i + j) % 3] == secondArr[i]) {
                        ball++;
                        break;
                    }
                }
            }
        }
        printOutput(strike, ball);
        return false;
    }

    static void printOutput(int strike, int ball) {  //테스트 결과 출력
        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
            return;
        }
        if (strike > 0) {
            System.out.print(strike + "스트라이크 ");
        }
        if (ball > 0) {
            System.out.print(ball + "볼");
        }
        System.out.println();
        return;
    }

    static int[] split(int k) {  //숫자 분할
        int c = k % 10;
        k /= 10;
        int b = k % 10;
        k /= 10;
        int a = k;
        int temp[] = new int[3];
        temp[0] = a;
        temp[1] = b;
        temp[2] = c;
        return temp;
    }

    static boolean isRightNumber(String str) {  //올바른 문자열인지 확인
        if (str.length() != 3) //세글자 아니면 false
        {
            return false;
        }
        if (str.matches("[+-]?\\d*(\\.\\d+)?") == true) {   //숫자 아니면 false;
            int arr[] = split(Integer.valueOf(str));
            if (arr[0] == 0) {
                return false;
            }
            if (arr[1] == 0) {
                return false;
            }
            if (arr[2] == 0) {
                return false;
            }
            if (arr[0] == arr[1]) {
                return false;
            }
            if (arr[2] == arr[1]) {
                return false;
            }
            if (arr[0] == arr[2]) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int game = 1;
        Application application = new Application();
        while (game == 1) {
            int randomNum = application.makeRandomNumber();
            while (true) {
                int n = application.getInput();
                //n의 대해서 확인 해야함
                if (application.test(randomNum, n) == true) {
                    break;
                }
            }
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String str = Console.readLine();
            game = Integer.valueOf(str);
        }
        System.out.println("게임 끝");
    }
}
