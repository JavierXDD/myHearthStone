import java.util.ArrayList;
import java.util.List;


public class test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		//∑¥…‰
//		Class own =Class.forName("Card."+"XunMengLong");
//		Object o =own.newInstance();
//		Person p =(XunMengLong) o;
//		System.out.println("----------"+p.getAtt());
//		int i=-100;
//		String a=String.valueOf(i);
//		System.out.println(a.substring(1,2));
		List a =  new ArrayList();
		List b = new ArrayList();
		a.add(b);
		b.add(1);
		b.add(2);
//		b.removeAll(b);
		System.out.println(a.contains(1));
		System.out.println(a);
	}
}
