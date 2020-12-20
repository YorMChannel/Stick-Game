package StickGame.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BarTask extends BukkitRunnable {
	
	Player player;
	
	FallingBlock[] bar = new FallingBlock[3];
	
	public BarTask(Player player, FallingBlock[] bar) {
		this.player = player;
		for (int i = 0; i < 3; i++) {
			this.bar[i] = bar[i];
		}
	}
	
	World world = Bukkit.getWorld("world");
	Location loc = new Location(world, 74.5, 51, -111.5);
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			if(bar[i].getTicksLived() > 590) {
				bar[i].setTicksLived(1);
			}
		}
		
		if(bar[1].getLocation().distance(getBarLocation()) > 0.1) {
			for (int i = -1; i < 2; i++) {
				double z = bar[i+1].getLocation().getZ() - (getBarLocation().getZ() + i);
				Vector vec = new Vector(0, 0, z).multiply(Math.abs(z) * -1);
				vec.setZ(vec.getZ() < 4 ? vec.getZ() : 4);
				vec.setZ(vec.getZ() > -4 ? vec.getZ() : -4);
				bar[i+1].setVelocity(vec);
			}
		}
		
		for(Entity item : Bukkit.getWorld("world").getEntities()) {
			if(item instanceof Item || item instanceof ItemStack) {
				double minZ = bar[1].getLocation().getZ()-2;
				double maxZ = bar[1].getLocation().getZ()+2;
				double minY = 50.7;
				double maxY = 52.5;
				//Bukkit.getWorld("world").spawnParticle(Particle.HEART, new Location(Bukkit.getWorld("world"), bar[1].getLocation().getX(), bar[1].getLocation().getY()+0.5, minZ), 1);
				//Bukkit.getWorld("world").spawnParticle(Particle.HEART, new Location(Bukkit.getWorld("world"), bar[1].getLocation().getX(), bar[1].getLocation().getY()+0.5, maxZ), 1);
				if(item.getLocation().getZ() >= minZ && item.getLocation().getZ() <= maxZ && item.getLocation().getY() >= minY && item.getLocation().getY() <= maxY) {
					double motionY = Math.abs(getRandom() * 3);
					motionY = motionY >= 1 ? motionY : 1;
					item.setVelocity(new Vector(0, motionY, getRandom()));
				}
			}
		}
	}
	
	protected final Location getBarLocation() {
		Block block = player.getTargetBlock(null, 50);
		Location loc_block = block.getLocation();
		double z = loc_block.getZ() <= -103.5 ? loc_block.getZ() : -103.5;
		z = z >= -121.5 ? z : -121.5;
		Location loc = new Location(Bukkit.getWorld("world"), 84, 51, z);
		return loc;
	}
	
	protected final double getRandom() {
		double rand = Math.random() + 0.1;
		rand *= (Math.random() + 0.1) * 1.2;
		
		double rand2 = Math.random();
		if((rand2 * 10) % 2 < 1) {
			rand *= -1;
			//Bukkit.broadcastMessage(rand2 + "");
		}
		return rand;
	}

}
