package StickGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class StickGame extends JavaPlugin implements Listener{
	
	public static Game game;
	
	public static Team red = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("RED");
	public static Team blue = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("BLUE");

	@Override
	public void onEnable() {	
		getServer().getPluginManager().registerEvents(this, this);
		
		red.setColor(ChatColor.RED);
		blue.setColor(ChatColor.BLUE);
	}
	
	@Override
	public void onDisable() {
		//TODO
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("sg")) {
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("start")) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "이 명령어를 사용할 권한이 없습니다.");
						return false;
					}
					game = new Game(this);
					game.start();
					return true;
				}else if(args[0].equalsIgnoreCase("stop")) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "이 명령어를 사용할 권한이 없습니다.");
						return false;
					}
					game.stop();
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> arguments = new ArrayList<String>();
		if(args.length == 1) {
			List<String> arguments1 = Arrays.asList("start", "stop");
			for(String arg : arguments1) {
				if(arg.toLowerCase().startsWith(args[0].toLowerCase())) {
					arguments.add(arg);
				}
			}
			return arguments;
		}
		return null;
	}
}
