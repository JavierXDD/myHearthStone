package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class HuoCheWang extends Person{
	public HuoCheWang(){
		this.setCrystai(4);
		this.setName("����");
		this.setHp(2);
		this.setAtt(6);
		this.setIsatt(true);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	public HuoCheWang(int x,int y){
		this.setCrystai(0);
		this.setName("��ɫ����");
		this.setHp(1);
		this.setAtt(1);
		this.setIsatt(true);
	}
	/**
	 * ѱ��ʦ�ĵ�ս������ս���ж������ƣ��ڴ˷������aiҪ������ơ�����
	 */
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		d.getEqiup().add(new HuoCheWang(1,1));
		d.getEqiup().add(new HuoCheWang(1,1));
		return true;
	}
}