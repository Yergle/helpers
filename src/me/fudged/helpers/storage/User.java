package me.fudged.helpers.storage;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class User {
	
	private Player p;
	private Integer miner, farmer, butcher, lumberjack, excavator;
	private Inventory inv;
	
	public User(Player p, Integer miner, Integer farmer, Integer butcher, Integer lumberjack, Integer excavator, Inventory inv){
		this.p = p;
		this.miner = miner;
		this.farmer = farmer;
		this.butcher = butcher;
		this.lumberjack = lumberjack;
		this.excavator = excavator;
		this.inv = inv;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public Integer getMiners(){
		return miner;
	}
	
	public Integer getFarmers(){
		return farmer;
	}
	
	public Integer getButchers(){
		return butcher;
	}
	
	public Integer getLumberjacks(){
		return lumberjack;
	}
	
	public Integer getExcavators(){
		return excavator;
	}
	
	public Inventory getInventory(){
		return inv;
	}

}
