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
	 {//创建ArrayList集合:     //传入自己定义的比较方式!
	  ArrayList<Card> al=new ArrayList<Card>();
	  //添加元素://简单写了;
	  al.add( new AoShuSheJi() );
	  al.add( new JingLinglong() );
	  al.add( new MaFengZhuRu() );
	  al.add( new DongWuHuoBan() );
	  //sop(al);//开始排序:
	  Collections.sort(al, new CardCom());
	  //迭代取出集合元素:
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
//比较器:
class CardCom implements Comparator<Card>
{ //实现排序方式:
public int compare(Card o1, Card o2)
{
    int num =new Float(o2.getCrystai()).compareTo(new Float(o1.getCrystai()));
  if( num == 0 )//--> 当主要条件满足则判断次要条件;
  {
  return new Integer(o2.getCrystai()).compareTo( new Integer(o1.getCrystai()) );
  }
  return num;
}

}