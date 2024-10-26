package com.github.nutt1101.items;

import java.util.List;
import java.util.stream.Collectors;

import com.github.nutt1101.ConfigSetting;

import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GoldEgg {

    //public static ItemStack makeGoldEgg() {
    //    ItemStack goldEgg = new ItemStack(Material.EGG);
//
    //    ItemMeta meta = goldEgg.getItemMeta();
    //    meta.setDisplayName(ConfigSetting.toChat(TranslationFileReader.goldEggName, "", ""));
    //    meta.addEnchant(Enchantment.SOUL_SPEED, 1, true);
    //    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//
    //    meta.setLore(TranslationFileReader.goldEggLore.stream().map(lore -> ChatColor.
    //            translateAlternateColorCodes('&', lore).replace("{PERCENT}", String.valueOf(ConfigSetting.
    //                    chickenDropGoldEggChance))).collect(Collectors.toList()));
//
    //    goldEgg.setItemMeta(meta);
//
    //    return goldEgg;
    //}
//}

public static ItemStack makeGoldEgg()
    {
        ItemStack result = new ItemStack(Material.STICK, 1);
        ItemMeta  meta   = Bukkit.getItemFactory().getItemMeta(Material.STICK);

        meta.setDisplayName("§9晶淵");

        meta.setLore
        (
            List.of
            (
                "§7    ",
                "§7    不存在於這世界的物質，    ",
                "§7    漆黑的漫光為他漆上了綺麗的神祕，    ",
                "§7    然而我們仍可有下一步？    ",
                "§7    "
            )
        );

        meta.setCustomModelData(19903);

        result.setItemMeta(meta);

        return result.clone();
    }
}