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
import java.util.List;


public class WapenFabriek implements Listener, CommandExecutor {
    Main plugin;
    public WapenFabriek(Main instance) {
        this.plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        Inventory wapengui = Bukkit.createInventory(null, 27, "WapenMenu");
        Player p = (Player) sender;
        for (String wconfig : plugin.getConfig().getConfigurationSection("Items.").getKeys(false)) {
            ItemStack wapen = new ItemStack(Material.valueOf(String.valueOf(plugin.getConfig().getString( "Wapens." + wconfig + ".type"))));
            wapen = NBTEditor.set(wapen, plugin.getConfig().getString( "Wapens." + wconfig + ".NBTtag"), plugin.getConfig().getString( "Wapens." + wconfig + ".MTCustom"));
            ItemMeta wapenmeta = wapen.getItemMeta();
            wapenmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString( "Wapens." + wconfig + ".name")));
            wapenmeta.spigot().setUnbreakable(true);
            wapen.setDurability(Short.parseShort(plugin.getConfig().getString( "Wapens." + wconfig + ".DefaultDura")));
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Wapens." + wconfig + ".Lore")));
            wapenmeta.setLore(lore);
            wapen.setItemMeta(wapenmeta);

            wapengui.addItem(wapen);
        }
        p.openInventory(wapengui);


        return false;
    }
}