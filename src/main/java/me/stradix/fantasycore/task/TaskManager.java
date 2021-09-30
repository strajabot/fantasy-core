package me.stradix.fantasycore.task;

import me.stradix.fantasycore.task.global.UpdatePlayerEnergyTask;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static TaskManager instance = new TaskManager();

    public static TaskManager get() {
        return instance;
    }

    private List<Task> globalTasks = new ArrayList<>();

    private TaskManager() {
        globalTasks.add(new UpdatePlayerEnergyTask());
    }

    public void enable() {
        for(Task task: globalTasks) {
            task.enable();
        }
    }

    public void disable() {
        for(Task task: globalTasks) {
            task.disable();
        }
    }

}
