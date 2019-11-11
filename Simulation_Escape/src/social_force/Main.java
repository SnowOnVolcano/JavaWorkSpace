package social_force;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    static int HUMAN_SUM = 100;              //总人数
    static double WALL_WAST_X = 4;       //西侧墙的横坐标
    static double WALL_EAST_X = 12;      //东侧墙的横坐标
    static double WALL_SOUTH_Y = 4;     //南侧墙的纵坐标
    static double WALL_NORTH_Y = 10;     //北侧墙的纵坐标
    static double DOOR_SOUTH_Y = 6.5;    //门南侧的纵坐标
    static double DOOR_NORTH_Y = 7.5;    //门北侧的纵坐标
    static double BARRIER_WEST_X = 7;   //障碍物西的横坐标
    static double BARRIER_EAST_X = 8;  //障碍物东的横坐标
    static double BARRIER_SOUTH_Y = 5;  //障碍物南的纵坐标
    static double BARRIER_NORTH_Y = 9;  //障碍物北的纵坐标

    static double Ai = 2000;                //N
    static double Bi = 0.08;                //m
    static double K = 120000;               //kg*s^-2
    public static double k = 240000;        //kg*m^-1*s^-1
    static double deta_t = 1 / 30.0;        //s
    static Human[] h_list = new Human[HUMAN_SUM];  //存储人的表
    static Wall[] w_list = new Wall[9];     //存储墙的表
    private static PrintStream log;         //重定向的输出流

    //文件输出的统一接口
    private static void Print_log(String str) {
        log.println(str);
    }

    //主函数
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fs;
        try {
            fs = new FileOutputStream(new File("log.txt"));
            log = new PrintStream(fs);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //初始化人的表，表中共计有HUMAN_SUM个人
        for (int i = 0; i < Main.HUMAN_SUM; i++) {
            Main.h_list[i] = new Human(i);
        }
        //初始化墙的表
        for (int i = 0; i < 9; i++)
            Main.w_list[i] = new Wall();

        //设置墙的相关参数
        Main.w_list[0].begin.x_loc = Main.WALL_WAST_X;
        Main.w_list[0].begin.y_loc = Main.WALL_NORTH_Y;
        Main.w_list[0].end.x_loc = Main.WALL_EAST_X;
        Main.w_list[0].end.y_loc = Main.WALL_NORTH_Y;
        Main.w_list[0].IsLevel = true;

        Main.w_list[1].begin.x_loc = Main.WALL_WAST_X;
        Main.w_list[1].begin.y_loc = Main.WALL_NORTH_Y;
        Main.w_list[1].end.x_loc = Main.WALL_WAST_X;
        Main.w_list[1].end.y_loc = Main.WALL_SOUTH_Y;
        Main.w_list[1].IsLevel = false;

        Main.w_list[2].begin.x_loc = Main.WALL_WAST_X;
        Main.w_list[2].begin.y_loc = Main.WALL_SOUTH_Y;
        Main.w_list[2].end.x_loc = Main.WALL_EAST_X;
        Main.w_list[2].end.y_loc = Main.WALL_SOUTH_Y;
        Main.w_list[2].IsLevel = true;

        Main.w_list[3].begin.x_loc = Main.WALL_EAST_X;
        Main.w_list[3].begin.y_loc = Main.WALL_NORTH_Y;
        Main.w_list[3].end.x_loc = Main.WALL_EAST_X;
        Main.w_list[3].end.y_loc = Main.DOOR_NORTH_Y;
        Main.w_list[3].IsLevel = false;

        Main.w_list[4].begin.x_loc = Main.WALL_EAST_X;
        Main.w_list[4].begin.y_loc = Main.DOOR_SOUTH_Y;
        Main.w_list[4].end.x_loc = Main.WALL_EAST_X;
        Main.w_list[4].end.y_loc = Main.WALL_SOUTH_Y;
        Main.w_list[4].IsLevel = false;

        Main.w_list[5].begin.x_loc = Main.BARRIER_WEST_X;
        Main.w_list[5].begin.y_loc = Main.BARRIER_NORTH_Y;
        Main.w_list[5].end.x_loc = Main.BARRIER_WEST_X;
        Main.w_list[5].end.y_loc = Main.BARRIER_SOUTH_Y;
        Main.w_list[5].IsLevel = false;

        Main.w_list[6].begin.x_loc = Main.BARRIER_WEST_X;
        Main.w_list[6].begin.y_loc = Main.BARRIER_NORTH_Y;
        Main.w_list[6].end.x_loc = Main.BARRIER_EAST_X;
        Main.w_list[6].end.y_loc = Main.BARRIER_NORTH_Y;
        Main.w_list[6].IsLevel = true;

        Main.w_list[7].begin.x_loc = Main.BARRIER_WEST_X;
        Main.w_list[7].begin.y_loc = Main.BARRIER_SOUTH_Y;
        Main.w_list[7].end.x_loc = Main.BARRIER_EAST_X;
        Main.w_list[7].end.y_loc = Main.BARRIER_SOUTH_Y;
        Main.w_list[7].IsLevel = true;

        Main.w_list[8].begin.x_loc = Main.BARRIER_EAST_X;
        Main.w_list[8].begin.y_loc = Main.BARRIER_NORTH_Y;
        Main.w_list[8].end.x_loc = Main.BARRIER_EAST_X;
        Main.w_list[8].end.y_loc = Main.BARRIER_SOUTH_Y;
        Main.w_list[8].IsLevel = false;

        //让人开始运动，总共运动400帧
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < HUMAN_SUM; j++) {
                Main.h_list[j].run();
            }
        }

        //输出
        for (int i = 0; i < HUMAN_SUM; i++) {
            for (int j = 0; j < 400; j++) {
                Location L = Main.h_list[i].path.get(j);
                Main.Print_log(L.x_loc + " " + L.y_loc);
            }
        }

    }

    //社会力模型中常用的g(x)函数
    static double g(double x) {
        return (x < 0) ? 0 : x;
    }
}
