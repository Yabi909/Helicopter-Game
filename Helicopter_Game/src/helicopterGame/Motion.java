package helicopterGame;

import java.util.LinkedList;

import helicopterGame.Groups.GroupA;
import helicopterGame.Groups.GroupB;

public class Motion {
	
public static boolean Collission(GroupA ga, GroupB gb){
		
		if (ga.getBounds().intersects(gb.getBounds())){
			return true;
		}
		return false;
	}
	
    public static boolean Collission(GroupB gb, GroupA ga){

		if (gb.getBounds().intersects(ga.getBounds())){
			return true;
		}
		return false;
	}

}
