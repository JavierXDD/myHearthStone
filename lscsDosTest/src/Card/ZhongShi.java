package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class ZhongShi extends Person{
	public ZhongShi(){
		this.setCrystai(1);
		this.setName("叫嚣的中士");
		this.setHp(1);
		this.setAtt(2);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	/**
	 * 叫嚣中士的战吼
	 */
	@Override
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		if(z.length()==3){
			int num =Integer.valueOf(z.substring(2));
			Person pc=(Person) c.getEqiup().get(num-1);
			pc.setAtt(pc.getAtt()+2);
		}else if(z.length()==4){
			int num =Integer.valueOf(z.substring(3));
			Person pc=(Person) d.getEqiup().get(num-1);
			pc.setAtt(pc.getAtt()+2);
		}else{
			System.out.println("请正确输入");
			return false;
		}
	return true;
	}
}
