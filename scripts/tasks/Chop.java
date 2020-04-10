package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GameObject;
import scripts.Task;

import java.util.concurrent.Callable;

public class Chop extends Task {

    public final int [] TREE_IDS = {1276, 1277, 1278, 1279, 1280};
    public final int [] TREE_IDS_15 = {1276, 1277, 1278, 1279, 1280, 10820};

    public int [] available_tree_ids = {};

    Tile treeLocation = Tile.NIL;


    public Chop(ClientContext ctx) {
        super(ctx);
        setTreeIds();
    }

    @Override
    public boolean activate() {
        return ctx.objects.select().at(treeLocation).id(available_tree_ids).poll().equals(ctx.objects.nil()) || ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.select().id(available_tree_ids).nearest().poll();
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

    public void setTreeIds() {
        int level = ctx.skills.level(Constants.SKILLS_WOODCUTTING);
        if(level >= 1 && level < 15)
            available_tree_ids = TREE_IDS;
        else if(level >= 15) {
            available_tree_ids = TREE_IDS_15;
        }
    }
}
