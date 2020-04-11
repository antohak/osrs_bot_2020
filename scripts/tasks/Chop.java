package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GameObject;
import scripts.Task;
import scripts.objects.Trees;

import java.util.concurrent.Callable;

public class Chop extends Task {

    public int [] available_tree_ids = {};

    Tile treeLocation = Tile.NIL;


    public Chop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.objects.select().at(treeLocation).id(Trees.TREE_IDS_LEVEL_30).poll().equals(ctx.objects.nil()) || ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.select().id(Trees.TREE_IDS_LEVEL_30).nearest().poll();
        treeLocation = tree.tile();
        ctx.camera.turnTo(tree);
        tree.interact("Chop down");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        });
    }
}
