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
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "��� [ +4�� ]");
						addGoodEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.BROWN_MUSHROOM) {
						game.point += 6;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "���� [ +6�� ]");
						addGoodEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.GOLD_ORE) {
						game.point -= 4;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "���δ� 210 [ -4�� ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "���δ��� �����ϸ� �Ϻδ� �� ����, ���� ������ ����, ���� �� ���� ���̰� �ȴ�. ����� �ҷ��� ���̰�, �������� ��ü, �ַ� ����, ������ �� ȣ��� ������ ���ʿ� �����Ѵ�.\n���δ�-210���� ���� �� ���ڴ� �������������� �ı��ϰ�, ���� ��������, DNA�� �ջ��Ű��, ������ ���� �� �ִ�.");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.COAL) {
						game.point -= 5;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "����ƾ [ -5�� ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "���� ���� ����ƾ�� �Ű�迡 �ۿ��Ͽ� ���� �� �α��� �Ű��� ��н��� �Ͻ������� �谨�� ��� ������ ���� ���� ����ƾ�� �Ű��� ���� ���� ȯ�� ���¿����� �̸����Ѵ�.\n(�븶�ʺ��� �ߵ����� ����)");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.CHARCOAL) {
						game.point -= 3;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "Ÿ�� [ -3�� ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "Ÿ�� �ӿ��� 2õ�� ���� ���� ȭ�� ������ ��� �ְ�, �� �߿��� �� 20������ �߾Ϲ������� ���ԵǾ� �ִ�.");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.GLASS) {
						game.point -= 2;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "�ϻ�ȭź�� [ -2�� ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "�ϻ�ȭź�Ҵ� �����ҿ� �����ϴ� �ɷ��� ��Һ��� �پ�� ��踦 ��� �ǿ�� �Ǹ� �ᱹ ������ ��ҿ�� �ɷ��� ������ ��Һ����� ������ ���� ������� ������ ����Ŵ���μ�\n��� ��ü������ ������翡 ��ְ� ����� ��ȭ������ ����Ų��.");
						addBadEffect(item.getLocation());
						item.remove();
					}else if(((Item) item).getItemStack().getType() == Material.IRON_INGOT) {
						game.point -= 3;
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "���� [ -3�� ]");
						Bukkit.broadcastMessage(ChatColor.BOLD + "" + "�߱ݼ�, ���͸�, ����� ���Ǵ� ������ �� �� �߻��Ǵ� �̼��� ������ ���� ����Ǹ� ��Ⱓ ���� �� ȣ���� ���� �� ���� ����ų �� �ִ� �����̴�.");
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
