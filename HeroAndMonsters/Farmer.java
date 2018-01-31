public class Farmer{
    private int number;
    private String voice;
    private int x_coord;
    private int y_coord;
    
    public Farmer(int n){
        number = n;
        if(number == 0){
            voice = "My sheep have been taken… My family is gone and I have nothing left except for bronze amor I have buried… Only a true hero will receive this.";
        }else{
            voice = "I have a sword that can do lots of damage, if you kill more than 4 monsters, you can take it as a gift";
        }
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
    }
 
    public String toString(){
        return voice;
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
    
    public String voiceLine(){
        return voice;
    }
}
