package watch;

import customsettingsui.components.settings.SelectionSetting;
import customsettingsui.settings.CustomModSettings;
import customsettingsui.settings.CustomModSettingsGetter;
import jdk.jfr.events.ExceptionThrownEvent;
import necesse.engine.modLoader.ModLoader;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.registries.ItemRegistry;
import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.item.Item;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import net.bytebuddy.asm.Advice;

@ModEntry
public class Watch  {
    public static CustomModSettingsGetter settingsGetter;

    public void init() {
        System.out.println("Watch Loaded!");
        ItemRegistry.registerItem("Watch", new WatchItem(Item.Rarity.UNCOMMON, "watchbuff", 400), 10f, true);
        BuffRegistry.registerBuff("watchbuff", new WatchBuff());
    }

    public void postInit() {
        Recipes.registerModRecipe(new Recipe(
                "Watch",
                1,
                RecipeTechRegistry.DEMONIC_WORKSTATION,
                new Ingredient[]{
                        new Ingredient("glass", 1),
                        new Ingredient("ironbar", 3),
                        new Ingredient("wire", 5),
                        new Ingredient("leather", 2)
                }
        ).showAfter("chainshirt"));
    }

    public ModSettings initSettings() {
        CustomModSettings customModSettings = new CustomModSettings()
            .addTextSeparator("timeFormat")
            .addSelectionSetting("timeFormat", 0,
                    new SelectionSetting.Option("12hr","12"),
                    new SelectionSetting.Option("24hr","24")
            );
        settingsGetter = customModSettings.getGetter();
        return customModSettings;
    }
}
