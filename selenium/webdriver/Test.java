package webdriver;

public class Test {
	public static void main(String[] args) {
		for (int i = 7; i > -7; i--) {
			for (int j = 0; j < i; j ++) {
				System.out.print(" ");
			}
			for (int j = 0; j >=i; j--) {
				System.out.print(" ");
			}
			System.out.println("*");
		}
	}

}
