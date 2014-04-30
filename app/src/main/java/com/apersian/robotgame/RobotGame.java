package com.apersian.robotgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;
import com.apersian.robotgame.R;
import com.apersian.framework.Screen;
import com.apersian.framework.implementation.AndroidGame;

public class RobotGame extends AndroidGame {

	public static String map;
	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}

		InputStream is = getResources().openRawResource(R.raw.map1);
		map = convertStreamToString(is);

		return new SplashLoadingScreen(this);
	}

    //handles the actions designated to happen when the user presses the back key

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

    // resumes the program after the user has re-opened it

	@Override
	public void onResume() {
		super.onResume();
		Assets.theme.play();
	}

    // handles the actions needed for when the program is minimized

	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();
	}

}
