package StickGame.task;

import org.bukkit.scheduler.BukkitRunnable;

import StickGame.Game;

public class BossBarTask extends BukkitRunnable {
	
	Game game;

	public BossBarTask(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		double percent = game.point / 100;
		if(percent >= 1) {
			game.win();
		}else if (percent <= 0) {
			game.lose();
		}
		percent = percent <= 1 ? percent : 1;
		game.bar.setProgress(percent);
	}

}
