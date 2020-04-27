package me.patrick.MTWapensPremium;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nonamesldev.itembuilder.ItemBuilder;

public class WapenFabriek implements Listener, CommandExecutor {
    Main plugin;

    public WapenFabriek(Main instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        Inventory wapengui = Bukkit.createInventory(null, 27, "WapenMenu");
        Player p = (Player) sender;
        for (String wconfig : plugin.getConfig().getConfigurationSection("Wapens").getKeys(false)) {

            FileConfiguration config = plugin.getConfig();

            ItemStack weapon = new ItemBuilder(Material.valueOf(config.getString("Wapens." + wconfig + ".type")))
                    .setColoredName(config.getString("Wapens." + wconfig + ".name"))
                    .setNBT(config.getString("Wapens." + wconfig + ".MTCustom"),
                            config.getString("Wapens." + wconfig + ".NBTtag"))
                    .makeUnbreakable(true)
                    .setDurability((short) config.getInt(config.getString("Wapens." + wconfig + ".DefaultDura")))
                    .addLoreLine(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("Wapens." + wconfig + ".Lore")))
                    .toItemStack();

            wapengui.addItem(weapon);
        }
        p.openInventory(wapengui);

        return false;
    }
}