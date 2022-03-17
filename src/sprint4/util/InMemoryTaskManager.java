package sprint4.util;

import sprint4.controller.HistoryManager;
import sprint4.controller.TaskManager;
import sprint4.task.Epic;
import sprint4.task.Subtask;
import sprint4.task.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, Subtask> subTasks;
    private Map<Integer, Epic> epics;

        private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
            tasks = new HashMap();
            subTasks = new HashMap();
            epics = new HashMap();
            this.historyManager = historyManager;
        }

        @Override
        public Task getTask(int id) {
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        }

        @Override
        public Subtask getSubtask(int id) {
            historyManager.add(subTasks.get(id));
            return subTasks.get(id);
        }

        @Override
        public Epic getEpic(int id) {
            historyManager.add(epics.get(id));
            return epics.get(id);
        }

        @Override
        public void newTask(Task task) {
            if (tasks.containsKey(task.getId())) {
                return;
            }
            this.tasks.put(task.getId(), task);
        }

        @Override
        public Subtask newSubtask(Subtask subtask) {
            if (subTasks.containsKey(subtask.getId())) {
                return null;
            }
            subTasks.put(subtask.getId(), subtask);
            epics.get(subtask.getEpic().getId()).addSubtask(subtask);
            return subtask;
        }

        @Override
        public Epic newEpic(Epic epic) {
            if (epics.containsKey(epic.getId())) {
                return null;
            }
            return epics.put(epic.getId(), epic);
        }

    @Override
    public void removeAllTasks() {
        tasks.clear();
        subTasks.clear();
        epics.clear();
    }

    @Override
        public void updateTask(Task task) {
        historyManager.update(task);
        tasks.put(task.getId(), task);
        }

        @Override
        public void updateSubtask(Subtask subtask) {
        historyManager.update(subtask);
        epics.get(subtask.getEpic().getId()).updateSubtask(subtask);
        subTasks.put(subtask.getId(), subtask);
        }

        @Override
        public void updateEpic(Epic epic) {
            Epic newEpic = epics.get(epic.getId());
            if (newEpic == null) {
                return;
            }
            for (Subtask s : newEpic.getList()) {
                newEpic.addSubtask(s);
            }
            epics.put(epic.getId(), newEpic);
        }

        @Override
        public void removeTask(int number) {
        historyManager.remove(number);
        tasks.remove(number);
        }

        @Override
        public void removeSubtask(int number) {
            Subtask subtask = subTasks.get(number);
            historyManager.remove(number);
            subTasks.remove(number);
            epics.get(subtask.getEpic().getId()).delSubtask(subtask);
        }

        @Override
        public void removeEpic(int number) {

            ArrayList<Subtask> subtasks = getEpic(number).getList();
            for (Subtask s : subtasks) {
                historyManager.remove(number);
                subTasks.remove(s.getId());
            }
            epics.remove(number);
        }

        @Override
        public List<Task> history() {
            return historyManager.getHistory();
        }

        @Override
        public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
            return epic.getList();
        }

        @Override
        public List<Task> getAllTasks() {
            return new ArrayList<>(tasks.values());
        }

        @Override
        public List<Subtask> getAllSubtasks() {
            return new ArrayList<>(subTasks.values());
        }

        @Override
        public List<Epic> getAllEpics() {
            return new ArrayList<>(epics.values());
        }
    }


