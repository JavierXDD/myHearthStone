package Card;

import Role.Player;

public class LeiOuKe extends Person{
	public LeiOuKe(){
		this.setCrystai(3);
		this.setName("雷欧克");
		this.setHp(4);
		this.setAtt(2);
		this.setHalo(true);
		this.setType(2);
	}
	@Override
	public void haloHappening(Player c, Player d,Card a) {
			if(this.getId()==a.getId()){//雷欧克上场触发光环效果，类似战吼
				for(int i=0;i<c.getEqiup().size();i++){
					Person p = (Person) c.getEqiup().get(i);
					System.out.println("this id is "+this.getId());
					System.out.println("p id is "+p.getId());
					if(this.getId()!=p.getId()){//光环不给自己加成
						p.setAtt(p.getAtt()+1);
					}
				}
			}else{
				Person p = (Person) a;
				p.setAtt(p.getAtt()+1);
				System.out.println(p.getName()+"的攻击是"+p.getAtt());
			}
	}
	@Override
	public void haloEnd(Player c, Player d, Card a) {
		for(int i=0;i<c.getEqiup().size();i++){
			Person p = (Person) c.getEqiup().get(i);
				p.setAtt(p.getAtt()-1);
		}
	}
}
