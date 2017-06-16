package skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import Card.AoShuSheJi;
import Card.Card;
import Card.DongWuHuoBan;
import Card.JingLinglong;
import Card.MaFengZhuRu;

public class Demo1
{
	 public static void main(String []args)
	 {//����ArrayList����:     //�����Լ�����ıȽϷ�ʽ!
	  ArrayList<Card> al=new ArrayList<Card>();
	  //���Ԫ��://��д��;
	  al.add( new AoShuSheJi() );
	  al.add( new JingLinglong() );
	  al.add( new MaFengZhuRu() );
	  al.add( new DongWuHuoBan() );
	  //sop(al);//��ʼ����:
	  Collections.sort(al, new CardCom());
	  //����ȡ������Ԫ��:
	  Iterator<Card> it=al.iterator();
	  while( it.hasNext() )
	  {
	   Card ee=(Card)it.next();
	      sop( "name:"+ee.getName()+":"+"Crystai:"+ee.getCrystai() );
	  }
	 
	 }
	 public static void sop(Object obj)
	  {
	     System.out.println(obj);
	  }
	 }
//�Ƚ���:
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