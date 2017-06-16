package Card;

import utils.Utils;
import Role.Player;

public class CardSkill extends Card {
	private int attr;
	private String flag="1";
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		int jiacheng = 0;//法伤加成；
		for(int i=0;i<c.getEqiup().size();i++){//检索是否有法伤加成的随从
			Person card = (Person)c.getEqiup().get(i);
				if(card.getSpecial().contains(Dict.MAGICUP)){//判断是否存在法伤随从
					jiacheng++;
				}
		}
		if(a.getCrystai()<=c.getCrystai()){
			int attr =this.getAttr()+jiacheng;
			c.setCrystai(c.getCrystai()-a.getCrystai());
			if(a!=null&&b==null){//技能打脸
				d.setHp(d.getHp()-this.getAttr()-jiacheng);
				System.out.println(c.getType()+"使用"+a.getName()+d.getType()+"受到"+attr+"点伤害");
				if(d.getHp()<=0){
					System.out.println(d.getType()+"已阵亡");
					System.out.println("感谢您的参与");
					System.exit(0);
				}
			}else if(a!=null&&b!=null){//技能打怪
				Person bb=(Person) b;
				if(bb.getSpecial().contains(Dict.MAGICIMMUNITY)||bb.getSpecial().contains(Dict.HIDE)){//魔免和潜行状态不能用直杀法术进行攻击
					System.out.println(b.getName()+"不能成为法术目标");
					c.setCrystai(c.getCrystai()+a.getCrystai());
					return true;	
				}
				CardSkill card = (CardSkill)a;//强转
				int att =card.getAttr();
				b.setHp(b.getHp()-att-jiacheng);
				System.out.println(c.getType()+"使用"+a.getName()+b.getName()+"受到"+attr+"点伤害,剩余"+b.getHp()+"血");
				if(b.getHp()<=0){//技能将对方随从打死
//					d.getEqiup().remove(b);
					Utils.remove(b, a, d, c);
				}
			}
			c.getHandcard().remove(a);//技能用完后将技能牌去除
			return true;
		}else{
			System.out.println("你没有足够的水晶");
			return false;
		}
		
	}
	//去子类实现
	public boolean conditions(Card a,Card b,Player c,Player d){
			return false;
	}
	//去子类实现
	public void result(Card a,Card b,Player c,Player d){
	}
}
