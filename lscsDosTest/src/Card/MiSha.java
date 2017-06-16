package Card;

import java.util.ArrayList;
import java.util.List;

public class MiSha extends Person{
	public MiSha(){
		this.setCrystai(3);
		this.setName("Ã×É¯");
		this.setHp(4);
		this.setAtt(4);
		List<String> array = new ArrayList<String>();
		array.add(Dict.DERIDe);
		this.setSpecial(array);//³°·í
		this.setType(2);//Ò°ÊÞÀàÐÍ
	}
}