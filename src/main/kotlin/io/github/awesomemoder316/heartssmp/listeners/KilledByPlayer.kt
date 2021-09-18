package io.github.awesomemoder316.heartssmp.listeners

import com.SirBlobman.combatlogx.api.event.PlayerPunishEvent
import org.bukkit.GameMode
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class KilledByPlayer: Listener {

    @EventHandler
    fun combatLogged(e: PlayerPunishEvent) {
        e.player.killer = e.previousEnemy as Player
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {

        val deadPlayer = e.entity
        val killer = deadPlayer.killer

        val deadPlayerHearts = deadPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH)
        if (deadPlayerHearts!!.baseValue <= 2.0) deadPlayer.gameMode = GameMode.SPECTATOR
        else deadPlayerHearts.baseValue -= 2.0

        if (killer != null)
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue += 2.0
    }

}