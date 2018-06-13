/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.tomatower.mvc;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;
import edu.ricm3.game.tomatower.entities.Player;
import edu.ricm3.game.tomatower.entities.Tower;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Hud;
import edu.ricm3.game.tomatower.map.Map;

public class View extends GameView {

	private static final long serialVersionUID = 1L;
	BufferedImage background;
	long last;
	int npaints;
	int fps;
	Model model;
	Controller ctr;
	GameUI gameUI;
	Hud hud;

	public View(Model m, Controller c) {
		model = m;
		ctr = c;
		hud = new Hud(m);
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - last > 1000L) {
			fps = npaints;
			last = now;
			npaints = 0;
		}
		m_game.setFPS(fps, "npaints=" + npaints);
		npaints++;
	}

	@Override
	protected void _paint(Graphics g) {

		computeFPS();

		// erase background
		// g.setColor(m_background);
		// g.fillRect(0, 0, getWidth(), getHeight());
		Map map = this.model.getCurrentMap();
		Iterator<Cell> iter_cells = map.getCellsIterator();
		Cell c;
		while (iter_cells.hasNext()) {
			c = iter_cells.next();
			int d = map.getCellSize();
			int[] pos = c.getPosition();
			int x = pos[0] * d;
			int y = pos[1] * d;
			if (map == model.getMainMap()) {
				g.drawImage(model.getSprites().sprite_grass, x, y, d, d, null);
			} else {
				g.drawImage(model.getSprites().sprite_plank, x, y, d, d, null);
			}
		}

		map.paint(g);

		hud.paint(g);
	}

	public void setGameUI(GameUI gameUI) {
		this.gameUI = gameUI;
	}
}