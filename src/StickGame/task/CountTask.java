package StickGame.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountTask extends BukkitRunnable {
	
	int sec = 4;
	
	World world = Bukkit.getWorld("world");
	Location loc = new Location(world, 74.5, 51, -111.5);

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		sec--;
		Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
		Bukkit.getOnlinePlayers().toArray(onlinePlayers);
		for (int i = 0; i < onlinePlayers.length; i++) {
			onlinePlayers[i].sendTitle(ChatColor.RED + "" + sec, ChatColor.BOLD + "금연에 성공하세요!", 0, 20, 0);
			Bukkit.getWorld("world").playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
		}
		
		if(sec < 1) {
			Bukkit.getOnlinePlayers().toArray(onlinePlayers);
			for (int i = 0; i < onlinePlayers.length; i++) {
				onlinePlayers[i].sendTitle(ChatColor.RED + "시작!", ChatColor.BOLD + "금연에 성공하세요!", 0, 20, 0);
				Bukkit.getWorld("world").playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.6f);
			}
			this.cancel();
		}
	}

}
