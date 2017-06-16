package Card;

import utils.Utils;
import Role.Player;

public class Equipment extends Card {
	private int attr;//װ��������
	private int armor;//װ�����ף�
	private int durable;//װ���;�;
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
			System.out.println(d.getType() + "�ܵ�" + attract + "���˺���ʣ������Ϊ"
					+ (d.getHp() - attract));
			d.setHp(d.getHp() - attract);
			if (d.getHp() <= 0) {
				System.out.println(d.getType() + "������");
				System.out.println("��л���Ĳ���");
				System.exit(0);
			}
		} else {
			int size = d.getEqiup().size();// �Է������ʹ�
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
					System.out.println(c.getType() + "������");
					System.out.println("��л���Ĳ���");
					System.exit(0);
				}
			} else {
				System.out.println("�����������");
			}
		}
		this.setDurable(this.getDurable()-1);
		if(this.getDurable()<=0){
			c.getEquipment().remove(this);
			System.out.println("�����;�Ϊ0�������������ƻ�");
		}else{
			System.out.println("������ǰ�;ö�Ϊ"+this.getDurable());
		}
		
	}
}
