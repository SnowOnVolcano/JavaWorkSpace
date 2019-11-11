package social_force;

import java.util.ArrayList;

//枚举8个方向
enum Direction_4D {UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};

public class Human {
    private int ID;                       //人的ID
    private boolean disabled;             //是否已经到达终点
    private Direction_4D desired_dir;     //期望速度的方向
    Location location;            //当前坐标
    private Direction Fdirection;         //受力方向
    public double Force;                 //受力大小
    private Direction Vdirection;         //真实速度方向
    private double Velocity;              //真实速度大小
    private double desired_velocity = 1;  //期望速度
    static double radius = 0.2;   //人的半径
    private static double M = 60.0;       //人的质量
    ArrayList<Location> path;     //人走过的路径（输出时用）

    //Human构造函数，须确定编号
    Human(int i) {
        ID = i;
        disabled = false;
        Velocity = 0;
        //随机初始化出生点
        location = new Location();
        //bfs查找期望方向
        desired_dir = location.bfs(i);
        Fdirection = new Direction();
        Vdirection = new Direction();
        path = new ArrayList<Location>();
    }

    //检查两个人是否接触
    public boolean IsTouched_h2h(Human A, Human B) {
        return Location.getDistance(A.location, B.location) <= Human.radius;
    }

    //human运行函数
    void run() {
        this.path.add(new Location(location.x_loc, location.y_loc));
        //bfs确定新的方向
        desired_dir = location.bfs(this.ID);
        Force F = new Force();
        if (desired_dir == Direction_4D.DOWN) {
            F = new Force(Human.M * (-Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (-desired_velocity - Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.UP) {
            F = new Force(Human.M * (-Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (desired_velocity - Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.LEFT) {
            F = new Force(Human.M * (-desired_velocity - Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (-Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.RIGHT) {
            F = new Force(Human.M * (desired_velocity - Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (-Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.UP_LEFT) {
            F = new Force(Human.M * (-desired_velocity * 0.707 - Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (desired_velocity * 0.707 - Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.UP_RIGHT) {
            F = new Force(Human.M * (desired_velocity * 0.707 - Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (desired_velocity * 0.707 - Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.DOWN_LEFT) {
            F = new Force(Human.M * (-desired_velocity * 0.707 - Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (-desired_velocity * 0.707 - Velocity * Vdirection.y_dir) / Main.deta_t);
        } else if (desired_dir == Direction_4D.DOWN_RIGHT) {
            F = new Force(Human.M * (desired_velocity * 0.707 - Velocity * Vdirection.x_dir) / Main.deta_t, Human.M * (-desired_velocity * 0.707 - Velocity * Vdirection.y_dir) / Main.deta_t);
        } else ;
        //计算期望速度和实际速度的速度差产生的力

        //计算人与人之间的作用力
        for (int i = 0; i < Main.HUMAN_SUM; i++) {
            if (i != ID && !Main.h_list[i].disabled)
                F = social_force.Force.MergeForce(F, social_force.Force.getForce_h2h(Main.h_list[i], this));
        }
        //计算人与墙之间的作用力
        for (int i = 0; i < 9; i++) {
            F = social_force.Force.MergeForce(F, social_force.Force.getForce_h2w(this, Main.w_list[i]));
        }
        //根据力计算速度
        double x_v = Velocity * Vdirection.x_dir + F.Force_scale / Human.M * F.Fdir.x_dir * Main.deta_t;
        double y_v = Velocity * Vdirection.y_dir + F.Force_scale / Human.M * F.Fdir.y_dir * Main.deta_t;
        this.Velocity = Math.sqrt(x_v * x_v + y_v * y_v);
        this.Vdirection.x_dir = x_v / Velocity;
        this.Vdirection.y_dir = y_v / Velocity;
        if (this.Velocity == 0) {
            this.Vdirection.x_dir = 1;
            this.Vdirection.y_dir = 0;
        }
        //根据速度计算位移
        this.location.x_loc += x_v * Main.deta_t;
        this.location.y_loc += y_v * Main.deta_t;
        if (this.location.x_loc > 12.44) {
            this.disabled = true;
        }
    }

}
