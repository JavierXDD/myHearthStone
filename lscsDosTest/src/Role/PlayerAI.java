package Role;


import init.Initial;

import java.util.ArrayList;
import java.util.Random;

import Card.Card;


//****************AIÕÊº“¿‡*********************
public class PlayerAI extends Player{ 
	
	public PlayerAI(){}
	public PlayerAI(Character character,int hp){
		super(character,hp);
		this.setType("(AI)");
	}
}
