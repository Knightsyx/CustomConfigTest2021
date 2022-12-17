package me.knightsy.newmcbuild;

import me.knightsy.newmcbuild.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class Main extends JavaPlugin implements Listener {

    public CustomConfig customConfig;

    @Override
    public void onEnable() {
       this.customConfig = new CustomConfig(this);

       //this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("Red")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Console can not use this command.");
                return true;
            }
            Player player = (Player) sender;
            if(!(this.customConfig.getConfig().contains("players." + player.getUniqueId().toString() + ".name"))){
                addData(player);
            }
            this.customConfig.getConfig().set("players." + player.getUniqueId().toString() + ".class", "Red");
            this.customConfig.saveConfig();

            return true;
        }

        if(label.equalsIgnoreCase("Blue")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Console can not use this command.");
                return true;
            }
            Player player = (Player) sender;

            if(!(this.customConfig.getConfig().contains("players." + player.getUniqueId().toString() + ".name"))){
                addData(player);
            }

            this.customConfig.getConfig().set("players." + player.getUniqueId().toString() + ".class", "Blue");
            this.customConfig.saveConfig();

            return true;
        }

        if(label.equalsIgnoreCase("Team")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("Console can not use this command.");
                return true;
            }
            Player player = (Player) sender;
            if(this.customConfig.getConfig().contains("players." + player.getUniqueId().toString() + ".class")){
                player.sendMessage(Objects.requireNonNull(this.customConfig.getConfig().getString("players." + player.getUniqueId().toString() + ".class")));
                return true;
            }
            player.sendMessage("No class.");
        }

        return false;
    }
    public void addData(Player player){
        this.customConfig.getConfig().set("players." + player.getUniqueId().toString() + ".name", player.getName());
    }

    /*@EventHandler
    public void onBreakEvent(BlockBreakEvent event){
        if(event.getBlock().getType().equals(Material.DIAMOND_ORE)){
            Player player = event.getPlayer();
            int amount = 0;

            if(this.customConfig.getConfig().contains("players." + player.getUniqueId().toString() + ".total")) {
                amount = this.customConfig.getConfig().getInt("players." + player.getUniqueId().toString() + ".total");
            }
            this.customConfig.getConfig().set("players." + player.getUniqueId().toString() + ".name", player.getName());
            this.customConfig.getConfig().set("players." + player.getUniqueId().toString() + ".total", (amount + 1));
            this.customConfig.saveConfig();
        }
    }*/

}
