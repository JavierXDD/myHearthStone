package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class MaFengZhuRu extends Person{
	public MaFengZhuRu(){
		this.setCrystai(1);
		this.setName("���٪��");
		this.setHp(1);
		this.setAtt(2);
		List<String> array = new ArrayList<String>();
		array.add(Dict.DEADLANGUAGE);
		this.setSpecial(array);
	}
	@Override
	public void deadleagueHappening(Card a,Card b,Player c,Player d){
		d.setHp(d.getHp()-2);
		System.out.println("���٪��������"+d.getType()+"�ܵ�2���˺�");
		if(d.getHp()<=0){
			System.out.println(d.getType()+"������");
			System.out.println("��л���Ĳ���");
			System.exit(0);
		}
	}
}
