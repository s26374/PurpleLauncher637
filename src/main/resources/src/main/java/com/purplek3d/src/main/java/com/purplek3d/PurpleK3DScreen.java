package com.purplek3d;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PurpleK3DScreen extends Screen {
    public PurpleK3DScreen() {
        super(Text.literal("Purple K3D Visuals"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("HUD Виджет: " + (PurpleK3DClient.hudEnabled ? "§dВКЛ" : "§7ВЫКЛ")),
                button -> {
                    PurpleK3DClient.hudEnabled = !PurpleK3DClient.hudEnabled;
                    button.setMessage(Text.literal("HUD Виджет: " + (PurpleK3DClient.hudEnabled ? "§dВКЛ" : "§7ВЫКЛ")));
                }
        ).dimensions(centerX - 90, centerY - 28, 180, 22).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Партиклы ударов: " + (PurpleK3DClient.hitParticles ? "§dВКЛ" : "§7ВЫКЛ")),
                button -> {
                    PurpleK3DClient.hitParticles = !PurpleK3DClient.hitParticles;
                    button.setMessage(Text.literal("Партиклы ударов: " + (PurpleK3DClient.hitParticles ? "§dВКЛ" : "§7ВЫКЛ")));
                }
        ).dimensions(centerX - 90, centerY, 180, 22).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Закрыть"),
                button -> this.close()
        ).dimensions(centerX - 90, centerY + 32, 180, 22).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(0, 0, this.width, this.height, 0xAA2A0E54, 0xDD090412);

        int cardW = 220, cardH = 150;
        int left = (this.width - cardW) / 2;
        int top = (this.height - cardH) / 2;

        context.fill(left, top, left + cardW, top + cardH, 0xDD120824);
        context.drawBorder(left, top, cardW, cardH, 0xFFC084FC);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§d§lPURPLE K3D STYLE"), this.width / 2, top + 14, 0xFFFFFFFF);

        super.render(context, mouseX, mouseY, delta);
    }
}
