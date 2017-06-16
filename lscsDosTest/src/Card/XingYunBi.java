package Card;

import Role.Player;

public class XingYunBi extends CardSkill {
	public XingYunBi(){
		this.setAttr(0);
		this.setCrystai(0);
		this.setName("幸运币");
	}
	//重写此方法
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		c.setCrystai(c.getCrystai()+1);
		c.getHandcard().remove(a);
		return true;
		
	}
}
