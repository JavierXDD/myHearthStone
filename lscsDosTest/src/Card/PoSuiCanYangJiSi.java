package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class PoSuiCanYangJiSi extends Person{
	public PoSuiCanYangJiSi(){
		this.setCrystai(3);
		this.setName("ÆÆËé²ÐÑô¼Àìë");
		this.setHp(2);
		this.setAtt(3);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	@Override
	/**
	 * ÆÆËé²ÐÑôµÄµÄÕ½ºð
	 */
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		if(z.length()==3){
			int num =Integer.valueOf(z.substring(2));
			Person pc=(Person) c.getEqiup().get(num-1);
			pc.setAtt(pc.getAtt()+1);
			pc.setHp(pc.getHp()+1);
		}
	return true;
	}
}
