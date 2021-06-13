package me.blume.costumpotions;

import org.bukkit.plugin.java.JavaPlugin;

import me.blume.costumpotions.items.CreativePotion;
import me.blume.costumpotions.items.EveryPotion;
import me.blume.costumpotions.items.EveryPotion2;
import me.blume.costumpotions.items.FlyPotion;
import me.blume.costumpotions.items.HastePotion;
import me.blume.costumpotions.items.LuckPotion;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new FlyPotion(this), this);
		getServer().getPluginManager().registerEvents(new CreativePotion(this), this);
		getServer().getPluginManager().registerEvents(new LuckPotion(this), this);
		getServer().getPluginManager().registerEvents(new HastePotion(this), this);
		getServer().getPluginManager().registerEvents(new EveryPotion(this), this);
		getServer().getPluginManager().registerEvents(new EveryPotion2(this), this);
	}
	@Override
	public void onDisable() {
	
	}

}
