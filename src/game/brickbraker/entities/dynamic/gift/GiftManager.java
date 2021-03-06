package game.brickbraker.entities.dynamic.gift;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GiftManager {
    private ArrayList<Gift> gifts;

    public GiftManager(){
        gifts = new ArrayList<>();
    }

    public void addGift(Gift gift){
        gifts.add(gift);
    }

    public void tick(){
        Iterator<Gift> iterator = gifts.iterator();
        while(iterator.hasNext()){
            Gift gift = iterator.next();
            gift.tick();
            if(!gift.isActive()){
                iterator.remove();
            }
        }
    }

    public void render(Graphics g){
        for(Gift gift : gifts){
            gift.render(g);
        }
    }

    public boolean hasActiveGift(){
        for(Gift gift : gifts){
            if(gift.isActive())
                return true;
        }
        return false;
    }
}
