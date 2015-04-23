package me.Fabricio20.BungeeCord.Runnables;

import me.Fabricio20.BungeeCord.API.UpdateAPI;

public class UpdateChecker implements Runnable {

	@Override
	public void run() {
		UpdateAPI.silentCheck();
	}

}
