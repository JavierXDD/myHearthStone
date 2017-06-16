package Card;

import java.util.Random;

import Role.Player;

public class DongWuHuoBan extends CardSkill{
	public DongWuHuoBan(){
		this.setCrystai(3);
		this.setName("动物伙伴");
	}
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
//		System.out.println("-----------------------begin--"+c.getCrystai()+"------------------------------");
		if(a.getCrystai()<=c.getCrystai()){
//			int num=3;
			int num=new Random().nextInt(3);
			if(num==1){
//				c.getEqiup().add(new MiSha());
				MiSha m = new MiSha();
				m.setId(this.getId());
				m.cardused(m, b, c, d, z);
			}else if(num==2){
				ChongFengZhu cf=new ChongFengZhu();
				cf.setId(this.getId());
				cf.cardused(cf, b, c, d, z);
			}else{
//				c.getEqiup().add(new LeiOuKe());
				LeiOuKe l=new LeiOuKe();
				l.setId(this.getId());
				l.cardused(l, b, c, d, z);
			}	
//			c.setCrystai(c.getCrystai()-a.getCrystai());
//			System.out.println("------------------end-------"+c.getCrystai()+"------------------------------");
			c.getHandcard().remove(a);
		}else{
			System.out.println("你没有足够的水晶");
		}
		
		return true;
		
		
	}
}

