package social_force;

public class Direction {
	public double x_dir;
	public double y_dir;
	
	//计算人到人之间的方向
	public static Direction Distance2Direction_h2h(Location src, Location des) {
		double distance = Math.sqrt((src.x_loc-des.x_loc)*(src.x_loc-des.x_loc)+(src.y_loc-des.y_loc)*(src.y_loc-des.y_loc));
		Direction Dir = new Direction();
		Dir.x_dir = (des.x_loc-src.x_loc)/distance;
		Dir.y_dir = (des.y_loc-src.y_loc)/distance;
		return Dir;
	}
	
	//计算墙到人之间的方向
	public static Direction Distance2Direction_h2w(Location src, Wall w) {
		Direction Dir = new Direction();
		if (w.IsLevel) {
			Dir.x_dir = 0; 
			if (src.y_loc >= w.begin.y_loc) Dir.y_dir = 1;
			else Dir.y_dir = -1;
		}
		else {
			if (src.x_loc >= w.begin.x_loc) Dir.x_dir = 1; 
			else Dir.x_dir = -1;
			Dir.y_dir = 0;
		}
		return Dir;
	}
}
