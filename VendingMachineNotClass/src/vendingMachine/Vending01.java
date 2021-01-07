package vendingMachine;

import java.util.Scanner;

public class Vending01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int money, menuNum;		// 금액, switch 경우의 수
		int pay = 0;			// 지불 금액
		int thing = 0;			// 고른게 있고 없고 조건
		int subMoney;			// 더 투입할 돈
		String con;				// n이나 N 고를 때 사용할 변수
		//int n1000 = 0, n500 = 0, n100 = 0, n50 = 0, n10 = 0;
		
		int[] menuNum2 = new int[9];	// 정한 메뉴 갯수
		String[] menu = {"콜라", "사이다", "환타", "커피", "생수", "이온음료", "과자", "팝콘", "껌"};	//갯수 구할 메뉴명
		
		String[][] menuBoard = {{"콜라(600원)", "사이다(600원)", "환타(600원)"},
				{"커피(1480원)", " 생수(470원)", "이온음료(900원)"},
				{"과자(1350원)", " 팝콘(1000원)", " 껌(550원)"}};		// 메뉴판(2차원 배열)
		
		int[][] menuBoardNum = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};		// 메뉴판 번호
		
		System.out.println("========================메뉴(MENU)========================");
		System.out.println();
		
		for(int i = 0; i < menuBoard.length; i++) {
			for(int j = 0; j < menuBoard.length; j++) {
				System.out.printf(menuBoardNum[i][j] + "%14s   ", menuBoard[i][j]);
			}
			System.out.println();
			System.out.println();
		}
		
		System.out.println("                    10 선택하지 않고 나가기");
		System.out.println("=========================================================");
		System.out.println();
		
		while(true) {
			System.out.print("금액을 입력하세요. : ");
			money = sc.nextInt();
			if(money < 0)
				System.out.println("알맞은 값을 입력하시오.");
			else {
				System.out.println("현재 투입된 금액 : " + money + "원");
				break;
			}
		}
		
		while(true) {
			System.out.print("음료를 선택하세요.(번호 입력) : ");
			menuNum = sc.nextInt();
			
			switch(menuNum) {
				case 1:
					pay = 600;
					menuNum2[0]++;	// 콜라 수량 추가
					break;
				case 2:
					pay = 600;
					menuNum2[1]++;	// 사이다 수량 추가
					break;
				case 3:
					pay = 600;
					menuNum2[2]++;	// 환타 수량 추가
					break;
				case 4:
					pay = 1480;
					menuNum2[3]++;	// 커피 수량 추가
					break;
				case 5:
					pay = 470;
					menuNum2[4]++;	// 생수 수량 추가
					break;
				case 6:
					pay = 900;
					menuNum2[5]++;	// 이온음료 수량 추가
					break;
				case 7:
					pay = 1350;
					menuNum2[6]++;	// 과자 수량 추가
					break;
				case 8:
					pay = 1000;
					menuNum2[7]++;	// 팝콘 수량 추가
					break;
				case 9:
					pay = 550;
					menuNum2[8]++;	// 껌 수량 추가
					break;
				case 10:
					break;
				default:
					System.out.println("메뉴 재선택(1 ~ 9) 혹은 선택 안 함(10)");	
			}	// switch문
			
			if(money < pay && menuNum != 10) {
				if(menuNum < 1 || menuNum > 9)
					continue;
				
				System.out.println("잔액이 부족합니다. " + (pay - money) + "원 만큼 필요합니다.");
				menuNum2[menuNum - 1]--;	// 추가된 수량에서 하나 뺌
				System.out.print("계속 하시겠습니까?(Y or N) : ");
				con = sc.next();
				if(con.equals("n")||con.equals("N"))
					break;	// while문 빠져나오기
				
				while(true) {
					System.out.print("돈을 더 투입해주세요. : ");
					subMoney = sc.nextInt();
					if(subMoney < 0)
						System.out.println("알맞은 값을 입력하시오.");
					else {
						money += subMoney;
						System.out.println("현재 투입된 금액 : " + money +"원");
						break;
					}
				}
			}	// 잔액 부족
			else if(menuNum == 10) {
				break;	// while문 빠져나오기
			}
			else if(menuNum >= 1 && menuNum <= 9){ // 메뉴 재 선택만 뜨게 하기 위해, 안쓰면 밑에꺼 까지 다 나옴
				thing = 1;
				money -= pay;
				System.out.println("잔액 : " + money +"원");
				
				System.out.print("추가 선택?(Y or N) : ");
				con = sc.next();
				
				if(con.equals("n")||con.equals("N"))
					break;	// while문 빠져나오기
			}	
		} //while 메뉴 고르기
		
		System.out.println("이용해주셔서 감사합니다.");
		if(thing != 1)
			System.out.print("선택하신 메뉴는 없습니다.");		// 선택이 없을 경우
		else {
			System.out.print("선택하신 메뉴는 ");
			for(int i = 0; i < 9; i++) {
				System.out.print(menu[i] + "는 " + menuNum2[i] + "개");
				if(i < 8)
					System.out.print(", ");
			}	// 메뉴의 갯수
		}
		System.out.println();
		System.out.println("남은 금액은 " + money + "원 입니다.");
		
		int[] coinUnit = { 1000, 500, 100, 50, 10, 1 };	// 거스름돈 단위
		for (int i = 0; i < coinUnit.length; i++) {
			System.out.print(coinUnit[i] + "원 " + money/coinUnit[i] +"개");
			money %= coinUnit[i];
			if(i < coinUnit.length - 1)
				System.out.print(", ");
		} // 거스름돈
		/*
		System.out.println();
		n1000 = money/1000;
		n500 = (money%1000)/500;
		n100 = ((money%1000)%500)/100;
		n50 = (((money%1000)%500)%100)/50;
		n10 = ((((money%1000)%500)%100)%50)/10;
		
		System.out.println("1000원 " + n1000 + "개, 500원 " + n500 + "개, 100원 " + n100 + "개, 50원 " + n50 +"개, 10원 " + n10 +"개");
		*/
		sc.close();
	}
}
