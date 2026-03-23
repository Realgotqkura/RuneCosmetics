package com.realgotqkura.runeCosmetics.utils;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Particle;

public enum Rune {

    REDSTONE;


    public String getName(){
        char[] chars = this.toString().toLowerCase().toCharArray();
        String newChar = Character.toString(chars[0]).toUpperCase();
        chars[0] = newChar.toCharArray()[0];
        return String.copyValueOf(chars);
    }

    public String getBase64(){
        switch(this){
            case REDSTONE -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWUwY2E1OTI2ODU1ZTRjYTg3ZTEwNmJkYzZiNTczYzZmYzE0MjAxODI2Mjg3ODFhOTNjYjg0ZTg2ZWIxMGJmNiJ9fX0="; }

            default -> { return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJiMzBmZDEyMTRkN2ViMGJmZWMyNTZhNDA2Mzk1ZDEzYmI4ZjMyYjQyYzkzMmE1ODJiNjBlNDIxZjE1NDIxIn19fQ=="; }
        }
    }

    public ChatColor getColor(){
        switch(this){
            case REDSTONE -> {return ChatColor.DARK_RED;}
            default -> {return ChatColor.BLACK;}
        }
    }

    public String getSymbol(){
        switch (this){
            case REDSTONE -> {return "⚜";}
        }

        return "NO SYMBOL";
    }

    public Particle getParticle(){
        switch (this){
            case REDSTONE -> {
                Particle particle;
                try {
                    particle = Particle.valueOf("DUST");
                } catch (IllegalArgumentException e) {
                    particle = Particle.valueOf("REDSTONE");
                }
                return particle;
            }
        }

        return null;
    }
}
