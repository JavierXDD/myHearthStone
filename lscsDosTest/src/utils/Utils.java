package utils;

import Card.Card;
import Card.Dict;
import Card.Person;
import Role.Player;

public class Utils {
	/**
	 * ս�����Ƴ��������
	 * @param a ���Ƴ��Ŀ���
	 * @param b	��չ��
	 * @param c ���Ƴ����Ƶ����
	 * @param d ��չ��
	 */
	public static void remove(Card a,Card b,Player c,Player d){
		c.getEqiup().remove(a);
		Person pc =(Person) a;
		if(pc.getSpecial().contains(Dict.DEADLANGUAGE)){//��������Ч��
			pc.deadleagueHappening(a,b,c,d);
		}
		if(pc.isHalo()==true){//�Ƴ��⻷Ч��
			pc.haloEnd(c, d, a);
		}
	}
	
}
