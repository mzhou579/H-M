import java.util.*;
import java.io.*;
public class Tester{
    public static void main(String args[]){
        int m = 10;
        Monster[] monster = new Monster[m];
        Farmer[] farmer = new Farmer[2];
        Potion[] potion = new Potion[3];
        Weapon weapon = new Weapon();
        Hero hero = new Hero();
        System.out.println("** Hero & Monsters **");
        Object[][] map = new Object[15][15];
        fillMap(map);
        System.out.println("* The map has been generated *");
        updateHero(map,hero);
        System.out.println("* The enemies have been placed *");
        System.out.println("* The items have been placed *");
        System.out.println("* Remember you can only win if you defeat the BOSS *");
        String x = "";
        String y = "";
        int xcoord = (int)(Math.random() * 10);
        int ycoord = (int)(Math.random() * 10);
        int a = 1;
        int b = 0;
        int f = 0;
        int battleLoop = 1;
        int monsterKilled = 0;
        int potionNum = 3;
        int p = 0;
        int potionCarried = 0;
        int potionCondition = 0;
        int energyDrink = 0;
        int shop = 0;
        int hides = 0;
        int getHides = 20;
        int bossLoop = 0;
        int traderLoop = 0;
        int bossBattleLoop = 1;
        int bossKilled = 0;
        int weaponSwitchLoop = 0;
        
        if(xcoord > 5){
            x = "east";
        }else if(xcoord < 5){
            x = "west";
        }
        
        if(ycoord > 5){
            y = "North";
        }else if(ycoord < 5){
            y = "South";
        }
        
        if(xcoord == 5 && ycoord == 5){
            System.out.println("Hero begins in the middle of the Yore. ");
        }else{
            System.out.println("Hero begins his journey in the " + y + x + " corner of Yore. ");
        }
        
        EliteMonster boss = new EliteMonster();
        if(map[boss.getX()][boss.getY()] == "[  ? ?  ]"){
            map[boss.getX()][boss.getY()] = "[ Boss  ]";
            bossLoop = 1;
        }
        
        Trader trader = new Trader();
        if(map[trader.getX()][trader.getY()] == "[  ? ?  ]"){
            map[trader.getX()][trader.getY()] = "[ Shop  ]";
            traderLoop = 1;
        }
        
        while(p < potion.length){
            potion[p] = new Potion();
            if(map[potion[p].getX()][potion[p].getY()] == "[  ? ?  ]"){
                map[potion[p].getX()][potion[p].getY()] = "[Potion ]";
                p++;
            }
        }
        
        while(f < farmer.length){
            farmer[f] = new Farmer(f);
            if(map[farmer[f].getX()][farmer[f].getY()] == "[  ? ?  ]"){
                map[farmer[f].getX()][farmer[f].getY()] = "[Farmer ]";
                f++;
            }
        }
        
        while(b < monster.length){
            monster[b] = new Monster();
            if(map[monster[b].getXcoord()][monster[b].getYcoord()] == "[  ? ?  ]"){
                map[monster[b].getXcoord()][monster[b].getYcoord()] = "[monster]";
                b++;
            }
        }

        //* turn this on/off to toggle the "wallhack" *
        fillMap(map);
        map[hero.getX()][hero.getY()] = "[ Hero  ]";
        map[boss.getX()][boss.getY()] = "[ Boss  ]";
        map[trader.getX()][trader.getY()] = "[ Shop  ]";
        
        printMap(map);
        
        while(a == 1){
            shop = 0;
            potionCondition = 0;
            battleLoop = 1;
            Scanner kbReader = new Scanner(System.in);
            System.out.println("\n\nEnter direction (north, south, east, west) \nOr enter 'info' to learn about hero's status: ");
            String direction = kbReader.nextLine();
            if(direction.equals("north") || direction.equals("w") && hero.getY() <= 14){
                if(map[hero.getX()][hero.getY()] != "[  x_x  ]" && map[hero.getX()][hero.getY()] != "[Farmer ]" && map[hero.getX()][hero.getY()] != "[monster]" && map[hero.getX()][hero.getY()] != "[ Shop  ]" && map[hero.getX()][hero.getY()] != "[ Boss  ]"){
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("n");
                updateHero(map, hero);
                checkMonsterMethod(monster, hero, map);
                if(checkMonsterNearby(monster, hero) == true){
                    System.out.println("Hero: I can sense a monster nearby!");
                }
                printMap(map);
            }else if(direction.equals("south") || direction.equals("s") && hero.getY() >= 0){
                if(map[hero.getX()][hero.getY()] != "[  x_x  ]" && map[hero.getX()][hero.getY()] != "[Farmer ]" && map[hero.getX()][hero.getY()] != "[monster]" && map[hero.getX()][hero.getY()] != "[ Shop  ]" && map[hero.getX()][hero.getY()] != "[ Boss  ]"){
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("s");
                updateHero(map, hero);
                checkMonsterMethod(monster, hero, map);
                if(checkMonsterNearby(monster, hero) == true){
                    System.out.println("Hero: I can sense a monster nearby!");
                }
                printMap(map);
            }else if(direction.equals("west") || direction.equals("a") && hero.getX() >= 0){
                if(map[hero.getX()][hero.getY()] != "[  x_x  ]" && map[hero.getX()][hero.getY()] != "[Farmer ]" && map[hero.getX()][hero.getY()] != "[monster]" && map[hero.getX()][hero.getY()] != "[ Shop  ]" && map[hero.getX()][hero.getY()] != "[ Boss  ]"){
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("w");
                updateHero(map, hero);
                checkMonsterMethod(monster, hero, map);
                if(checkMonsterNearby(monster, hero) == true){
                    System.out.println("Hero: I can sense a monster nearby!");
                }
                printMap(map);
            }else if(direction.equals("east") || direction.equals("d") && hero.getX() <= 14){
                if(map[hero.getX()][hero.getY()] != "[  x_x  ]" && map[hero.getX()][hero.getY()] != "[Farmer ]" && map[hero.getX()][hero.getY()] != "[monster]" && map[hero.getX()][hero.getY()] != "[ Shop  ]" && map[hero.getX()][hero.getY()] != "[ Boss  ]"){
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("e");
                updateHero(map, hero);
                checkMonsterMethod(monster, hero, map);
                if(checkMonsterNearby(monster, hero) == true){
                    System.out.println("Hero: I can sense a monster nearby!");
                }
                printMap(map);
            }else if(direction.equals("info")){
                System.out.println("\n\nHero now has " + hero.getHealth() + "/100 health left");
                System.out.println("Hero has defeated " + monsterKilled + " monster(s)");
                System.out.println("Weapon: " + hero.getWeapon() + " \nDamage: " + hero.getDamage());
                System.out.println("Armor: " + hero.getArmor());
                System.out.println("Hero has " + potionCarried + " potion(s) in inventory");
                System.out.println("Hero has " + hides + " gold(s)");
                System.out.println("Hero level: " + hero.getLevel());
            }else{
                System.out.println("Please enter a valid command!");
            }
            map[hero.getX()][hero.getY()] = "[ Hero  ]";
           
            int monsterNum = checkMonsterMethod(monster, hero, map);
            
            if(potionNum != 0){
                    if(checkPotionMethod(potion, hero, map) == true){
                        while(potionCondition == 0){
                        Scanner potionDecision = new Scanner(System.in);
                        System.out.println("\n\nHero has picked up a Health Potion, would you like to use it right now? (Y/N)");
                        String decision = potionDecision.nextLine();
                        if(decision.equals("Y")){
                            hero.setHealth(100);
                            System.out.println("Hero's health has been fully recovered");
                            potionNum--;
                            potionCondition = 1;
                        }else if(decision.equals("N")){
                            System.out.println("Health Potion has been added to hero's inventory");
                            System.out.println("*Remember hero can choose to drink the potion during battle*");
                            potionCarried++;
                            potionCondition = 1;
                        }else{
                            System.out.println("Please enter a valid command");
                        }
                    }
                }
            }
            
            while(shop == 0){
                if(checkTraderMethod(trader, hero) == true){
                    map[hero.getX()][hero.getY()] = "[ Shop  ]";
                    System.out.println("\n\n Hero has found a trader, he offers some supplies that can help hero!");
                    System.out.println("\n\n Trader: Hello, what would you like to buy?");
                    Scanner shopDecision = new Scanner(System.in);
                    System.out.println("1. Energy Drink (Energy drink can give hero a recovery of 10 hp, hero's health can be greater than 100)");
                    System.out.println("Cost: 10 golds");
                    System.out.println("2. Axe (A strong weapon that has a damage of 35)");
                    System.out.println("Cost: 60 golds");
                    System.out.println("3. EXCALIBUR (The legendary sword of King Arthur and Saber! Has a damage of 70)");
                    System.out.println("Cost: 140 golds");
                    System.out.println("4. Quit");
                    String shop1 = shopDecision.nextLine();
                    if(shop1.equals("1")){
                        if(hides >= 10){
                            energyDrink++;
                            hides = hides - 10;
                            hero.setHealth(hero.getHealth() + 10);
                            System.out.println("Hero's health + 10");
                        }else{
                            System.out.println("Hero doesn't have enough golds! Go kill some monsters!");
                        }
                    }else if(shop1.equals("2")){
                        if(hides >= 60){
                            hero.switchWeapon2();
                            hero.setWeapon2();
                            hides = hides - 60;
                            System.out.println("Hero now has an Axe as his weapon!");
                        }else{
                            System.out.println("Hero doesn't have enough gold! Go kill some monsters!");
                        }
                    }else if(shop1.equals("3")){
                        if(hides >= 120){
                            hero.switchWeapon1();
                            hero.setWeapon1();
                            hides = hides - 120;
                            System.out.println("Hero now has the EXCALIBUR sword as his weapon!");
                        }
                    }else if(shop1.equals("4")){
                        shop = 1;
                        printMap(map);
                    }else{
                        System.out.println("Please enter a valid command");
                    }
                }
                shop = 1;
            }
            
            if(checkFarmerMethod(farmer, hero, map) == 0){
                if(monsterKilled >= 2){
                    System.out.println("\n\nThe farmer says: Thank you so much! Here is you Armor!");
                    hero.pickArmor();
                    hero.setArmor();
                    System.out.println("* Hero now has a Armor that can block some of monster's damage! (The damage for every monster will now reduce by 1/3) *");
                }else{
                    System.out.println("Hero has found a lost farmer! \nThe farmer says: " + farmer[0].voiceLine() + "\n Defeat 2 monsters and go back to get the reward!");
                    map[hero.getX()][hero.getY()] = "[Farmer ]";
                }
            }else if(checkFarmerMethod(farmer, hero, map) == 1){
                if(monsterKilled >= 4){
                    if(hero.getWeapon().equals("EXCALIBUR")){
                        while(weaponSwitchLoop == 0){
                            Scanner exWeapon = new Scanner(System.in);
                            System.out.println("* Hero already has the legendary EXCALIBUR as his weapon, are you sure you want to change it to a normal sword? *");
                            String weaponChoice = exWeapon.nextLine();
                            if(weaponChoice.equals("Y")){
                                System.out.println("\n\nThe farmer says: Thank you so much! Here is you Sword!");
                                hero.switchWeapon();
                                hero.setWeapon();
                                System.out.println("* Hero now has a Sword as the weapon! *");
                                weaponSwitchLoop = 1;
                            }else if(weaponChoice.equals("N")){
                                System.out.println("* GOOD CHOICE *");
                                weaponSwitchLoop = 1;
                            }else{
                                System.out.println("* Please enter a valid Command *");
                            }
                        }
                    }
                }else{
                    System.out.println("\n\nHero has found a lost farmer! \nThe farmer says: " + farmer[1].voiceLine() + "\n Defeat 4 monsters and go back to get the reward!");
                    map[hero.getX()][hero.getY()] = "[Farmer ]";
                }
            }

            if(checkBossMethod(boss, hero, map) == true){
                map[hero.getX()][hero.getY()] = "[battle ]";
                while(bossBattleLoop == 1){
                    System.out.println("\n\nMonster incoming! This time it is a ELITE MONSTER! Ready to encounter");
                    printMap(map);
                    System.out.println("The BOSS's info has been updated: ");
                    System.out.println("Health: " + boss.getHealth());
                    System.out.println("Speed: -1 ");
                    System.out.println("Attack: 10");
                    Scanner bossBattle = new Scanner(System.in);
                    System.out.println("Enter an action (run, attack, recover): ");
                    String bossDecision = bossBattle.nextLine();
                    if(bossDecision.equals("run")){
                        System.out.println("\n\nHero has successfully ran away!");
                        map[hero.getX()][hero.getY()] = "[ Boss  ]";
                        bossBattleLoop = 0;
                    }else if(bossDecision.equals("attack")){
                        System.out.println("\n\nHero attacks, BOSS's energy goes down to " + (boss.getHealth() - hero.getDamage()));
                        System.out.println("\nBOSS attacks, hero's energy goes down to " + (hero.getHealth() - boss.getAttack(hero)) + "/100");
                        boss.setHealth(boss.getHealth() - hero.getDamage());
                        hero.setHealth(hero.getHealth() - boss.getAttack(hero));
                        if(boss.getHealth() <= 0){
                            bossBattleLoop = 0;
                            bossKilled++;
                            if(bossKilled == 1){
                                System.out.println("* Hero has defeated the legendary BOSS! *");
                                System.out.println("* YOU WIN *");
                                a = 0;
                                bossBattleLoop = 0;
                            }
                        }
                        if(hero.death()){
                            System.out.println("* Hero collapses and is struck a deadly blow by The BOSS *");
                            System.out.println("* GAME OVER *");
                            a = 0;
                            bossBattleLoop = 0;
                        }
                    }else if(bossDecision.equals("recover")){
                        hero.setHealth(100);
                        potionCarried--;
                        System.out.println("\n\nHero's health has been recoverd");
                    }
                }
            }
            
            if(checkMonsterMethod(monster, hero, map) != 10){
                map[hero.getX()][hero.getY()] = "[battle ]";
                while(battleLoop == 1){
                    System.out.println("\n\nMonster incoming! Ready to encounter!");
                    printMap(map);
                    System.out.println("The monster's info has been updated: ");
                    System.out.println("Health: " + monster[monsterNum].getHea());
                    System.out.println("Speed: " + monster[monsterNum].getSpe());
                    System.out.println("Attack: " + monster[monsterNum].getAtt(hero));
                    Scanner Battle = new Scanner(System.in);
                    System.out.println("Enter an action (run, attack, recover): ");
                    String battle = Battle.nextLine();
                    if(battle.equals("run")){
                        if(monster[monsterNum].getSpe() == 3){
                            System.out.println("\n\nHero tries to run! The monster is too fast!");
                            
                            System.out.println("The monster attacks! Hero’s energy goes down to " + (hero.getHealth() - monster[monsterNum].getAtt(hero)) + "/100");
                            hero.setHealth(hero.getHealth() - monster[monsterNum].getAtt(hero));
                            
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by Monster*");
                                System.out.println("GAME OVER");
                                a = 0;
                                battleLoop = 0;
                            }
                        }
                        
                        if(monster[monsterNum].getSpe() == 2){
                            int chance = (int)(Math.random() * 4 + 1);
                            if(chance == 1){
                                System.out.println("\n\nHero has successfully ran away!");
                                map[hero.getX()][hero.getY()] = "[monster]";
                                battleLoop = 0;
                            }else{
                                System.out.println("\n\nHero tries to run! The monster is too fast!");
                                
                                System.out.println("The monster attacks! Hero’s energy goes down to " + (hero.getHealth() - monster[monsterNum].getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster[monsterNum].getAtt(hero));
                                
                                if(hero.death()){
                                    System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }
                        
                        if(monster[monsterNum].getSpe() == 1){
                            int chance1 = (int)(Math.random() * 2 + 1);
                            if(chance1 == 1){
                                System.out.println("\n\nHero has successfully ran away!");
                                map[hero.getX()][hero.getY()] = "[monster]";
                                battleLoop = 0;
                            }else{
                                System.out.println("\n\nHero tries to run! The monster is too fast!");
                                
                                System.out.println("The monster attacks! Hero’s energy goes down to " + (hero.getHealth() - monster[monsterNum].getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster[monsterNum].getAtt(hero));
                                if(hero.death()){
                                    System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }
                        
                        if(monster[monsterNum].getSpe() == 0){
                            int chance2 = (int)(Math.random() * 4 + 1);
                            if(chance2 == 2 || chance2 == 3 || chance2 == 4){
                                System.out.println("\n\nHero has successfully ran away!");
                                map[hero.getX()][hero.getY()] = "[monster]";
                                battleLoop = 0;
                            }else{
                                System.out.println("\n\nHero tries to run! The monster is too fast!");
                                
                                System.out.println("The monster attacks! Hero’s energy goes down to " + (hero.getHealth() - monster[monsterNum].getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster[monsterNum].getAtt(hero));
                                
                                
                                if(hero.death()){
                                    System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }
                    }
                    
                    if(battle.equals("attack")){
                        System.out.println("\n\nHero attacks, the monster's energy goes down to " + (monster[monsterNum].getHea() - hero.getDamage() + "/100"));
                        System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster[monsterNum].getAtt(hero)) + "/100");
                        hero.setHealth(hero.getHealth() - monster[monsterNum].getAtt(hero));
                        monster[monsterNum].setHea(monster[monsterNum].getHea() - hero.getDamage());
                        if(monster[monsterNum].getHea() <= 0 ){
                            System.out.println("\n\nThe monster has been defeated!");
                            monster[monsterNum].setX();
                            hero.setExp(hero.getExp() + 15);
                            System.out.println("Hero EXP + 15");
                            System.out.println("Reward: +" + getHides +" Hides");
                            hides = hides + getHides;
                            monsterKilled++;
                            map[hero.getX()][hero.getY()] = "[  x_x  ]";
                            printMap(map);
                            battleLoop = 0;
                            if(hero.getExp() >= hero.getLevelUpExp()){
                                hero.upgrade();
                                System.out.println("LEVEL UP!");
                                hero.setHealth(hero.getHealth() + 20);
                                System.out.println("Hero has gained 20 extra HP!");
                            }
                        }
                        if(monster[monsterNum].getHea() <= 0 || hero.getHealth() <= 0){
                            if(hero.death()){
                                System.out.println("*Hero has defeated the monster but also lost all of his energy!*");
                                System.out.println("GAME OVER");
                            }
                        }
                        if(monsterKilled == 10){
                            System.out.println("*Hero has defeated all of the monsters*");
                            System.out.println("*Now go and defeat the final BOSS*");
                        }
                        if(hero.death()){
                            System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                            System.out.println("GAME OVER");
                            a = 0;
                            battleLoop = 0;
                        }
                    }      
                    
                    if(battle.equals("recover")){
                        if(potionCarried != 0){
                            hero.setHealth(100);
                            potionCarried--;
                            System.out.println("\n\nHero's health has been recoverd");
                        }else{
                            System.out.println("\n\nHero doesn't have any potion to drink!");
                        }
                    }
                    
                    if(!battle.equals("run") && !battle.equals("attack") && !battle.equals("recover")){
                        System.out.println("Please enter a valid command!");
                    }
                }
            }
        }
    }
    
    public static void fillMap(Object[][]map){
        for(int k = 0; k < map.length; k++){
            for(int l = 0; l < map[0].length; l++){
                map[k][l] = "[  ? ?  ]";
            }
        }
    }
    
    public static void printMap(Object[][] map){
        for(int o = 0; o < map.length; o++){
            for(int p = 0; p < map[0].length; p++){
                System.out.print(map[o][p] + "  ");
            }
            System.out.println();
        }
    }

    public static void updateHero(Object[][] map, Hero hero){
        map[hero.getX()][hero.getY()] = "[ Hero  ]";
    }
    
    public static int checkMonsterMethod(Monster[] monster, Hero hero, Object[][] map){
        int count = 10;
        for(int i = 0; i < monster.length; i++){
            if(hero.getX() == monster[i].getXcoord() && hero.getY() == monster[i].getYcoord() && monster[i].getHea() > 0){
                count = i;
            }
        }
        return count;
    }
    
    public static int checkFarmerMethod(Farmer[] farmer, Hero hero, Object[][] map){
        int count = 3;
        for(int i = 0; i < farmer.length; i++){
            if(hero.getX() == farmer[i].getX() && hero.getY() == farmer[i].getY()){
                count = i;
            }
        }
        return count;
    }
    
    public static boolean checkPotionMethod(Potion[] potion, Hero hero, Object[][] map){
        for(int i = 0; i < potion.length; i++){
            if(hero.getX() == potion[i].getX() && hero.getY() == potion[i].getY() && potion[i].potionReturn()){
                potion[i].foundP();
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkMonsterNearby(Monster[] monster, Hero hero){
        for(int i = 0; i < monster.length; i++){
            if(hero.getX() == monster[i].getXcoord()){
                if(hero.getY() == monster[i].getYcoord() + 1 || hero.getY() == monster[i].getYcoord() - 1){
                    return true;
                }
            }else if(hero.getY() == monster[i].getYcoord()){
                if(hero.getX() == monster[i].getXcoord() + 1 || hero.getX() == monster[i].getXcoord() - 1){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean checkBossMethod(EliteMonster boss, Hero hero, Object[][] map){
        if(hero.getX() == boss.getX() && hero.getY() == boss.getY()){
            return true;
        }
        return false;
    }
    
    public static boolean checkTraderMethod(Trader trader, Hero hero){
        if(hero.getX() == trader.getX() && hero.getY() == trader.getY()){
            return true;
        }
        return false;
    }
}