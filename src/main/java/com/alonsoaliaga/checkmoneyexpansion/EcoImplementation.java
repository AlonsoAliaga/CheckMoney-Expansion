package com.alonsoaliaga.checkmoneyexpansion;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EcoImplementation {
    private final boolean hooked;
    private Economy econ = null;
    public EcoImplementation() {
        hooked = setupEconomy();
    }
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public boolean isHooked() {
        return hooked;
    }
    public Economy getEcon() {
        return econ;
    }
}