package com.github.nutt1101.codex;

import com.github.nutt1101.CatchBall;
import com.github.nutt1101.HeadDrop;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class CodexMenu implements InventoryHolder {

    public static final int PREVIOUS_SLOT = 45;
    public static final int PROGRESS_SLOT = 49;
    public static final int TAB_SLOT = 51;
    public static final int NEXT_SLOT = 53;
    private static final int CONTENT_SLOTS = 45;

    private final Inventory inventory;
    private final int page;
    private final int pages;
    private final boolean variantTab;

    public CodexMenu(Map<String, CodexEntry> unlocked, int requestedPage, boolean variantTab) {
        this.variantTab = variantTab;
        List<String> entries = variantTab ? CodexService.variants() : CodexService.species();
        this.pages = Math.max(1, (int) Math.ceil((double) entries.size() / CONTENT_SLOTS));
        this.page = Math.min(Math.max(requestedPage, 0), pages - 1);

        int owned = 0;
        int caught = 0;
        for (String entry : entries) {
            CodexEntry data = unlocked.get(entry);
            if (data != null) {
                owned++;
                caught += data.caught();
            }
        }

        this.inventory = Bukkit.createInventory(this, 54, TranslationFileReader.codex(
                variantTab ? "TitleVariant" : "Title", "PAGE", String.valueOf(page + 1), "PAGES", String.valueOf(pages)));

        SimpleDateFormat format = new SimpleDateFormat(
                TranslationFileReader.localeYamlConfig.getString("Codex.DateFormat", "yyyy/MM/dd HH:mm"));
        for (int slot = 0; slot < CONTENT_SLOTS; slot++) {
            int index = page * CONTENT_SLOTS + slot;
            if (index >= entries.size()) {
                break;
            }
            String entry = entries.get(index);
            inventory.setItem(slot, render(entry, index + 1, unlocked, format));
        }

        if (page > 0) {
            inventory.setItem(PREVIOUS_SLOT, icon("PreviousPage", Material.PAPER,
                    TranslationFileReader.codex("PreviousPage"), null));
        }
        if (page + 1 < pages) {
            inventory.setItem(NEXT_SLOT, icon("NextPage", Material.PAPER,
                    TranslationFileReader.codex("NextPage"), null));
        }
        inventory.setItem(PROGRESS_SLOT, icon("Progress", Material.BOOK, TranslationFileReader.codex("ProgressName"),
                TranslationFileReader.codexList("Progress", "UNLOCKED", String.valueOf(owned),
                        "TOTAL", String.valueOf(entries.size()), "CAUGHT", String.valueOf(caught))));
        inventory.setItem(TAB_SLOT, icon("Tab", Material.NAME_TAG,
                TranslationFileReader.codex(variantTab ? "TabNormal" : "TabVariant"), null));

        ItemStack filler = icon("Filler", Material.GRAY_STAINED_GLASS_PANE, " ", null);
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (inventory.getItem(slot) == null) {
                inventory.setItem(slot, filler.clone());
            }
        }
    }

    private ItemStack render(String entry, int number, Map<String, CodexEntry> unlocked, SimpleDateFormat format) {
        CodexEntry data = unlocked.get(entry);
        ItemStack item = data != null
                ? new HeadDrop().skullTextures(new ItemStack(Material.PLAYER_HEAD), CodexService.value(entry, "Skull"))
                : icon("Locked", Material.SKELETON_SKULL, "", null);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(TranslationFileReader.codex("Number", "NUMBER", String.format("%03d", number))
                + (data != null ? ChatColor.WHITE : ChatColor.GRAY)
                + ChatColor.translateAlternateColorCodes('&', CodexService.value(entry, "DisplayName")));

        List<String> lore = new ArrayList<>();
        lore.add(TranslationFileReader.codex(data != null ? "StatusUnlocked" : "StatusLocked"));
        if (data != null) {
            lore.add(TranslationFileReader.codex("UnlockedAt", "VALUE", format.format(new Date(data.unlockedAt()))));
            lore.add(TranslationFileReader.codex("Caught", "VALUE", String.valueOf(data.caught())));
        }
        if (!variantTab) {
            List<String> variants = CodexService.variants(entry);
            if (!variants.isEmpty()) {
                long owned = variants.stream().filter(unlocked::containsKey).count();
                lore.add(TranslationFileReader.codex("Variants",
                        "UNLOCKED", String.valueOf(owned), "TOTAL", String.valueOf(variants.size())));
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @SuppressWarnings("deprecation")
    private static ItemStack icon(String name, Material fallback, String displayName, List<String> lore) {
        FileConfiguration config = CatchBall.plugin.getConfig();
        Material material = Material.matchMaterial(config.getString("Codex.Icons." + name + ".Material", fallback.name()));
        ItemStack item = new ItemStack(material == null ? fallback : material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        int modelData = config.getInt("Codex.Icons." + name + ".CustomModelData", 0);
        if (modelData > 0) {
            meta.setCustomModelData(modelData);
        }
        if (lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public int page() {
        return page;
    }

    public int pages() {
        return pages;
    }

    public boolean variantTab() {
        return variantTab;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
