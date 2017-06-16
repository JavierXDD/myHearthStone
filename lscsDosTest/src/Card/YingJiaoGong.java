package Card;

import Role.Player;

public class YingJiaoGong extends Equipment {
	public YingJiaoGong(){
		this.setCrystai(3);
		this.setName("鹰角弓");
		this.setAttr(3);
		this.setDurable(2);
	}
	@Override
	public  boolean cardused(Card a,Card b,Player c,Player d,String z){
		if(a.getCrystai()<=c.getCrystai()){
			if(c.getEquipment().size()>0){
				c.getEquipment().remove(0);//去掉之前的武器
			}
			c.getEquipment().add(a);
			c.getHandcard().remove(a);//技能用完后将技能牌去除
			c.setCrystai(c.getCrystai()-a.getCrystai());
			return true;
		}else{
			System.out.println("你没有足够的水晶");
			return false;
		}
	}
}