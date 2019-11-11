package social_force;

class Wall {
    Location begin;  //起始点
    Location end;    //终止点
    boolean IsLevel; //是否水平
    static double THICKNESS = 0.5; //墻的厚度

    Wall() {
        begin = new Location();
        end = new Location();
    }
}
