package com.coconut.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	Handler handler;
	public int counter = 20;
	public float health;
	
	public Player(float x, float y, mobId id, int direction, float health ,Handler handler) {
		super(x, y, id, direction);
		this.handler = handler;
		this.health = health;
		setHealth(health);
	}

	
	public void tick() {

		x += velX;
        y += velY;
        x = Game.clamp((int)x, 0, Game.WIDTH-36);
        y = Game.clamp((int)y, 0, Game.HEIGHT-36); 
        if(attack==true && counter == 0){
        	handler.addObject(new Bullet(x+16,y+16, mobId.Bullet, direction, handler,this));
        	counter = 20;
        	
        }
        else if(attack == true && counter > 0){
        	
        	counter--;
        }
        else if(attack == false)
        {
        	if(counter > 0)
        		counter--;
        	else
        		counter =0;
        }
        	
		collision();
	}

	
	public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
		
	}
	
	public void collision(){
        for(int i = 0; i < handler.object.size(); ++i)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == mobId.BasicEnemy ){
                if(getBounds().intersects(tempObject.getBounds()))
                    if(getHealth() > 0)
                    	setHealth(getHealth()-3);
                
            } 
        }
	}


	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32,32);
	}

	
}
