package me.patrick.MTWapensPremium;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KogelFabriek implements Listener, CommandExecutor {
    Main plugin;
    public KogelFabriek(Main instance) {
        this.plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        Inventory kogelgui = Bukkit.createInventory(null, 27, "KogelMenu");
        Player p = (Player) sender;
        for (String wconfig : plugin.getConfig().getConfigurationSection("Wapens.").getKeys(false)) {
            ItemStack kogel = new ItemStack(Material.valueOf(String.valueOf(plugin.getConfig().getString( "Wapens." + wconfig + ".kogeltype"))), 64);
            kogel = NBTEditor.set(kogel, plugin.getConfig().getString( "Wapens." + wconfig + ".kogelNBTtag"), plugin.getConfig().getString( "Wapens." + wconfig + ".kogelcustom"));
            ItemMeta kogelmeta = kogel.getItemMeta();
            kogelmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString( "Wapens." + wconfig + ".kogelname")));
            kogel.setItemMeta(kogelmeta);


            kogelgui.addItem(kogel);
        }
        p.openInventory(kogelgui);


        return false;
    }
}