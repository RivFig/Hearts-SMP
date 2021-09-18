package io.github.awesomemoder316.heartssmp

import io.github.awesomemoder316.heartssmp.commands.Restore
import io.github.awesomemoder316.heartssmp.commands.Withdraw
import io.github.awesomemoder316.heartssmp.listeners.ClickHeart
import io.github.awesomemoder316.heartssmp.listeners.KilledByPlayer
import io.github.awesomemoder316.heartssmp.listeners.UnlockRecipe
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

class HeartsSmp: JavaPlugin() {

    companion object {
        lateinit var heartFragmentModel: ItemStack
        lateinit var heartItemModel: ItemStack
    }

    override fun onEnable() {
        heartFragment()
        heartItem()

        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(ClickHeart(), this)
        pluginManager.registerEvents(KilledByPlayer(), this)
        pluginManager.registerEvents(UnlockRecipe(), this)

        getCommand("restore")?.setExecutor(Restore())
        getCommand("withdraw")?.setExecutor(Withdraw())
    }

    private fun heartFragment() {

        val fragment = ItemStack(Material.CARROT_ON_A_STICK)
        val fragmentMeta= fragment.itemMeta

        val lore = listOf(ChatColor.LIGHT_PURPLE.toString() + "Use four of these to craft a heart!")

        fragmentMeta.lore = lore

        fragmentMeta.setDisplayName(ChatColor.RED.toString() + "Heart Fragment")

        fragment.itemMeta = fragmentMeta

        val fragmentRecipe = ShapedRecipe(NamespacedKey.minecraft("heart_fragment"), fragment)
        fragmentRecipe.shape("ODO", "DTD", "ODO")

        fragmentRecipe.setIngredient('O', Material.OBSIDIAN)
        fragmentRecipe.setIngredient('D', Material.DIAMOND_BLOCK)
        fragmentRecipe.setIngredient('T', Material.TOTEM_OF_UNDYING)
        Bukkit.getServer().addRecipe(fragmentRecipe)

        heartFragmentModel = fragment
    }

    private fun heartItem() {

        val heart = ItemStack(Material.WARPED_FUNGUS_ON_A_STICK)
        val heartMeta= heart.itemMeta

        heartMeta.lore = (listOf(ChatColor.LIGHT_PURPLE.toString() + "Use one of these to gain a heart!"))

        heartMeta.setDisplayName(ChatColor.RED.toString() + "Heart")

        heart.itemMeta = heartMeta

        val heartRecipe = ShapedRecipe(NamespacedKey.minecraft("heart"), heart)
        heartRecipe.shape("FDF", "DED", "FDF")

        heartRecipe.setIngredient('F', RecipeChoice.ExactChoice(heartFragmentModel))
        heartRecipe.setIngredient('D', Material.DIAMOND_BLOCK)
        heartRecipe.setIngredient('E', Material.ELYTRA)
        Bukkit.getServer().addRecipe(heartRecipe)

        heartItemModel = heart
    }
}