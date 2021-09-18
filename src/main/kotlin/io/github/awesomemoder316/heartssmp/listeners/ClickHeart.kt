package io.github.awesomemoder316.heartssmp.listeners

import io.github.awesomemoder316.heartssmp.HeartsSmp
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class ClickHeart: Listener {

    @EventHandler
    fun rightClick(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_AIR && e.action != Action.RIGHT_CLICK_BLOCK) return

        if (e.item?.isSimilar(HeartsSmp.heartItemModel) != true) return

        e.item!!.amount = 0
        e.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue += 2.0

        e.player.sendMessage(
            Component.text("Your gained a heart!")
                .color(TextColor.color(Integer.parseInt("55FF55", 16)))
        )
    }
}