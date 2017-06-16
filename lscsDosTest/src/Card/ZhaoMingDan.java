package Card;

import java.util.ArrayList;

import Role.Player;

public class ZhaoMingDan extends CardSkill {
	public ZhaoMingDan(){
		this.setCrystai(1);
		this.setName("照明弹");
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		c.setCrystai(c.getCrystai()-a.getCrystai());//扣除费用
		c.AddCard(c, 1, d);//摸一张牌
		d.getAoMi().removeAll(d.getAoMi());//移除所有奥秘
		for(int i =0;i<d.getEqiup().size();i++){//将隐形单位显行
			Person p = (Person) d.getEqiup().get(i);
			if(p.getSpecial().contains(Dict.HIDE)){
				p.setSpecial(new ArrayList<String>());
			}
		}
		c.getHandcard().remove(a);//技能用完后将技能牌去除
		return true;
	}
}
