public abstract class Monster{
    private int attack;
    private int health;
    private int speed;
    private int x = 0;
    
    public Monster(){
        attack = (int)(Math.random() * 20) + 10;
        health = (int)(Math.random() * 50) + 1;
        speed = (int)(Math.random() * 4);
    }
    
    public void setX(){
        x = 0;
    }
    
    public int getAtt(Hero hero){
        if(x == 0){
            if(hero.getArmor()){
                attack -= (int)(attack / 3);
                x++;
            }
        }
        return attack;
    }
    
    public boolean death(){
        if(health <= 0){
            return true;
        }
        return false;
    }
    
    public int getHea(){
        return health;
    }
    
    public void setHea(int h){
        health = h;
    }
    
    public int getSpe(){
        return speed;
    }
    
    public abstract boolean checkWeapon(String weapon);
    
    public String toString(){
        return "Monster: \nAttack: " + attack + "\nHealth: " + health + "\nspeed: " + speed;
    }
}
