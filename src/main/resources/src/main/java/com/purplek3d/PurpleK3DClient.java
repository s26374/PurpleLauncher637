package com.purplek3d;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.lwjgl.glfw.GLFW;

public class PurpleK3DClient implements ClientModInitializer {
    public static boolean hudEnabled = true;
    public static boolean hitParticles = true;
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.purplek3d.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.purplek3d"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                client.setScreen(new PurpleK3DScreen());
            }
        });

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof GameMenuScreen) {
                Screens.getButtons(screen).add(
                        ButtonWidget.builder(Text.literal("§d✦ Purple K3D"), btn -> client.setScreen(new PurpleK3DScreen()))
                                .dimensions(scaledWidth - 108, 8, 100, 20)
                                .build()
                );
            }
        });

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (world.isClient && hitParticles) {
                for (int i = 0; i  {
            if (hudEnabled) renderPurpleHud(drawContext);
        });
    }

    private void renderPurpleHud(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int totems = 0;
        for (int i = 0; i < client.player.getInventory().size(); i++) {
            if (client.player.getInventory().getStack(i).isOf(Items.TOTEM_OF_UNDYING)) {
                totems += client.player.getInventory().getStack(i).getCount();
            }
        }

        int x = 10, y = 10, width = 148, height = 52;
        context.fill(x, y, x + width, y + height, 0xCC090412);
        context.drawBorder(x, y, width, height, 0xFFA855F7);
        context.fillGradient(x + 1, y + 1, x + width - 1, y + 3, 0xFF9333EA, 0xFFC026D3);

        context.drawTextWithShadow(client.textRenderer, Text.literal("§d§lPURPLE K3D §7v1.0"), x + 8, y + 8, 0xFFE9D5FF);
        context.drawTextWithShadow(client.textRenderer, Text.literal("§7FPS: §f" + client.getCurrentFps()), x + 8, y + 22, 0xFFCBD5E1);
        context.drawTextWithShadow(client.textRenderer, Text.literal("§7Тотемы: §d" + totems), x + 8, y + 35, 0xFFCBD5E1);
    }
    }
  
