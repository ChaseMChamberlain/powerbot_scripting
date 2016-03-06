package fighter;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;
import org.powerbot.script.Condition;

public class Chicken extends Task<ClientContext> {

    public Chicken(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.count() > -1;
    }

    @Override
    public void execute() {
        final Npc chicken = ctx.npcs.select().name("Chicken").nearest().poll();
        Random rnd = new Random();
        if (ctx.players.local().idle()) {
            if (chicken.inViewport()) {
                Fighter.displayStatus = ("Attacking Chicken");
                chicken.hover();
                chicken.interact("Attack");
                Condition.sleep(rnd.nextInt(500, 1000));
                Fighter.killed = Fighter.killed + 1;
                Fighter.totalKilled = Fighter.totalKilled + 1;
                Fighter.chickensKilled = "Chickens killed: " + Fighter.totalKilled;
                System.out.println("Chickens killed: " + Fighter.totalKilled);
            } else {
                ctx.camera.turnTo(chicken);
            }
        }
    }
}