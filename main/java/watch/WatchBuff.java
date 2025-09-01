package watch;

import necesse.engine.localization.Localization;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameTexture.GameTexture;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.trinketItem.TrinketItem;

public class WatchBuff extends TrinketBuff {
    public WatchBuff() {
        this.canCancel = false;
        this.isVisible = true;
        this.isImportant = true;
        this.shouldSave = true;
        this.isPassive = true;
    }

    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber) {
    }

    public void drawIcon(int x, int y, ActiveBuff buff) {
        GameTexture drawIcon = this.getDrawIcon(buff);
        drawIcon.initDraw().size(32, 32).draw(x, y);
        String text = "wack";
        int width;
        if (buff.owner.isPlayer) {
            text = getTime((PlayerMob) buff.owner);
        }
        width = FontManager.bit.getWidthCeil(text, durationFontOptions);
        FontManager.bit.drawString((float)(x + 16 - width / 2), (float)(y + 30), text, durationFontOptions);

    }

    public ListGameTooltips getTrinketTooltip(TrinketItem trinketItem, InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTrinketTooltip(trinketItem, item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "watchbuff3"));
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

    public String getTime(PlayerMob perspective) {
        String out = "wack2";
        if (Watch.settingsGetter.getSelection("timeFormat").toString().equalsIgnoreCase("24")) {
            out = perspective.getWorldEntity().getDayTimeReadable();
        } else {
            if (perspective.getWorldEntity().getDayTimeHour() == 0) {
                out = "12" + ":" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " AM";
            } else if (perspective.getWorldEntity().getDayTimeHour() <= 11) {
                out = Integer.toString(perspective.getWorldEntity().getDayTimeHour()) + ":" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " AM";
            } else if (perspective.getWorldEntity().getDayTimeHour() == 12) {
                out = "12:" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " PM";
            } else if (perspective.getWorldEntity().getDayTimeHour() > 12) {
                out = Integer.toString(perspective.getWorldEntity().getDayTimeHour()-12) + ":" + addAzero(perspective.getWorldEntity().getDayTimeMinute()) + " PM";
            }
        }
        return out;
    }
}