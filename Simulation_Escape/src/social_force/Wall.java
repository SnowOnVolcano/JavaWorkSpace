package social_force;

public class Wall {
	public Location begin;  //起始点
	public Location end;    //终止点
	public boolean IsLevel; //是否水平
	
	public Wall() {
		begin = new Location();
		end = new Location();
	}
}
