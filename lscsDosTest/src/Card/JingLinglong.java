package Card;

import java.util.ArrayList;
import java.util.List;

public class JingLinglong extends Person{
	public JingLinglong(){
		this.setType(1);
		this.setCrystai(2);
		this.setName("¾«ÁéÁú");
		this.setHp(2);
		this.setAtt(3);
		List array = new ArrayList();
		array.add(Dict.MAGICIMMUNITY);
		this.setSpecial(array);
	}
}
