package com.Game.game;

import android.app.Application;

public class Variables extends Application{
public static int mode;

public static int getMode() {
	return mode;
}

public static void setMode(int mode) {
	Variables.mode = mode;
}


}
