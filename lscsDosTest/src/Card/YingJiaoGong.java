package Card;

import Role.Player;

public class YingJiaoGong extends Equipment {
	public YingJiaoGong(){
		this.setCrystai(3);
		this.setName("ӥ�ǹ�");
		this.setAttr(3);
		this.setDurable(2);
	}
	@Override
	public  boolean cardused(Card a,Card b,Player c,Player d,String z){
		if(a.getCrystai()<=c.getCrystai()){
			if(c.getEquipment().size()>0){
				c.getEquipment().remove(0);//ȥ��֮ǰ������
			}
			c.getEquipment().add(a);
			c.getHandcard().remove(a);//��������󽫼�����ȥ��
			c.setCrystai(c.getCrystai()-a.getCrystai());
			return true;
		}else{
			System.out.println("��û���㹻��ˮ��");
			return false;
		}
	}
}