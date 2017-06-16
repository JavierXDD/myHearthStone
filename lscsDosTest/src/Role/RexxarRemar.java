package Role;

import Card.Card;
import Card.Person;
import skill.CharacterAbstractSkill;

public class RexxarRemar extends Character {
	public RexxarRemar(){
		this.setName("雷克萨");
		this.setMaxhp(30);
		this.setHp(this.getMaxhp());
		this.setMaxCrystai(1);
		this.setCrystai(this.getMaxCrystai());
	}
	@Override
	//重写lr技能稳固射击
	public void characterskill(Player who, Player towho,int r) {
		// TODO Auto-generated method stub
		if(who.getCrystai()-2>=0){
			who.setCrystai(who.getCrystai()-2);
			System.out.println(who.getType()+"发动稳固射击"+towho.getType()+"受到2点伤害");
			towho.setHp(towho.getHp()-2);
			this.setSkill(false);
			if(towho.getHp()<=0){
				System.out.println(towho.getType()+"已阵亡");
				System.out.println("感谢您的参与");
				System.exit(0);
			}
		}else{
			System.out.println("你没有足够的法力值");
		}
	}
}
