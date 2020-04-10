package scripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

public class MouseMovement extends Task {
    public MouseMovement(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() != -1;
    }

    @Override
    public void execute() {

    }
}
