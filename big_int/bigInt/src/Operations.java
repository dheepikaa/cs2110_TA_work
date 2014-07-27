import java.util.StringTokenizer;
import java.util.Vector;


public class Operations {

	public String no;
	
	public static Vector<Integer> stringToVector(String no) {
		
		int length = no.length();
		
		Vector<Integer> number = new Vector<Integer>();
		
		while(length > 6) {
			number.add(Integer.parseInt(no.substring(no.length()-6, no.length()-1)));
			no = no.substring(0, no.length()-7);
			length = no.length();
		}
		if(length != 0) {
			number.add(Integer.parseInt(no));
		}
		return number;
	}
	
	public static String add(String no1, String no2) {
		
		Vector<Integer> number1 = stringToVector(no1);
		Vector<Integer> number2 = stringToVector(no2);
		
		Vector<Integer> temp = new Vector<Integer>();
		
		if(number1.size() > number2.size()) {
			temp = number1;
			number1 = number2;
			number2 = temp;
		}
		
		int i = 0;
		Integer n,carry=0;
		
		while(i < number1.size()) {
			n = number1.get(i)+number2.get(i)+carry;
			temp.add(n%100000);
			carry = n/100000;
		}
	
		temp.add(number2.get(i)+carry);
		carry = 0;
	
		while(i<number2.size()) {
			temp.add(number2.get(i));
		}
		
		return temp.toString();
		
	}
}
