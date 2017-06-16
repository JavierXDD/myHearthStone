package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class MaFengZhuRu extends Person{
	public MaFengZhuRu(){
		this.setCrystai(1);
		this.setName("麻风侏儒");
		this.setHp(1);
		this.setAtt(2);
		List<String> array = new ArrayList<String>();
		array.add(Dict.DEADLANGUAGE);
		this.setSpecial(array);
	}
	@Override
	public void deadleagueHappening(Card a,Card b,Player c,Player d){
		d.setHp(d.getHp()-2);
		System.out.println("麻风侏儒死亡，"+d.getType()+"受到2点伤害");
		if(d.getHp()<=0){
			System.out.println(d.getType()+"已阵亡");
			System.out.println("感谢您的参与");
			System.exit(0);
		}
	}
}
