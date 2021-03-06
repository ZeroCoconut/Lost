package com.coconut.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	Handler handler;
	public int counter = 20;
	public float health;
	private int score = 0;
	
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
        	if(getScore() > 1000){
        		handler.addObject(new Bullet(x+1,y+8, mobId.Bullet, 2, handler,this));
        	}
        	if(getScore() >2000)
        	{
        		handler.addObject(new Bullet(x+15,y+8, mobId.Bullet, 3, handler,this));
        	}
        	handler.addObject(new Bullet(x+8,y+8, mobId.Bullet, 1, handler,this));
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
        if(getHealth() <= 0){
        	Game.gameOver = true;
        	handler.removeObject(this);
        }
		collision();
	}

	
	public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	public void collision(){
		int tempSize = handler.object.size();
        for(int i = 0; i < tempSize; ++i)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == mobId.BasicEnemy || tempObject.getId() == mobId.EnemyBullet ){
                if(getBounds().intersects(tempObject.getBounds())){
                    if(getHealth() > 0)
                    	setHealth(getHealth()-1);
                 
                    	handler.removeObject(tempObject);
                    	return;
              
                }
            }
            if(tempObject.getId() == mobId.Boss){
            	if(getBounds().intersects(tempObject.getBounds()))
                    if(getHealth() > 0)
                    	setHealth(getHealth()-3);
            }
        }
	}


	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16,16);
	}
	

	
}
