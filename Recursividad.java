
public class Recursividad {
	public static int sumatoria(int n) {
		if(n == 1) {
			return 1;
		}else {
			return sumatoria(n - 1) + (n - 1) + 1;
		}
	}
	public static String base10a2(int n) {
		if(n == 1) {
			return "1";
		}else if(n == 0){
			return "0";
		}else {
			return base10a2(n/2)+ n%2 + "";
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(sumatoria(10));
		System.out.println(base10a2(10));
	}
}
