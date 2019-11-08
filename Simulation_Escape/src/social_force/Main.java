package social_force;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static double Ai = 2000;                //N
    public static double Bi = 0.08;                //m
    public static double K = 120000;               //kg*s^-2
    public static double k = 240000;               //kg*m^-1*s^-1
    public static double deta_t = 1 / 30.0;          //s
    public static Human[] h_list = new Human[26];  //存储人的表
    public static Wall[] w_list = new Wall[9];     //存储墙的表
    public static PrintStream log;                 //重定向的输出流

    //文件输出的统一接口
    public static void Print_log(String str) {
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

        //初始化人的表，表中共计有26人
        for (int i = 0; i < 26; i++) {
            Main.h_list[i] = new Human(i);
        }
        //初始化墙的表
        for (int i = 0; i < 9; i++)
            Main.w_list[i] = new Wall();

        //设置墙的相关参数
        Main.w_list[0].begin.x_loc = 3.66;
        Main.w_list[0].begin.y_loc = 9.977;
        Main.w_list[0].end.x_loc = 11.96;
        Main.w_list[0].end.y_loc = 9.977;
        Main.w_list[0].IsLevel = true;

        Main.w_list[1].begin.x_loc = 3.66;
        Main.w_list[1].begin.y_loc = 9.977;
        Main.w_list[1].end.x_loc = 3.66;
        Main.w_list[1].end.y_loc = 3.689;
        Main.w_list[1].IsLevel = false;

        Main.w_list[2].begin.x_loc = 3.66;
        Main.w_list[2].begin.y_loc = 3.689;
        Main.w_list[2].end.x_loc = 11.96;
        Main.w_list[2].end.y_loc = 3.689;
        Main.w_list[2].IsLevel = true;

        Main.w_list[3].begin.x_loc = 11.96;
        Main.w_list[3].begin.y_loc = 9.977;
        Main.w_list[3].end.x_loc = 11.96;
        Main.w_list[3].end.y_loc = 7.3375;
        Main.w_list[3].IsLevel = false;

        Main.w_list[4].begin.x_loc = 11.96;
        Main.w_list[4].begin.y_loc = 6.3375;
        Main.w_list[4].end.x_loc = 11.96;
        Main.w_list[4].end.y_loc = 3.689;
        Main.w_list[4].IsLevel = false;

        Main.w_list[5].begin.x_loc = 9.308;
        Main.w_list[5].begin.y_loc = 7.481;
        Main.w_list[5].end.x_loc = 9.308;
        Main.w_list[5].end.y_loc = 6.909;
        Main.w_list[5].IsLevel = false;

        Main.w_list[6].begin.x_loc = 9.308;
        Main.w_list[6].begin.y_loc = 7.481;
        Main.w_list[6].end.x_loc = 10.478;
        Main.w_list[6].end.y_loc = 7.481;
        Main.w_list[6].IsLevel = true;

        Main.w_list[7].begin.x_loc = 9.308;
        Main.w_list[7].begin.y_loc = 6.909;
        Main.w_list[7].end.x_loc = 10.478;
        Main.w_list[7].end.y_loc = 6.909;
        Main.w_list[7].IsLevel = true;

        Main.w_list[8].begin.x_loc = 10.478;
        Main.w_list[8].begin.y_loc = 7.481;
        Main.w_list[8].end.x_loc = 10.478;
        Main.w_list[8].end.y_loc = 6.909;
        Main.w_list[8].IsLevel = false;

        //让人开始运动，总共运动400帧
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 26; j++) {
                Main.h_list[j].run();
            }
        }

        //输出
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 400; j++) {
                Location L = Main.h_list[i].path.get(j);
                Main.Print_log(L.x_loc + " " + L.y_loc);
            }
        }

    }

    //社会力模型中常用的g(x)函数
    public static double g(double x) {
        return (x < 0) ? 0 : x;
    }
}
