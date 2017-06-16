package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class XunShouShi extends Person{
	public XunShouShi(){
		this.setCrystai(4);
		this.setName("驯兽师");
		this.setHp(3);
		this.setAtt(4);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	@Override
	/**
	 * 驯兽师的的战吼（由于战吼有对象限制，在此方法里对ai要重新设计。。）
	 */
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		boolean flag =true;
		if(z.length()==3){
			int num =Integer.valueOf(z.substring(2));
			Person pc=(Person) c.getEqiup().get(num-1);
			if(pc.getType()==2){//如果是野兽则+2+2并嘲讽。。
				pc.setAtt(pc.getAtt()+2);
				pc.setHp(pc.getHp()+2);
				List<String> array = new ArrayList<String>();
				array.add(Dict.DERIDe);
				pc.setSpecial(array);//嘲讽
			}else{
				for(int i=0;i<c.getEqiup().size();i++){//检查是否有野兽
					Person ps=(Person) c.getEqiup().get(i);
					if(ps.getType()==2){//如果有野兽则战吼必须生效
						if(c.getType().equals("(AI)")){//如果是ai 由于ai在父方法中控制有点不妥再此控制
							ps.setAtt(pc.getAtt()+2);
							ps.setHp(pc.getHp()+2);
							List<String> array = new ArrayList<String>();
							array.add(Dict.DERIDe);
							ps.setSpecial(array);//嘲讽
							break;
						}else{
							flag = false;
							System.out.println("驯兽师的战吼目标必须是野兽");
							break;
						}
					}
				}
			}
		}
	return flag;
	}
}