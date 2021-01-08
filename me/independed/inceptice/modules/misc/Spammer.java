
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.util.TimerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Spammer
extends Module {
    public ModeSetting mode;
    ArrayList messages;
    Random random;
    TimerUtil timerUtil = new TimerUtil();
    public List targets = null;
    int cnt = 0;

    public List getTargets() {
        return Spammer.mc.world.loadedEntityList.stream().filter(entity -> entity != Spammer.mc.player).filter(entity -> Spammer.mc.player.getDistance(entity) <= 1.0E8f).filter(entity -> entity instanceof EntityPlayer).collect(Collectors.toList());
    }

    public void sendingFor(Entity entity) {
        Spammer.mc.player.sendChatMessage("/msg " + entity.getName() + " get best HACK, get 'RAGE R8' vk - 'vk.com/rage_r8'");
    }

    public Spammer() {
        super("Spammer", "spams messages in chat", 0, Module$Category.WORLD);
        this.random = new Random();
        this.mode = new ModeSetting("Mode", "Global", "Global", "MSG");
        this.messages = new ArrayList();
        this.messages.add("[Rage R8] - get Best Hack 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get HVH client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - better than > norender");
        this.messages.add("[Rage R8] - get Best Legit client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get Most updatable 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - better than > infinity");
        this.messages.add("[Rage R8] - get Strongest Client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get good 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - better than > zamorozka");
        this.messages.add("[Rage R8] - get PvP client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get ghost client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get undetectable client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - better than > squad");
        this.messages.add("[Rage R8] - get superior client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get undefeatable client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get fucker client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - better than > sigma");
        this.messages.add("[Rage R8] - get mst and sunrise fucker client 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get this yes 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - get out client now!!! 'vk.com/rage_r8'");
        this.messages.add("[Rage R8] - go for buy client Rage R8 'vk.com/rage_r8'");
    }

    public void sending(String string) {
        Spammer.mc.player.sendChatMessage("!" + string + " vk - 'vk.com/rage_r8' " + (this.random.nextBoolean() ? this.random.nextDouble() : (double)this.random.nextLong()));
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Spammer.mc.player == null || Spammer.mc.player.isDead) {
            return;
        }
        if (this.mode.activeMode == "MSG") {
            if (this.timerUtil.hasReached(9000.0)) {
                this.targets = this.getTargets();
                this.timerUtil.reset();
            }
            if (this.targets.size() > 0 && this.timerUtil.hasReached(1000 + this.random.nextInt(368))) {
                if (this.cnt == this.targets.size()) {
                    this.cnt = 0;
                }
                this.sendingFor((Entity)this.targets.get(this.cnt++));
                this.timerUtil.reset();
            }
        } else if (this.timerUtil.hasReached(4378.0)) {
            double d = 1.0;
            double d2 = 9.0;
            double d3 = Math.random() * (d2 - d + 1.0) + d;
            if (this.cnt == this.messages.size()) {
                this.cnt = 0;
            }
            String string = String.valueOf((String)this.messages.get(this.cnt++) + " " + d3);
            this.sending(string);
            this.timerUtil.reset();
        }
    }
}

