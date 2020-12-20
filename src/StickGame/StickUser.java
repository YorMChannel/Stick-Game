package StickGame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import StickGame.task.BarTask;

public class StickUser {
	protected FallingBlock[] bar;
	protected final World world = Bukkit.getWorld("world");
	protected Location loc = new Location(world, 84, 52.5, -113.5);
	
	protected Player player;
	
	public StickUser(Player player) {
		this.player = player;
		bar = makeBar();
		BukkitRunnable task = new BarTask(player, bar);
		task.runTaskTimerAsynchronously(StickGame.getPlugin(StickGame.class), 0L, 0L);
	}
	
	public final FallingBlock[] makeBar() {
		FallingBlock[] blocks = new FallingBlock[3];
		for (int i = 0; i < 3; i++) {
			FallingBlock block = Bukkit.getWorld("world").spawnFallingBlock(loc, Material.DIAMOND_BLOCK, (byte) 1);
			block.setGravity(false);
			blocks[i] = block;
			loc.add(0, 0, 1);
		}
		
		return blocks;
	}
}
