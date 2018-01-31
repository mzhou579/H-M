public class EliteMonster{
    private int x_coord;
    private int y_coord;
    private int health;
    private int attack;
    private int x = 0;
    
    public EliteMonster(){
        health = 300;
        attack = 10;
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
    }
    
    public void setHealth(int h){
        health = h;
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getAttack(Hero hero){
        if(x == 0){
            if(hero.getArmor()){
                attack = attack - (int)(attack / 3);
                x++;
            }
        }
        return attack;
    }
}
