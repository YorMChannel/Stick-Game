package StickGame.task;

import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ItemCreateTask extends BukkitRunnable {
	
	World world = Bukkit.getWorld("world");

	@Override
	public void run() {
		Location loc = new Location(world, 84.5, 79, -122);
		double rand = Math.random() * 20;
		loc.add(new Vector(0, 0, rand));
		
		double rand2 = (Math.random() * 10) % 9;
		if(rand2 < 1) {
			Item item = world.dropItem(loc, new ItemStack(Material.GOLD_ORE));
			setDropItemData(item, ChatColor.RED, "Æú·Î´½ 210");
		}else if(rand2 < 2) {
			Item item = world.dropItem(loc, new ItemStack(Material.COAL));
			setDropItemData(item, ChatColor.RED, "´ÏÄÚÆ¾");
		}else if(rand2 < 3) {
			Item item = world.dropItem(loc, new ItemStack(Material.CHARCOAL));
			setDropItemData(item, ChatColor.RED, "Å¸¸£");
		}else if(rand2 < 4) {
			Item item = world.dropItem(loc, new ItemStack(Material.GLASS));
			setDropItemData(item, ChatColor.RED, "ÀÏ»êÈ­Åº¼Ò");
		}else if(rand2 < 5) {
			Item item = world.dropItem(loc, new ItemStack(Material.IRON_INGOT));
			setDropItemData(item, ChatColor.RED, "´ÏÄÌ");
		}else if(rand2 < 7) {
			Item item = world.dropItem(loc, new ItemStack(Material.APPLE));
			setDropItemData(item, ChatColor.GREEN, "»ç°ú");
		}else if(rand2 < 9) {
			Item item = world.dropItem(loc, new ItemStack(Material.BROWN_MUSHROOM));
			setDropItemData(item, ChatColor.GREEN, "¹ö¼¸");
		}
	}

	public final void setDropItemData(Item item, ChatColor color, String name) {
		item.setCustomName(color + name);
		item.setCustomNameVisible(true);
		if(color == ChatColor.RED){
			item.setGlowing(true);
		}
	}
}
