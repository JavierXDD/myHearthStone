package Card;

import java.util.ArrayList;

import Role.Player;

public class ZhaoMingDan extends CardSkill {
	public ZhaoMingDan(){
		this.setCrystai(1);
		this.setName("������");
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		c.setCrystai(c.getCrystai()-a.getCrystai());//�۳�����
		c.AddCard(c, 1, d);//��һ����
		d.getAoMi().removeAll(d.getAoMi());//�Ƴ����а���
		for(int i =0;i<d.getEqiup().size();i++){//�����ε�λ����
			Person p = (Person) d.getEqiup().get(i);
			if(p.getSpecial().contains(Dict.HIDE)){
				p.setSpecial(new ArrayList<String>());
			}
		}
		c.getHandcard().remove(a);//��������󽫼�����ȥ��
		return true;
	}
}
