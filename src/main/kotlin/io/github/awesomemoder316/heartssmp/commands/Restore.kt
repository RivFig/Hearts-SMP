package io.github.awesomemoder316.heartssmp.commands

import io.github.awesomemoder316.heartssmp.HeartsSmp
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil

class Restore: CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage(
                Component.text("Incorrect usage: command is /restore (player name)")
                    .color(TextColor.color(Integer.parseInt("FF5555", 16))))
            return true
        }

                if ((sender !is Player || sender.hasPermission("heartssmp.admin")) &&
                  Bukkit.getPlayer(args[0]) != null) {

                    sender.sendMessage(
                        Component.text("Restored ${args[0]}'s max health to 10 hearts.")
                            .color(TextColor.color(Integer.parseInt("55FF55", 16)))
                    )
                    Bukkit.getPlayer(args[0])!!.sendMessage(
                        Component.text("Your max health has been restored to 10 hearts."
                        ).color(TextColor.color(Integer.parseInt("55FF55", 16)))
                    )

                    Bukkit.getPlayer(args[0])!!.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 20.0
                    return true
                }

                sender.sendMessage(
                    Component.text("Incorrect usage: command is /heartssmp withdraw (number of hearts)")
                        .color(TextColor.color(Integer.parseInt("FF5555", 16))))

                return true
            }



}