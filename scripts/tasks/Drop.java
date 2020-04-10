package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import scripts.Task;

import java.util.concurrent.Callable;

public class Drop extends Task {

    final static int [] LOGS_IDS = { 1511, 1521};

    public Drop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        for(Item log : ctx.inventory.select().id(LOGS_IDS)) {
            if(ctx.controller.isStopping()) {
                break;
            }

            int startAmtLogs = ctx.inventory.select().id(LOGS_IDS).count();
            log.interact("Drop");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(LOGS_IDS).count() != startAmtLogs;
                }
            }, 25, 20);
        }
    }
}
