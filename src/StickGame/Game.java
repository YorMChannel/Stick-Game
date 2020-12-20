package StickGame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import StickGame.task.BossBarTask;
import StickGame.task.CountTask;
import StickGame.task.ItemCreateTask;
import StickGame.task.ItemTask;

public class Game {
	
	StickGame plugin;
	
	public BossBar bar;
	public double point = 30;
	
	World world = Bukkit.getWorld("world");
	Location loc = new Location(world, 74.5, 70, -111.5);
	
	public Game(StickGame plugin) {
		this.plugin = plugin;
	}
	
	public final void start() {
		bar = Bukkit.createBossBar(ChatColor.LIGHT_PURPLE + "금연", BarColor.PURPLE, BarStyle.SOLID);
		Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
		Bukkit.getOnlinePlayers().toArray(onlinePlayers);
		for (int i = 0; i < onlinePlayers.length; i++) {
			bar.addPlayer(onlinePlayers[i]);
			UserList.users.add(new StickUser(onlinePlayers[i]));
		}
		bar.setProgress(point / 100);
		bar.setVisible(true);
		
		BukkitRunnable countTask = new CountTask();
		countTask.runTaskTimerAsynchronously(plugin, 0L, 20L);
		BukkitRunnable itemTask = new ItemTask(this);
		itemTask.runTaskTimerAsynchronously(plugin, 0L, 1L);
		BukkitRunnable bossBarTask = new BossBarTask(this);
		bossBarTask.runTaskTimerAsynchronously(plugin, 0L, 1L);
		BukkitRunnable itemCreateTask = new ItemCreateTask();
		itemCreateTask.runTaskTimer(plugin, 60L, 100L);
	}
	
	public final void stop() {
		bar.removeAll();
		Bukkit.getServer().getScheduler().cancelTasks(StickGame.getPlugin(StickGame.class));
		UserList.users.clear();
		for(Entity entity : Bukkit.getWorld("world").getEntities()) {
			if(entity instanceof FallingBlock || entity instanceof Item) {
				entity.remove();
			}
		}
		Bukkit.getServer().getScheduler().cancelTasks(StickGame.getPlugin(StickGame.class));
	}
	
	public final void win() {
		Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
		Bukkit.getOnlinePlayers().toArray(onlinePlayers);
		world.playSound(loc, Sound.BLOCK_ANVIL_USE, 10.0f, 1.0f);
		for (int i = 0; i < onlinePlayers.length; i++) {
			onlinePlayers[i].sendTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "성공!", "금연에 성공하셨습니다", 20, 40, 20);
		}
		stop();
	}
	
	public final void lose() {
		Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
		world.playSound(loc, Sound.ENTITY_WITHER_DEATH, 10.0f, 1.0f);
		Bukkit.getOnlinePlayers().toArray(onlinePlayers);
		for (int i = 0; i < onlinePlayers.length; i++) {
			onlinePlayers[i].sendTitle(ChatColor.BOLD + "" + ChatColor.RED + "실패!", "다시 도전해보세요", 20, 40, 20);
		}
		stop();
	}
}
