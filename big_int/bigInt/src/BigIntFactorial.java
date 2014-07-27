import java.util.Scanner;
import java.util.Vector;


public class BigIntFactorial {

	/**
	 * @param args
	 * n! + (n-1)! + (n-2)! + (n-3)! .... + 1!
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Scanner in = new Scanner(System.in);
		//int n = in.nextInt();
		sumOfFac(15);
		
	}

	private static void sumOfFac(int n) {
		// TODO Auto-generated method stub
		String sumTerm = "1";
		String fac = "1";
		Integer j;
		String tmp;
		
		for(int i=2; i<=n; i++) {
			j = new Integer(i);
			tmp = j.toString();
			fac = removeZero(fac);
			fac = multiply(fac, j.toString());
			sumTerm = add(sumTerm, fac);
			sumTerm = removeZero(sumTerm);
		}
		
		System.out.println(sumTerm);
	}
	
private static String removeZero(String fac) {
		// TODO Auto-generated method stub
			while(fac.charAt(0)=='0') {
				fac = fac.substring(1);
			}
		return fac;
	}

public static Vector<Integer> stringToVector(String no) {
		
		int length = no.length();
		String tempS;
		Vector<Integer> number = new Vector<Integer>();
		
		while(length > 4) {
			tempS = no.substring(no.length()-4, no.length());
			number.add(Integer.parseInt(tempS));
			no = no.substring(0, no.length()-4);
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
		
		number1 = addV(number1, number2);
		
		return vectorToS(number1);
	}
	
	public static Vector<Integer> addV(Vector<Integer> number1, Vector<Integer> number2 ) {

		Vector<Integer> temp = new Vector<Integer>();
		
		while(number1.size() > number2.size()) {
			number2.add(0);
		}
		
		while(number1.size() < number2.size()) {
			number1.add(0);
		}
		
		int i = 0;
		Integer n,carry=0;
		
		while(i < number1.size()) {
			n = number1.get(i)+number2.get(i)+carry;
			temp.add(n%10000);
			carry = n/10000;
			i++;
		}
	
		temp.add(carry);	
		return temp;
	
	}
	
	public static String multiply(String no1, String no2) {
		
		Vector<Integer> number1 = stringToVector(no1);
		Vector<Integer> number2 = stringToVector(no2);
		
		Vector<Integer> temp = new Vector<Integer>();
		
		while(number1.size() > number2.size()) {
			number2.add(0);
		}
		while(number2.size() > number1.size()) {
			number1.add(0);
		}
		
		temp = kasMul(number1, number2, number1.size());
		
		return vectorToS(temp);
	}
	
	private static String vectorToS(Vector<Integer> temp) {
		// TODO Auto-generated method stub
		String s = "", tmpS;
		int size = temp.size();
		Integer t;
		
		for(int i=0; i<size; i++) {
			t = temp.get(i);
			tmpS = t.toString();
			
			if( t < 1000) {
				tmpS = "0".concat(tmpS);
				if(t < 100) {
					tmpS = "0".concat(tmpS);
					if(t < 10)
						tmpS = "0".concat(tmpS);
				}
			}
			s = tmpS.concat(s);
		}
		return s;
	}

	public static Vector<Integer> kasMul(Vector<Integer> v1, Vector<Integer> v2, int no) {
		
		Vector<Integer> n11 = new Vector<Integer>();
		Vector<Integer> n12 = new Vector<Integer>();
		Vector<Integer> n21 = new Vector<Integer>();
		Vector<Integer> n22 = new Vector<Integer>();
		
		Vector<Integer> nr = new Vector<Integer>();
		Vector<Integer> nm1 = new Vector<Integer>();
		Vector<Integer> nm2 = new Vector<Integer>();
		Vector<Integer> nl = new Vector<Integer>();
		
		Integer temp, carry;
		
		if(no == 1) {
			temp = v1.get(0)*v2.get(0);
			carry = temp/10000;
			n11.add(temp%10000);
			n11.add(carry);
			return n11;
		}
		
		int length = 0;
		
		if(no%2 == 1) {
			v1.add(0);
			v2.add(0);
			no++;
		}
		
		while(length < no/2) {
			n11.add(v1.get(length));
			n21.add(v2.get(length));
			n12.add(v1.get(no/2 + length));
			n22.add(v2.get(no/2 + length));
			length++;
		}
		
		nr = kasMul(n11, n21, no/2);
		nm1 = kasMul(n12, n21, no/2);
		nm2 = kasMul(n11, n22, no/2);
		nl = kasMul(n12, n22, no/2);
		
		nm1 = addV(nm1, nm2);
		carry = 0;
		
		for(int i=no/2, j=0; i<no; i++, j++ ) {
			
			temp = carry + nm1.get(j) + nr.get(i);
			carry = temp/10000;
			nr.set(i, temp%10000);
		
		}
		
		for(int i=no/2 , j=0; i<no; i++, j++) {
			
			temp = nl.get(j)+nm1.get(i) + carry;
			carry = temp/10000;
			nr.add(temp);
		}
		
		nr.add(carry+nl.get(no/2));
		
		for(int i=no/2 + 1; i<no; i++) {
			nr.add(nl.get(i));
		}
		
		return nr;
	}

}
