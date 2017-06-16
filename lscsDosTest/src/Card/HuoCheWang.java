package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class HuoCheWang extends Person{
	public HuoCheWang(){
		this.setCrystai(4);
		this.setName("火车王");
		this.setHp(2);
		this.setAtt(6);
		this.setIsatt(true);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	public HuoCheWang(int x,int y){
		this.setCrystai(0);
		this.setName("黑色幼龙");
		this.setHp(1);
		this.setAtt(1);
		this.setIsatt(true);
	}
	/**
	 * 驯兽师的的战吼（由于战吼有对象限制，在此方法里对ai要重新设计。。）
	 */
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		d.getEqiup().add(new HuoCheWang(1,1));
		d.getEqiup().add(new HuoCheWang(1,1));
		return true;
	}
}