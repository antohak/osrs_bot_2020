package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Task;

import java.util.concurrent.Callable;

public class Chop extends Task {

    public final int [] TREE_IDS = {1276, 1277, 1278, 1279, 1280};

    public Chop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1 && !ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.select().id(TREE_IDS).nearest().poll();
        ctx.camera.turnTo(tree);
        tree.interact("Chop down", "Tree");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        });
    }
}
