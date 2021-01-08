
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  org.lwjgl.input.Keyboard
 */
package me.independed.inceptice.modules.movement;

import java.lang.reflect.Field;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.util.TimerUtil;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class InventoryMove
extends Module {
    TimerUtil timerUtil = new TimerUtil();

    public static float getDirection() {
        float f = InventoryMove.mc.player.rotationYaw;
        if (InventoryMove.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (InventoryMove.mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (InventoryMove.mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (InventoryMove.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (InventoryMove.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return f *= (float)Math.PI / 180;
    }

    void handleJump() {
        if (InventoryMove.mc.player.onGround && Keyboard.isKeyDown((int)InventoryMove.mc.gameSettings.keyBindJump.getKeyCode())) {
            InventoryMove.mc.player.jump();
        }
    }

    public InventoryMove() {
        super("InventoryMove", "allows you to walk in GUI", 0, Module$Category.MOVEMENT);
    }

    void moveLeft(double d) {
        d = 0.046;
        float f = InventoryMove.getDirection();
        InventoryMove.mc.player.motionZ += (double)MathHelper.sin((float)f) * d;
        InventoryMove.mc.player.motionX += (double)MathHelper.cos((float)f) * d;
    }

    void moveRight(double d) {
        d = 0.046;
        float f = InventoryMove.getDirection();
        InventoryMove.mc.player.motionZ -= (double)MathHelper.sin((float)f) * d;
        InventoryMove.mc.player.motionX -= (double)MathHelper.cos((float)f) * d;
    }

    void handleForward(double d) {
        d = 0.046;
        if (!Keyboard.isKeyDown((int)InventoryMove.mc.gameSettings.keyBindForward.getKeyCode())) {
            return;
        }
        this.moveForward(d);
    }

    void moveBack(double d) {
        d = 0.046;
        float f = InventoryMove.getDirection();
        InventoryMove.mc.player.motionX += (double)MathHelper.sin((float)f) * d;
        InventoryMove.mc.player.motionZ -= (double)MathHelper.cos((float)f) * d;
    }

    void handleBack(double d) {
        d = 0.046;
        if (!Keyboard.isKeyDown((int)InventoryMove.mc.gameSettings.keyBindBack.getKeyCode())) {
            return;
        }
        this.moveBack(d);
    }

    public static void setPressed(KeyBinding keyBinding, boolean bl) {
        Field field = keyBinding.getClass().getDeclaredField("pressed");
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
        if (InventoryMove.mc.currentScreen == null || InventoryMove.mc.currentScreen instanceof GuiChat || InventoryMove.mc.player == null || InventoryMove.mc.world == null) {
            return;
        }
        this.timerUtil.reset();
        double d = 0.046;
        if (!InventoryMove.mc.player.onGround) {
            d /= 4.0;
        }
        this.handleJump();
        this.handleForward(d);
        if (!InventoryMove.mc.player.onGround) {
            d /= 2.0;
        }
        this.handleBack(d);
        this.handleLeft(d);
        this.handleRight(d);
    }

    void handleRight(double d) {
        d = 0.046;
        if (!Keyboard.isKeyDown((int)InventoryMove.mc.gameSettings.keyBindRight.getKeyCode())) {
            return;
        }
        this.moveRight(d);
    }

    void handleLeft(double d) {
        d = 0.046;
        if (!Keyboard.isKeyDown((int)InventoryMove.mc.gameSettings.keyBindLeft.getKeyCode())) {
            return;
        }
        this.moveLeft(d);
    }

    void moveForward(double d) {
        d = 0.046;
        float f = InventoryMove.getDirection();
        InventoryMove.mc.player.setSprinting(true);
        InventoryMove.mc.player.motionX -= (double)MathHelper.sin((float)f) * d;
        InventoryMove.mc.player.motionZ += (double)MathHelper.cos((float)f) * d;
    }
}

