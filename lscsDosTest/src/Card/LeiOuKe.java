package Card;

import Role.Player;

public class LeiOuKe extends Person{
	public LeiOuKe(){
		this.setCrystai(3);
		this.setName("��ŷ��");
		this.setHp(4);
		this.setAtt(2);
		this.setHalo(true);
		this.setType(2);
	}
	@Override
	public void haloHappening(Player c, Player d,Card a) {
			if(this.getId()==a.getId()){//��ŷ���ϳ������⻷Ч��������ս��
				for(int i=0;i<c.getEqiup().size();i++){
					Person p = (Person) c.getEqiup().get(i);
					System.out.println("this id is "+this.getId());
					System.out.println("p id is "+p.getId());
					if(this.getId()!=p.getId()){//�⻷�����Լ��ӳ�
						p.setAtt(p.getAtt()+1);
					}
				}
			}else{
				Person p = (Person) a;
				p.setAtt(p.getAtt()+1);
				System.out.println(p.getName()+"�Ĺ�����"+p.getAtt());
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
