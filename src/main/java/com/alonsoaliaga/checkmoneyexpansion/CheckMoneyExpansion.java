package com.alonsoaliaga.checkmoneyexpansion;

import com.alonsoaliaga.checkmoneyexpansion.utils.ChatUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class CheckMoneyExpansion extends PlaceholderExpansion implements Configurable, Cacheable {
    boolean debug = false;
    private final Pattern splitPattern = Pattern.compile("_(?=[^\\}]*(?:\\{|$))");
    private DecimalFormat df;
    private EcoImplementation eco = null;
    private String vaultNotInstalled,
            vaultNotHooked,
            playersOnly;
    public CheckMoneyExpansion() {
        try {
            debug = getPlaceholderAPI().getPlaceholderAPIConfig().isDebugMode();
        } catch (Throwable ignored) {}
        int maxDecimals = getInt("max-decimals", 10);
        df = new DecimalFormat("0" + (maxDecimals >= 1 ? ("." + String.join("", Collections.nCopies(maxDecimals, "#"))) : ""));
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            eco = new EcoImplementation();
            if(eco.isHooked())
                Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Vault is not installed. 'hasenough' placeholder is available..");
            else Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Vault couldn't be hooked. 'hasenough' placeholder won't work..");
        }else{
            Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Vault is not installed. 'hasenough' placeholder won't work..");
        }
        vaultNotInstalled = ChatUtils.parseAllFormatting(getString("vault-not-installed","Vault is not installed."));
        vaultNotHooked = ChatUtils.parseAllFormatting(getString("vault-not-hooked","Vault couldn't be hooked."));
        playersOnly = ChatUtils.parseAllFormatting(getString("players-only","Placeholder for players only."));
    }
    @Override
    public void clear() {

    }
    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if (params.equalsIgnoreCase("version")) {
            return getVersion();
        }
        if (params.equalsIgnoreCase("author")) {
            return getAuthor();
        }
        if (params.startsWith("hasenough_")) { // %checkmoney_hasenough_{price}_Yes output_No output%
            if(eco == null) return vaultNotInstalled;
            if(!eco.isHooked()) return vaultNotHooked;
            if(p == null) return playersOnly;
            String[] parts = splitPattern.split(params.substring(10),3);
            if(parts.length >= 3) {
                String priceString = PlaceholderAPI.setBracketPlaceholders(p,parts[0]);
                try{
                    double price = Double.parseDouble(priceString);
                    String priceStr = df.format(price);
                    String balanceStr = df.format(eco.getEcon().getBalance(p));
                    if(eco.getEcon().has(p,price)) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[1]
                                .replace("[PRICE]",priceStr)
                                .replace("[BALANCE]",balanceStr)));
                    }else{
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]
                                .replace("[PRICE]",priceStr)
                                .replace("[BALANCE]",balanceStr)));
                    }
                }catch (Throwable e) {
                    String noString = ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]));
                    if(debug) Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Debug mode is enabled. Error: "+e.getMessage());
                    return noString;
                }
            }
            return null;
        }
        if (params.startsWith("hasenoughcustom_")) { // %checkmoney_hasenoughcustom_{money_placeholder}_{price}_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(16),4);
            if(debug) {
                Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Debug for split: ["+String.join(",",parts)+"]");
            }
            if(parts.length >= 4) {
                String balanceString = PlaceholderAPI.setBracketPlaceholders(p,parts[0]);
                String priceString = PlaceholderAPI.setBracketPlaceholders(p,parts[1]);
                try{
                    double balance = Double.parseDouble(balanceString);
                    double price = Double.parseDouble(priceString);
                    String priceStr = df.format(price);
                    String balanceStr = df.format(balance);
                    if(balance >= price) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]
                                .replace("[PRICE]",priceStr)
                                .replace("[BALANCE]",balanceStr)));
                    }else{
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[3]
                                .replace("[PRICE]",priceStr)
                                .replace("[BALANCE]",balanceStr)));
                    }
                }catch (Throwable e) {
                    String noString = ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[3]
                            .replace("[PRICE]",priceString)
                            .replace("[BALANCE]",balanceString)));
                    if(debug) Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Debug mode is enabled. Error: "+e.getMessage());
                    return noString;
                }
            }
            return null;
        }
        return null;
    }
    @Override
    public Map<String, Object> getDefaults() {
        final Map<String, Object> defaults = new LinkedHashMap<>();
        defaults.put("max-decimals",10);
        defaults.put("vault-not-installed","Vault is not installed.");
        defaults.put("vault-not-hooked","Vault couldn't be hooked.");
        defaults.put("players-only","Placeholder for players only.");
        return defaults;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "checkmoney";
    }
    @Override
    public @NotNull String getAuthor() {
        return "AlonsoAliaga";
    }
    @Override
    public @NotNull String getVersion() {
        return "0.1-BETA";
    }
}