package io.github.awesomemoder316.heartssmp.commands

import io.github.awesomemoder316.heartssmp.HeartsSmp
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player



class Withdraw: CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) {

            sender.sendMessage(
                Component.text("Only players can use /hsmp withdraw")
                    .color(TextColor.color(Integer.parseInt("FF5555", 16)))
            )

            return true
        }

        if (sender.hasPermission("heartssmp.withdraw")) {

            val heartsToWithdraw = 1
            if (sender.health <= 2) {

                // -2 cause ensure player always has 1 heart. heartsToWithdraw * 2 because we check health points, not hearts.
                sender.sendMessage(
                    Component.text("You don't have enough hearts!")
                    .color(TextColor.color(Integer.parseInt("FF5555", 16)))
                )

                return true
            }

            val oldHealth = sender.health
            sender.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue -= 2.0
            sender.health = oldHealth - 2

            for (number in 1..heartsToWithdraw) {

                if (hasAvaliableSlot(sender)) sender.inventory.addItem(HeartsSmp.heartItemModel)
                else sender.world.dropItemNaturally(sender.location, HeartsSmp.heartItemModel)

            }

            sender.sendMessage(
                Component.text("Your withdrew $heartsToWithdraw hearts!")
                    .color(TextColor.color(Integer.parseInt("55FF55", 16)))
            )

            return true
        }
        sender.sendMessage(
            Component.text("Incorrect usage: command is /heartssmp withdraw (number of hearts)")
                .color(TextColor.color(Integer.parseInt("FF5555", 16))))

        return true

    }

    private fun hasAvaliableSlot(player: Player): Boolean {
        for ((index, item) in player.inventory.contents.withIndex()) {
            if (index > 35) return false
            if (item == null || item.type == Material.AIR) {
                return true
            }
        }
        return false
    }

}