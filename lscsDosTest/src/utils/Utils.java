package utils;

import Card.Card;
import Card.Dict;
import Card.Person;
import Role.Player;

public class Utils {
	/**
	 * 战场上移除己方随从
	 * @param a 被移除的卡牌
	 * @param b	扩展用
	 * @param c 被移除卡牌的玩家
	 * @param d 扩展用
	 */
	public static void remove(Card a,Card b,Player c,Player d){
		c.getEqiup().remove(a);
		Person pc =(Person) a;
		if(pc.getSpecial().contains(Dict.DEADLANGUAGE)){//触发亡语效果
			pc.deadleagueHappening(a,b,c,d);
		}
		if(pc.isHalo()==true){//移除光环效果
			pc.haloEnd(c, d, a);
		}
	}
	
}
