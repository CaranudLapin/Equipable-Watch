package watch;

import necesse.engine.GameLog;
import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.trinketItem.SimpleTrinketItem;

public class WatchItem extends SimpleTrinketItem {
    public WatchItem(Rarity rarity, String buffStringID, int enchantCost) {
        super(rarity, buffStringID, enchantCost);
    }

    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = new ListGameTooltips();
        tooltips.add(Localization.translate("itemtooltip", this.isAbilityTrinket(item) ? "trinketabilityslot" : "trinketslot"));
        tooltips.add(Localization.translate("itemtooltip", "watchbuff1"));
        if (Watch.settingsGetter.getSelection("timeFormat").toString().equalsIgnoreCase("24")) {
            tooltips.add(Localization.translate("itemtooltip", "watchbuff2", "time", perspective.getWorldEntity().getDayTimeReadable()));
        } else {
            if (perspective.getWorldEntity().getDayTimeHour() == 0) {
                tooltips.add(Localization.translate("itemtooltip", "watchbuff2", "time", "12" + ":" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " AM"));
            } else if (perspective.getWorldEntity().getDayTimeHour() <= 11) {
                tooltips.add(Localization.translate("itemtooltip", "watchbuff2", "time", Integer.toString(perspective.getWorldEntity().getDayTimeHour()) + ":" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " AM"));
            } else if (perspective.getWorldEntity().getDayTimeHour() == 12) {
                tooltips.add(Localization.translate("itemtooltip", "watchbuff2", "time", "12:" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " PM"));
            } else if (perspective.getWorldEntity().getDayTimeHour() > 12) {
                tooltips.add(Localization.translate("itemtooltip", "watchbuff2", "time", Integer.toString(perspective.getWorldEntity().getDayTimeHour()-12) + ":" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " PM"));
            }
        }
        return tooltips;
    }
    public String addAzero(Integer time) {
        String out;
        if (time < 10) {
            out = "0" + Integer.toString(time);
        } else {
            out = Integer.toString(time);
        }
        return  out;
    }
}
