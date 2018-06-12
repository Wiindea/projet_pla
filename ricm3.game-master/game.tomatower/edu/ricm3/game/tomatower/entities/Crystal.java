package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crystal extends Inert {

//	int idx;
	private int hp = 100;
	private Crystal main_instance;
	public final int MAX_LIFE = 1000;
//	private BufferedImage sprite;

	public Crystal(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind,
			Crystal c_main_instance) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kind, Kind.Danger);
		this.main_instance = c_main_instance;
		this.hp = MAX_LIFE;

	}

	public void damage(int power) {
		if (main_instance == null)
			this.hp -= power;
		else
			main_instance.damage(power);
		System.out.println(this.hp);
	}

	public void paint(Graphics g) {
		if (main_instance == null)
			super.paint(g);
	}

	public int getHp() {
		return this.hp;
	}

//	public void step(long now) {
//		super.step(now);
//		if (now - last_action > 250L) {
//			last_action = now;
//			idx = (idx + 1) % this.sprite.length;
//		}
//	}
}