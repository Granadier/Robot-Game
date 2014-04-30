package com.apersian.robotgame;

import java.util.List;
import com.apersian.framework.Game;
import com.apersian.framework.Graphics;
import com.apersian.framework.Screen;
import com.apersian.framework.Input.TouchEvent;

// the first line extends the screen variable and then begins the game process

public class MainMenuScreen extends Screen {
	public MainMenuScreen(Game game) {
		super(game);
	}


    // this block of code handles the events associated with the user touching the screen.

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 50, 350, 250, 450)) {
					game.setScreen(new GameScreen(game));
				}
			}
		}
	}

    // if the touch input is in the bounds of the designated area then it will be registered.
    // this can be modified to fit the programmers needs.

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
