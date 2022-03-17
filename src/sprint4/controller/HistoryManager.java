package sprint4.controller;

import sprint4.task.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    void update(Task taskUpdate);
    List<Task> getHistory();
}
