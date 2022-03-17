package sprint4.controller;

import sprint4.util.InMemoryTaskManager;

public class Managers {
    public static TaskManager getDafault(HistoryManager historyManager) {
        return new InMemoryTaskManager(historyManager);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
