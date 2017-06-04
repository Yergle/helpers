package me.fudged.helpers.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.fudged.helpers.Main;
import me.fudged.helpers.Sounds;

public class Helpers implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("helpers")){
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("lookup") && sender.hasPermission("helpers.lookup")){
					if(Bukkit.getServer().getPlayer(args[1]) != null){
						lookupHelpers(sender, Bukkit.getServer().getPlayer(args[1]));
						return true;
					}
				}
				
				if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("helpers.reload")){
					if(Bukkit.getServer().getPlayer(args[1]) != null){
						Player t = Bukkit.getServer().getPlayer(args[1]);
						Main.getUserManager().reloadUser(t);
						sender.sendMessage(ChatColor.YELLOW + "Reloaded all helpers of " + ChatColor.GRAY + t.getName());
						sender.sendMessage(ChatColor.YELLOW + "Use " + ChatColor.GRAY + "/helpers lookup " + t.getName() + ChatColor.YELLOW + " to see the changes" );
						return true;
					}else{
						sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.GRAY + " is not online");
						return true;
					}
				}
				
				if(args[0].equalsIgnoreCase("admin") && sender.hasPermission("helpers.admin")){
					if(args.length == 2){
						if(!(sender instanceof Player)){
							sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
							return true;
						}
						Player p = (Player) sender;
						if(args[1].equalsIgnoreCase("miners")){
							p.openInventory(Main.getItemsEditor().editorInv(p.getName(), "miners"));
							return true;
						}
						if(args[1].equalsIgnoreCase("farmers")){
							p.openInventory(Main.getItemsEditor().editorInv(p.getName(), "farmers"));
							return true;
						}
						if(args[1].equalsIgnoreCase("butchers")){
							p.openInventory(Main.getItemsEditor().editorInv(p.getName(), "butchers"));
							return true;
						}
						if(args[1].equalsIgnoreCase("lumberjacks")){
							p.openInventory(Main.getItemsEditor().editorInv(p.getName(), "lumberjacks"));
							return true;
						}
						if(args[1].equalsIgnoreCase("excavators")){
							p.openInventory(Main.getItemsEditor().editorInv(p.getName(), "excavators"));
							return true;
						}
						
						p.sendMessage(ChatColor.YELLOW + "Unknown command. Use " + ChatColor.GRAY + "/helpers help" + ChatColor.YELLOW + " for help");
						return true;
						
					}else{
						sender.sendMessage(ChatColor.YELLOW + "Error: " + ChatColor.GRAY + "/helpers admin <miners/farmers/butchers/lumberjacks/excavators>");
						return true;
					}
				}
				
			}
			
			if(args.length == 0 && sender.hasPermission("helpers.use")){
				if(!(sender instanceof Player)){
					sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
					return true;
				}
				
				Player p = (Player) sender;
				p.playSound(p.getLocation(), Sounds.CHICKEN_EGG_POP.bukkitSound(), 1F, 1F);
				p.openInventory(Main.getUserManager().getUser(p).getInventory());
				return true;
			}
			
			if(args.length == 1 && args[0].equalsIgnoreCase("help")){
				sendHelpMsg(sender);
				return true;
			}
			
			sendHelpMsg(sender);
			
		}
		
		return true;
	}
	
	public void lookupHelpers(CommandSender p, Player t){
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------");
		p.sendMessage(ChatColor.GOLD + "Showing all helpers of " + ChatColor.GRAY + t.getName());
		p.sendMessage(ChatColor.YELLOW + "Miners: " + ChatColor.GRAY + Main.getUserManager().getUser(t).getMiners());
		p.sendMessage(ChatColor.YELLOW + "Farmers: " + ChatColor.GRAY + Main.getUserManager().getUser(t).getFarmers());
		p.sendMessage(ChatColor.YELLOW + "Butchers: " + ChatColor.GRAY + Main.getUserManager().getUser(t).getButchers());
		p.sendMessage(ChatColor.YELLOW + "Lumberjacks: " + ChatColor.GRAY + Main.getUserManager().getUser(t).getLumberjacks());
		p.sendMessage(ChatColor.YELLOW + "Excavator: " + ChatColor.GRAY + Main.getUserManager().getUser(t).getExcavators());
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------");
	}
	
	public void sendHelpMsg(CommandSender p){
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------");
		p.sendMessage(ChatColor.YELLOW + "/helpers lookup <player> " + ChatColor.GRAY + "Look up info about all helpers a player has");
		p.sendMessage(ChatColor.YELLOW + "/helpers reload <player> " + ChatColor.GRAY + "Reload and update the helpers a player has");
		p.sendMessage(ChatColor.YELLOW + "/helpers admin <miners/farmers/butchers/lumberjacks/excavators> " + ChatColor.GRAY + "Open up a GUI to edit the items");
		p.sendMessage(ChatColor.YELLOW + "/helpers " + ChatColor.GRAY + "Open up your personal helpers gui");
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------");
	}
	
	
}
