package init;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import Card.Card;

public class Sort {
	public Sort(){}
	public static List sortdesc(List list){
		List<Card> al=new ArrayList<Card>();
		Collections.sort(list, new CardCom());
		al=list;
		for(int i=0;i<al.size();i++){
			System.out.println( "name:"+al.get(i).getName()+":"+"Crystai:"+al.get(i).getCrystai() );
		}
		return al;
	}
}
class CardCom implements Comparator<Card>
{ //ʵ������ʽ:
  public int compare(Card o1, Card o2)
  {
      int num =new Float(o2.getCrystai()).compareTo(new Float(o1.getCrystai()));
    if( num == 0 )//--> ����Ҫ�����������жϴ�Ҫ����;
    {
    	return new Integer(o2.getCrystai()).compareTo( new Integer(o1.getCrystai()) );
    }
    return num;
  }
 }