package Role;

import Card.Card;
import Card.Person;
import skill.CharacterAbstractSkill;

public class RexxarRemar extends Character {
	public RexxarRemar(){
		this.setName("�׿���");
		this.setMaxhp(30);
		this.setHp(this.getMaxhp());
		this.setMaxCrystai(1);
		this.setCrystai(this.getMaxCrystai());
	}
	@Override
	//��дlr�����ȹ����
	public void characterskill(Player who, Player towho,int r) {
		// TODO Auto-generated method stub
		if(who.getCrystai()-2>=0){
			who.setCrystai(who.getCrystai()-2);
			System.out.println(who.getType()+"�����ȹ����"+towho.getType()+"�ܵ�2���˺�");
			towho.setHp(towho.getHp()-2);
			this.setSkill(false);
			if(towho.getHp()<=0){
				System.out.println(towho.getType()+"������");
				System.out.println("��л���Ĳ���");
				System.exit(0);
			}
		}else{
			System.out.println("��û���㹻�ķ���ֵ");
		}
	}
}
