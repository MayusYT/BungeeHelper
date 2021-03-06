package bansystem;

import api.BanAPI;
import main.BungeeHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BanCMD extends Command {

    public BanCMD() {
        super("ban");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {


        if (sender.hasPermission("main.BungeeHelper.Ban")) {
            switch (args.length) {
                case 0:
                    sender.sendMessage(BungeeHelper.prefix + "--------- Ban Gründe ---------");
                    sender.sendMessage("§4  1 §b§ §cHacking");
                    sender.sendMessage("§4  2 §b§ §cBeleidigung");
                    sender.sendMessage("§4  3 §b§ §cWerbung");
                    sender.sendMessage("§4  4 §b§ §cTrolling");
                    sender.sendMessage("§4  5 §b§ §cBugusing");
                    sender.sendMessage("§4  6 §b§ §cSuperKick");
                    sender.sendMessage("§4  7 §b§ §cVerhalten");
                    sender.sendMessage("§4  8 §b§ §cDrohung");
                    sender.sendMessage("§4  9 §b§ §cTeaming");
                    sender.sendMessage("§4 10 §b§ §cSpawntrapping");
                    sender.sendMessage("§4 11 §b§ §cSkin");
                    sender.sendMessage("§4 12 §b§ §cUsername");
                    sender.sendMessage("§4 13 §b§ §cSpamming");
                    sender.sendMessage("§4 14 §b§ §cExtrem");
                    sender.sendMessage("Ban checken mit: /ban #Nummer");
                    break;
                case 1:
                    if (args[0].startsWith("#")) {
                        UUID uuid = BanAPI.getUuidFromId(Integer.parseInt(args[0].substring(1)));
                        if (BanAPI.isBanned(uuid)) {
                            sender.sendMessage(BungeeHelper.prefix + "--------- Ban Info ---------");
                            sender.sendMessage(BungeeHelper.prefix + "Spieler:  §6" + BanAPI.getLastName(uuid));
                            sender.sendMessage(BungeeHelper.prefix + "Grund:  §6" + BanAPI.getReason(uuid));
                            sender.sendMessage(BungeeHelper.prefix + "Gebannt von:  §6" + BanAPI.getLastExecName(uuid));
                            if (BanAPI.getBannedUntil(uuid) == -1) {
                                sender.sendMessage(BungeeHelper.prefix + "Gebannt bis:  §cPERMANENT");
                            } else {
                                sender.sendMessage(BungeeHelper.prefix + "Gebannt bis:  §6" + BanAPI.getRemainingBanTime(uuid));
                            }
                            TextComponent message1 = new TextComponent(BungeeHelper.prefix + "[§aEntbannen§r]");
                            message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unbanUID " + uuid));
                            sender.sendMessage(message1);
                        } else {
                            sender.sendMessage(BungeeHelper.prefix + ChatColor.RED + "Dieser Spieler ist nicht gebannt!");

                        }
                    } else {
                        sender.sendMessage(BungeeHelper.prefix + "§cFalsche Argumente. Hier ein paar Beispiele:");
                        sender.sendMessage(BungeeHelper.prefix + "§c/ban | Hilfe & Gründe anzeigen");
                        sender.sendMessage(BungeeHelper.prefix + "§c/ban #12345 | Infos zum Ban der Nummer 12345 anzeigen");
                        sender.sendMessage(BungeeHelper.prefix + "§c/ban FrecherBruder 14 | Den Spieler 'FrecherBruder' bannen mit Grund 'Extrem'");
                    }
                    break;
                case 2:

                    if(ProxyServer.getInstance().getPlayer(args[0]) != null) {
                        Integer banID = Integer.parseInt(args[1]);
                        UUID playerToBan = ProxyServer.getInstance().getPlayer(args[0]).getUniqueId();

                        List<UUID> ownerList = new ArrayList<>();
                        ownerList.add(UUID.fromString("6da21308-6b8f-43a9-98d1-2831ab778511"));
                        ownerList.add(UUID.fromString("900bf5ae-3f2f-4594-8250-1871d6aec064"));

                        switch (banID) {
                            case 1:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Hacking", TimeUnit.DAYS.toMillis(30));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cHACKING §agebannt!");
                                break;
                            case 2:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Beleidigung", TimeUnit.DAYS.toMillis(15));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cBELEIDIGUNG §agebannt!");
                                break;
                            case 3:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Werbung", TimeUnit.DAYS.toMillis(10));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cWERBUNG §agebannt!");
                                break;
                            case 4:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Trolling", TimeUnit.DAYS.toMillis(8));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cTROLLING §agebannt!");
                                break;
                            case 5:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Bugusing", TimeUnit.DAYS.toMillis(5));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cBUGUSING §agebannt!");
                                break;
                            case 6:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "SupKick", TimeUnit.DAYS.toMillis(2));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cSUPERKICK §agebannt!");
                                break;
                            case 7:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Verhalten", TimeUnit.DAYS.toMillis(5));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cVERHALTEN §agebannt!");
                                break;
                            case 8:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Drohung", TimeUnit.DAYS.toMillis(10));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cDROHUNG §agebannt!");
                                break;
                            case 9:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Teaming", TimeUnit.DAYS.toMillis(10));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cTEAMING §agebannt!");
                                break;
                            case 10:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Spawntrapping", TimeUnit.DAYS.toMillis(8));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cSPAWNTRAPPING §agebannt!");
                                break;
                            case 11:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Skin", TimeUnit.DAYS.toMillis(5));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cSKIN §agebannt!");
                                break;
                            case 12:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Username", TimeUnit.DAYS.toMillis(5));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cUSERNAME §agebannt!");
                                break;
                            case 13:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Spamming", TimeUnit.DAYS.toMillis(5));
                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }
                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cSPAM §agebannt!");
                                break;
                            case 14:
                                BanAPI.banPlayer(playerToBan, ((ProxiedPlayer) sender).getUniqueId(), "Extrem");

                                try{
                                    ProxyServer.getInstance().getPlayer(playerToBan).disconnect(new TextComponent("\n§c§lSnapecraft \n§7 \n§8------------------------------ \n§7Du wurdest vom §bServernetzwerk §7gebannt. \n §7Grund: §c" + BanAPI.getReason(playerToBan) + "  §7#" + BanAPI.getBanID(playerToBan) + " \n \n §7Verbleibende Zeit: §c  \n" + BanAPI.getRemainingBanTime(playerToBan) + "\n \n §7Stelle einen §cEntbannungsantrag§7 im Forum: \n §eforum.snapecraft.net \n§8------------------------------"));
                                } catch(Exception e) {
                                }

                                sender.sendMessage(BungeeHelper.prefix + "§aSpieler wegen §cEXTREM §agebannt!");
                                break;
                            default:
                                sender.sendMessage(BungeeHelper.prefix + "§cUngültige ReasonID. Nutze /ban um die Gründe zu sehen.");
                                break;
                        }
                        break;
                    } else {
                        sender.sendMessage(BungeeHelper.prefix + "§cSpieler konnte nicht gefunden werden!");
                    }

            }
        } else {
            sender.sendMessage(BungeeHelper.noperm);
        }


    }
}
