package Card;

import java.util.ArrayList;
import java.util.List;

public class MiSha extends Person{
	public MiSha(){
		this.setCrystai(3);
		this.setName("��ɯ");
		this.setHp(4);
		this.setAtt(4);
		List<String> array = new ArrayList<String>();
		array.add(Dict.DERIDe);
		this.setSpecial(array);//����
		this.setType(2);//Ұ������
	}
}