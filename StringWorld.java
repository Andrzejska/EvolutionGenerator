package agh.cs.project;

public class StringWorld {
    public static void main(String args[]) throws  InterruptedException {
        JungleMap map = new JungleMap(10, 20, 0.2);
        Animal Adam = new Animal(map,45);
        Animal EwaChild = new Animal(map,65);
        Animal Ewa = new Animal(map,55);
        map.place(Adam);
        map.place(EwaChild);
        map.place(Ewa);
        map.place(new Grass(new Vector2d(0,2)));
        int i=0;
        while(true){
            System.out.println(++i+"\n");
            map.dayTurn();}
    }
}
