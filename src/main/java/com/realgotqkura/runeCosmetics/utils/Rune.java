package com.realgotqkura.runeCosmetics.utils;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Particle;

public enum Rune {

    REDSTONE, EMERALD, FIRE, SOUL_FIRE, WRATH, EXPLOSION;


    public String getName(){
        char[] chars = this.toString().toLowerCase().toCharArray();
        String newChar = Character.toString(chars[0]).toUpperCase();
        newChar = newChar.replaceAll("_", " ");
        chars[0] = newChar.toCharArray()[0];
        return String.copyValueOf(chars);
    }

    public String getBase64(){
        switch(this){
            case REDSTONE -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWUwY2E1OTI2ODU1ZTRjYTg3ZTEwNmJkYzZiNTczYzZmYzE0MjAxODI2Mjg3ODFhOTNjYjg0ZTg2ZWIxMGJmNiJ9fX0="; }
            case EMERALD -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRhZWVkMDMzZjgyMDI4MWIwNGVkMWIzOWUwOTQxZGVlNGE2MDI3MDE1MGJkMjAwODY0YzNmNTFiYTkxZjVhYiJ9fX0=";}
            case FIRE -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNlOTdmYWI0NzUzYjc1YmE1YjBjMDM4YmVkMzc3YjE2MmJhMjhiN2E1ZTI5MGFiZmQwMThhNTU4MWFjNTM4OCJ9fX0=";}
            case SOUL_FIRE -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDIwY2I3M2YyMDdiMDdkMjU5Y2ExZWVmN2NhZGUzMDJjN2VhMTA0YjhhMGMyNDBiODk3YTU3Nzk3MTEyMmU1NiJ9fX0=";}
            case WRATH -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZiOTdiMTdjNjM5NTM5MjY1OGYzMjcxOGFhNDZiZWZhMWMzMWQzNTcyNjUxYzMwZjdkMmJmM2I5M2Y2ZWFkOSJ9fX0=";}
            case EXPLOSION -> {return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjgzMWQ1NzIxOTUwZWZhOWFhNzk5NjdlYjE5MTZhYmViNjRjOTc4ZWE4NTkzYTBjNjgzMzU0ODA5YzZjMzYxZCJ9fX0=";}

            default -> { return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJiMzBmZDEyMTRkN2ViMGJmZWMyNTZhNDA2Mzk1ZDEzYmI4ZjMyYjQyYzkzMmE1ODJiNjBlNDIxZjE1NDIxIn19fQ=="; }
        }
    }

    public ChatColor getColor(){
        switch(this){
            case REDSTONE -> {return ChatColor.DARK_RED;}
            case EMERALD -> {return ChatColor.of("#38D117");}
            case FIRE ->{return ChatColor.of("#F7994D");}
            case SOUL_FIRE ->  {return ChatColor.of("#23C5CC");}
            case WRATH -> {return ChatColor.RED;}
            case EXPLOSION -> {return ChatColor.of("#AB8684");}


            default -> {return ChatColor.BLACK;}
        }
    }

    public String getSymbol(){
        switch (this){
            case REDSTONE -> {return "⚜";}
            case EMERALD -> {return "✧";}
            case FIRE -> {return "♨";}
            case SOUL_FIRE -> {return "☉";}
            case WRATH -> {return "☠";}
            case EXPLOSION -> {return "⚠";}
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
            case EMERALD -> {return Particle.HAPPY_VILLAGER;}
            case FIRE -> {return Particle.FLAME;}
            case SOUL_FIRE -> {return Particle.SOUL_FIRE_FLAME;}
            case WRATH -> {return Particle.ANGRY_VILLAGER;}
            case EXPLOSION -> {return Particle.EXPLOSION;}
        }

        return null;
    }
}
