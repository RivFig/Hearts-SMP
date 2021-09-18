package io.github.awesomemoder316.heartssmp.listeners

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe

class UnlockRecipe: Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        if (!e.player.hasDiscoveredRecipe(NamespacedKey.minecraft("heart")))
            e.player.discoverRecipe(NamespacedKey.minecraft("heart"))

        if (!e.player.hasDiscoveredRecipe(NamespacedKey.minecraft("heart_fragment")))
            e.player.discoverRecipe(NamespacedKey.minecraft("heart_fragment"))
    }
}