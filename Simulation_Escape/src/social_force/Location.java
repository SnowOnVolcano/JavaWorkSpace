package social_force;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Location {
    //坐标
    double x_loc;
    double y_loc;

    //放大的倍率
    private double MULTIPLE = 0.026;

    //存储bfs的起步方向
    private Direction_4D dir;
    private static Random rand = new Random(342);

    //随机在有效位置内生成随机点
    Location() {
        int x_ceil = rand.nextInt(320) + 140; //140~460
        int y_ceil = rand.nextInt(242) + 141; //141~383
        while (x_ceil >= 358 && x_ceil <= 403 && y_ceil >= 265 && y_ceil <= 287) {
            x_ceil = rand.nextInt(320) + 140;
            y_ceil = rand.nextInt(242) + 141;
        }
        x_loc = x_ceil * MULTIPLE;
        y_loc = y_ceil * MULTIPLE;
    }

    //在指定位置生成点
    Location(double x, double y) {
        x_loc = x;
        y_loc = y;
    }

    //获取距离
    static double getDistance(Location src, Location des) {
        return Math.sqrt((src.x_loc - des.x_loc) * (src.x_loc - des.x_loc) + (src.y_loc - des.y_loc) * (src.y_loc - des.y_loc));
    }

    //8方向bfs算法
    Direction_4D bfs(int ID) {
        int width = 500;
        int x_ceil = (int) Math.round(x_loc / MULTIPLE);
        int y_ceil = (int) Math.round(y_loc / MULTIPLE);
        Queue<Location> BFS_queue = new LinkedList<Location>();
        boolean[] Vis = new boolean[260000];
        for (int i = 0; i < 250000; i++)
            Vis[i] = false;
        BFS_queue.add(this);
        Vis[y_ceil * width + x_ceil] = true;
        boolean flag = true;
        while (!BFS_queue.isEmpty()) {
            Location point = BFS_queue.remove();
            x_ceil = (int) Math.round(point.x_loc / MULTIPLE);
            y_ceil = (int) Math.round(point.y_loc / MULTIPLE);
            Location point_1, point_2, point_3, point_4, point_5, point_6, point_7, point_8;
            if (flag) {
                point_1 = new Location(x_ceil * MULTIPLE, (y_ceil + 8) * MULTIPLE);
                point_1.dir = Direction_4D.UP;
                point_2 = new Location(x_ceil * MULTIPLE, (y_ceil - 8) * MULTIPLE);
                point_2.dir = Direction_4D.DOWN;
                point_3 = new Location((x_ceil - 8) * MULTIPLE, y_ceil * MULTIPLE);
                point_3.dir = Direction_4D.LEFT;
                point_4 = new Location((x_ceil + 8) * MULTIPLE, y_ceil * MULTIPLE);
                point_4.dir = Direction_4D.RIGHT;
                point_5 = new Location((x_ceil - 6) * MULTIPLE, (y_ceil + 6) * MULTIPLE);
                point_5.dir = Direction_4D.UP_LEFT;
                point_6 = new Location((x_ceil + 6) * MULTIPLE, (y_ceil + 6) * MULTIPLE);
                point_6.dir = Direction_4D.UP_RIGHT;
                point_7 = new Location((x_ceil - 6) * MULTIPLE, (y_ceil - 6) * MULTIPLE);
                point_7.dir = Direction_4D.DOWN_LEFT;
                point_8 = new Location((x_ceil + 6) * MULTIPLE, (y_ceil - 6) * MULTIPLE);
                point_8.dir = Direction_4D.DOWN_RIGHT;
                flag = false;
            } else {
                point_1 = new Location((x_ceil - 8) * MULTIPLE, y_ceil * MULTIPLE);
                point_1.dir = point.dir;
                point_2 = new Location((x_ceil + 8) * MULTIPLE, y_ceil * MULTIPLE);
                point_2.dir = point.dir;
                point_3 = new Location(x_ceil * MULTIPLE, (y_ceil + 8) * MULTIPLE);
                point_3.dir = point.dir;
                point_4 = new Location(x_ceil * MULTIPLE, (y_ceil - 8) * MULTIPLE);
                point_4.dir = point.dir;
                point_5 = new Location((x_ceil - 6) * MULTIPLE, (y_ceil + 6) * MULTIPLE);
                point_5.dir = point.dir;
                point_6 = new Location((x_ceil + 6) * MULTIPLE, (y_ceil + 6) * MULTIPLE);
                point_6.dir = point.dir;
                point_7 = new Location((x_ceil - 6) * MULTIPLE, (y_ceil - 6) * MULTIPLE);
                point_7.dir = point.dir;
                point_8 = new Location((x_ceil + 6) * MULTIPLE, (y_ceil - 6) * MULTIPLE);
                point_8.dir = point.dir;
            }
            //System.out.println(((int)Math.round(point_1.y_loc/MULTIPLE))*width+(int)Math.round(point_1.x_loc/MULTIPLE));
            if (Vis[((int) Math.round(point_1.y_loc / MULTIPLE)) * width + (int) Math.round(point_1.x_loc / MULTIPLE)])
                ;
            else {
                if (point_1.x_loc <= 3.666 || point_1.y_loc >= 9.958 || point_1.y_loc <= 3.692)
                    ;
                else if (point_1.x_loc >= Main.BARRIER_WEST_X && point_1.x_loc <= Main.BARRIER_EAST_X && point_1.y_loc >= 6.89 && point_1.y_loc <= 7.488)
                    ;
                else if (point_1.x_loc >= Main.WALL_EAST_X && (point_1.y_loc >= 7.332 || point_1.y_loc <= 6.334))
                    ;
                else if (point_1.x_loc >= 12.454)
                    return point_1.dir;
                else {
                    Vis[((int) Math.round(point_1.y_loc / MULTIPLE)) * width + (int) Math.round(point_1.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_1);
                }

            }

            if (Vis[((int) Math.round(point_2.y_loc / MULTIPLE)) * width + (int) Math.round(point_2.x_loc / MULTIPLE)])
                ;
            else {
                if (point_2.x_loc <= 3.666 || point_2.y_loc >= 9.958 || point_2.y_loc <= 3.692)
                    ;
                else if (point_2.x_loc >= Main.BARRIER_WEST_X && point_2.x_loc <= Main.BARRIER_EAST_X && point_2.y_loc >= 6.89 && point_2.y_loc <= 7.488)
                    ;
                else if (point_2.x_loc >= Main.WALL_EAST_X && (point_2.y_loc >= 7.332 || point_2.y_loc <= 6.334))
                    ;
                else if (point_2.x_loc >= 12.454)
                    return point_2.dir;
                else {
                    Vis[((int) Math.round(point_2.y_loc / MULTIPLE)) * width + (int) Math.round(point_2.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_2);
                }
            }

            if (Vis[((int) Math.round(point_3.y_loc / MULTIPLE)) * width + (int) Math.round(point_3.x_loc / MULTIPLE)])
                ;
            else {
                if (point_3.x_loc <= 3.666 || point_3.y_loc >= 9.958 || point_3.y_loc <= 3.692)
                    ;
                else if (point_3.x_loc >= Main.BARRIER_WEST_X && point_3.x_loc <= Main.BARRIER_EAST_X && point_3.y_loc >= 6.89 && point_3.y_loc <= 7.488)
                    ;
                else if (point_3.x_loc >= Main.WALL_EAST_X && (point_3.y_loc >= 7.332 || point_3.y_loc <= 6.334))
                    ;
                else if (point_3.x_loc >= 12.454)
                    return point_3.dir;
                else {
                    Vis[((int) Math.round(point_3.y_loc / MULTIPLE)) * width + (int) Math.round(point_3.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_3);
                }
            }

            if (Vis[((int) Math.round(point_4.y_loc / MULTIPLE)) * width + (int) Math.round(point_4.x_loc / MULTIPLE)])
                ;
            else {
                if (ID == 9)
                    System.out.println("BFS test:(" + point_4.x_loc + "," + point_4.y_loc + ")");
                if (point_4.x_loc <= 3.666 || point_4.y_loc >= 9.958 || point_4.y_loc <= 3.692)
                    ;
                else if (point_4.x_loc >= Main.BARRIER_WEST_X && point_4.x_loc <= Main.BARRIER_EAST_X && point_4.y_loc >= 6.89 && point_4.y_loc <= 7.488)
                    ;
                else if (point_4.x_loc >= Main.WALL_EAST_X && (point_4.y_loc >= 7.332 || point_4.y_loc <= 6.334))
                    ;
                else if (point_4.x_loc >= 12.454)
                    return point_4.dir;
                else {
                    Vis[((int) Math.round(point_4.y_loc / MULTIPLE)) * width + (int) Math.round(point_4.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_4);
                }
            }
            if (Vis[((int) Math.round(point_5.y_loc / MULTIPLE)) * width + (int) Math.round(point_5.x_loc / MULTIPLE)])
                ;
            else {
                if (point_5.x_loc <= 3.666 || point_5.y_loc >= 9.958 || point_5.y_loc <= 3.692)
                    ;
                else if (point_5.x_loc >= Main.BARRIER_WEST_X && point_5.x_loc <= Main.BARRIER_EAST_X && point_5.y_loc >= 6.89 && point_5.y_loc <= 7.488)
                    ;
                else if (point_5.x_loc >= Main.WALL_EAST_X && (point_5.y_loc >= 7.332 || point_5.y_loc <= 6.334))
                    ;
                else if (point_5.x_loc >= 12.454)
                    return point_5.dir;
                else {
                    Vis[((int) Math.round(point_5.y_loc / MULTIPLE)) * width + (int) Math.round(point_5.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_5);
                }

            }

            if (Vis[((int) Math.round(point_6.y_loc / MULTIPLE)) * width + (int) Math.round(point_6.x_loc / MULTIPLE)])
                ;
            else {
                if (point_6.x_loc <= 3.666 || point_6.y_loc >= 9.958 || point_6.y_loc <= 3.692)
                    ;
                else if (point_6.x_loc >= Main.BARRIER_WEST_X && point_6.x_loc <= Main.BARRIER_EAST_X && point_6.y_loc >= 6.89 && point_6.y_loc <= 7.488)
                    ;
                else if (point_6.x_loc >= Main.WALL_EAST_X && (point_6.y_loc >= 7.332 || point_6.y_loc <= 6.334))
                    ;
                else if (point_6.x_loc >= 12.454)
                    return point_6.dir;
                else {
                    Vis[((int) Math.round(point_6.y_loc / MULTIPLE)) * width + (int) Math.round(point_6.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_6);
                }
            }

            if (Vis[((int) Math.round(point_7.y_loc / MULTIPLE)) * width + (int) Math.round(point_7.x_loc / MULTIPLE)])
                ;
            else {
                if (point_7.x_loc <= 3.666 || point_7.y_loc >= 9.958 || point_7.y_loc <= 3.692)
                    ;
                else if (point_7.x_loc >= Main.BARRIER_WEST_X && point_7.x_loc <= Main.BARRIER_EAST_X && point_7.y_loc >= 6.89 && point_7.y_loc <= 7.488)
                    ;
                else if (point_7.x_loc >= Main.WALL_EAST_X && (point_7.y_loc >= 7.332 || point_7.y_loc <= 6.334))
                    ;
                else if (point_7.x_loc >= 12.454)
                    return point_7.dir;
                else {
                    Vis[((int) Math.round(point_7.y_loc / MULTIPLE)) * width + (int) Math.round(point_7.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_7);
                }
            }

            if (Vis[((int) Math.round(point_8.y_loc / MULTIPLE)) * width + (int) Math.round(point_8.x_loc / MULTIPLE)])
                ;
            else {
                if (ID == 9)
                    System.out.println("BFS test:(" + point_8.x_loc + "," + point_8.y_loc + ")");
                if (point_8.x_loc <= 3.666 || point_8.y_loc >= 9.958 || point_8.y_loc <= 3.692)
                    ;
                else if (point_8.x_loc >= Main.BARRIER_WEST_X && point_8.x_loc <= Main.BARRIER_EAST_X && point_8.y_loc >= 6.89 && point_8.y_loc <= 7.488)
                    ;
                else if (point_8.x_loc >= Main.WALL_EAST_X && (point_8.y_loc >= 7.332 || point_8.y_loc <= 6.334))
                    ;
                else if (point_8.x_loc >= 12.454)
                    return point_8.dir;
                else {
                    Vis[((int) Math.round(point_8.y_loc / MULTIPLE)) * width + (int) Math.round(point_8.x_loc / MULTIPLE)] = true;
                    BFS_queue.add(point_8);
                }
            }
        }
        System.out.println("出口不通！");
        return null;
    }
}
