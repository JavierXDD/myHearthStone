package Card;

import utils.Utils;
import Role.Player;

public class ShaLuMingLing extends CardSkill {
	public ShaLuMingLing(){
		this.setAttr(3);
		this.setCrystai(3);
		this.setName("杀戮命令");
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		int jiacheng = 0;//法伤加成；
		boolean flag=false;
		for(int i=0;i<c.getEqiup().size();i++){//检索是否有法伤加成的随从
			Person card = (Person)c.getEqiup().get(i);
				if(card.getSpecial().contains(Dict.MAGICUP)){//判断是否存在法伤随从
					jiacheng++;
				}
				System.out.println("------------------"+card.getType());
			if(card.getType()==2){//若有野兽
				flag =true;
			}
			
		}
		if(flag){//若有野兽攻击加2
			jiacheng=jiacheng+2;
		}
		if(a.getCrystai()<=c.getCrystai()){
			c.setCrystai(c.getCrystai()-a.getCrystai());
			int attr=this.getAttr()+jiacheng;
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
				if(bb.getSpecial().contains(Dict.MAGICIMMUNITY)||bb.getSpecial().contains(Dict.HIDE)){
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
}
