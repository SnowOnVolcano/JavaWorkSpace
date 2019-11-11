package social_force;

class Force {
	double Force_scale;
	Direction Fdir;
	
	//给定x，y轴分力，实例化一个力
	Force(double X_f, double Y_f) {
		Force_scale = Math.sqrt(X_f*X_f+Y_f*Y_f);
		Fdir = new Direction();
		Fdir.x_dir = X_f/Force_scale;
		Fdir.y_dir = Y_f/Force_scale;
	}
	Force() {
		Fdir = new Direction();
	}
	
	//人与人之间的作用力
	static Force getForce_h2h(Human A, Human B) {
		
		//计算法向作用力
		Force Fn = new Force();
		Fn.Fdir = Direction.Distance2Direction_h2h(A.location, B.location);
		double dAB = Location.getDistance(A.location, B.location);
		Fn.Force_scale = Main.Ai*Math.exp((Human.radius-dAB)/Main.Bi)+Main.K*Main.g(Human.radius-dAB);
		
		//计算切向作用力
		//social_force.Force Ft = new social_force.Force();
		//Ft.Fdir.x_dir = Fn.Fdir.y_dir; Ft.Fdir.y_dir = -Fn.Fdir.x_dir;
		//double deta_v = (Ft.Fdir.x_dir*A.Velocity*A.Vdirection.x_dir+Ft.Fdir.y_dir*A.Velocity*A.Vdirection.y_dir)
		//		      - (Ft.Fdir.x_dir*B.Velocity*B.Vdirection.x_dir+Ft.Fdir.y_dir*B.Velocity*B.Vdirection.y_dir);
		//Ft.Force_scale = social_force.Main.k*social_force.Main.g(Human.radius-dAB)*deta_v;
		
		//return social_force.Force.MergeForce(Fn, Ft);
		return Fn;
	}
	
	//人与墙之间的作用力
	static Force getForce_h2w(Human H, Wall W) {
		
		//计算法向作用力
		Force Fn = new Force();
		Fn.Fdir = Direction.Distance2Direction_h2w(H.location, W);
		double dHW;
		if (W.IsLevel && H.location.x_loc >= W.begin.x_loc && H.location.x_loc <= W.end.x_loc) dHW = Math.abs(W.begin.y_loc-H.location.y_loc);
		else if (!W.IsLevel && H.location.y_loc >= W.begin.y_loc && H.location.y_loc <= W.end.y_loc) dHW = Math.abs(W.begin.x_loc-H.location.x_loc);
		else dHW = 1;
		Fn.Force_scale = Main.Ai*Math.exp((Human.radius-dHW)/Main.Bi)+Main.K*Main.g(Human.radius-dHW);
		Fn.Force_scale = 0.8;
		//计算切向作用力
		//social_force.Force Ft = new social_force.Force();
		//Ft.Fdir.x_dir = Fn.Fdir.y_dir; Ft.Fdir.y_dir = -Fn.Fdir.x_dir;
		//double deta_v;
		//if (W.IsLevel) deta_v = H.Velocity*H.Vdirection.x_dir;
		//else deta_v = H.Velocity*H.Vdirection.y_dir;
		//Ft.Force_scale = social_force.Main.k*social_force.Main.g(Human.radius-dHW)*deta_v;
		
		//return social_force.Force.MergeForce(Fn, Ft);
		return Fn;
	}
	
	//力的合成
	static Force MergeForce(Force F1, Force F2) {
		Force F = new Force();
		double Fx = F1.Force_scale*F1.Fdir.x_dir+F2.Force_scale*F2.Fdir.x_dir;
		double Fy = F1.Force_scale*F1.Fdir.y_dir+F2.Force_scale*F2.Fdir.y_dir;
		F.Force_scale = Math.sqrt(Fx*Fx+Fy*Fy);
		F.Fdir.x_dir = Fx/F.Force_scale;
		F.Fdir.y_dir = Fy/F.Force_scale;
		return F;
	}
}
