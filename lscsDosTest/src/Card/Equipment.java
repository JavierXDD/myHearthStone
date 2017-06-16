package Card;

import utils.Utils;
import Role.Player;

public class Equipment extends Card {
	private int attr;//装备攻击；
	private int armor;//装备护甲；
	private int durable;//装备耐久;
	public int getAttr() {
		return attr;
	}
	public void setAttr(int attr) {
		this.attr = attr;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getDurable() {
		return durable;
	}
	public void setDurable(int durable) {
		this.durable = durable;
	}

	public void useEquipment(Card a, Card b, Player c, Player d, String z) {
		int result = Integer.valueOf(z.substring(2));
		if (result == 0) {
			int attract = this.getAttr();
			System.out.println(d.getType() + "受到" + attract + "点伤害，剩余生命为"
					+ (d.getHp() - attract));
			d.setHp(d.getHp() - attract);
			if (d.getHp() <= 0) {
				System.out.println(d.getType() + "已阵亡");
				System.out.println("感谢您的参与");
				System.exit(0);
			}
		} else {
			int size = d.getEqiup().size();// 对方场上仆从
			System.out.println(result);
			if (size >= result) {
				Person pr = (Person) d.getEqiup().get(result - 1);
				pr.setHp(pr.getHp() - this.getAttr());
				if (pr.getHp() <= 0) {
//					d.getEqiup().remove(pr);
					Utils.remove(pr, a, d, c);
				}
				c.setHp(c.getHp() - pr.getAtt());
				if (c.getHp() <= 0) {
					System.out.println(c.getType() + "已阵亡");
					System.out.println("感谢您的参与");
					System.exit(0);
				}
			} else {
				System.out.println("你输入的有误");
			}
		}
		this.setDurable(this.getDurable()-1);
		if(this.getDurable()<=0){
			c.getEquipment().remove(this);
			System.out.println("武器耐久为0，您的武器已破坏");
		}else{
			System.out.println("武器当前耐久度为"+this.getDurable());
		}
		
	}
}
