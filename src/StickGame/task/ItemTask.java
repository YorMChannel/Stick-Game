package StickGame.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import StickGame.Game;

public class ItemTask extends BukkitRunnable{
	
	Game game;
	
	public ItemTask(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		for(Entity item : Bukkit.getWorld("world").getEntities()) {
			if(item instanceof Item) {
				if(item.getLocation().getY() <= 51.5) {
					if(((Item) item).getItemStack().getType() == Material.APPLE) {
						game.point += 4;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "사과 [ +4점 ]");
						addGoodEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.BROWN_MUSHROOM) {
						game.point += 6;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "버섯 [ +6점 ]");
						addGoodEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.GOLD_ORE) {
						game.point -= 4;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "폴로늄 210 [ -4점 ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "폴로늄을 흡입하면 일부는 폐에 남고, 거의 절반이 비장, 신장 및 간에 모이게 된다. 골수로 소량이 모이고, 나머지는 신체, 주로 혈액, 림프절 및 호흡기 점막의 안쪽에 분포한다.\n폴로늄-210에서 방출 된 입자는 세포구조물들을 파괴하고, 핵을 조각내며, DNA를 손상시키고, 세포를 죽일 수 있다.");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.COAL) {
						game.point -= 5;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "니코틴 [ -5점 ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "적은 양의 니코틴은 신경계에 작용하여 교감 및 부교감 신경을 흥분시켜 일시적으로 쾌감을 얻게 하지만 많은 양의 니코틴은 신경을 마비 시켜 환각 상태에까지 이르게한다.\n(대마초보다 중독성이 강함)");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.CHARCOAL) {
						game.point -= 3;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "타르 [ -3점 ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "타르 속에는 2천여 종의 독성 화학 물질이 들어 있고, 그 중에는 약 20종류의 발암물질까지 포함되어 있다.");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.GLASS) {
						game.point -= 2;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "일산화탄소 [ -2점 ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "일산화탄소는 혈색소에 결합하는 능력이 산소보다 뛰어나서 담배를 계속 피우게 되면 결국 혈액의 산소운반 능력이 떨어져 산소부족을 일으켜 만성 저산소증 현상을 일으킴으로서\n모든 신체세포의 신진대사에 장애가 생기고 노화현상을 일으킨다.");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.IRON_INGOT) {
						game.point -= 3;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "니켈 [ -3점 ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "중금속, 배터리, 도료로 사용되는 물질로 흡연 시 발생되는 미세한 먼지를 통해 노출되며 장기간 노출 시 호흡기계 독성 및 암을 일으킬 수 있는 물질이다.");
						addBadEffect(item.getLocation());
						item.remove();
					}
				}
			}
		}
	}
	
	public final void addGoodEffect(Location loc) {
		World world = Bukkit.getWorld("world");
		world.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 1f);
		world.spawnParticle(Particle.HEART, loc, 1);
	}
	
	public final void addBadEffect(Location loc) {
		World world = Bukkit.getWorld("world");
		world.playSound(loc, Sound.ENTITY_WITHER_DEATH, 10.0f, 1f);
		world.spawnParticle(Particle.BARRIER, loc, 1);
	}
	
}
