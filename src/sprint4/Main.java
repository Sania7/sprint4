package sprint4;

import sprint4.controller.Managers;
import sprint4.controller.TaskManager;
import sprint4.task.Epic;
import sprint4.task.Subtask;
import sprint4.task.Task;
import sprint4.util.Status;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDafault(Managers.getDefaultHistory());

        int id = taskManager.hashCode();
        Task task1 = new Task("Задача 1", "Описание 1", ++id, Status.NEW);
        taskManager.newTask(task1);
        Task task2 = new Task("Задача 2", "Описание 2", ++id, Status.NEW);
        taskManager.newTask(task2);

        Epic epic = new Epic("Эпик ", "Описание ", ++id, Status.NEW);
        taskManager.newEpic(epic);
        Subtask subTask1 = new Subtask("Подзадача 1", "Описание 1", ++id, Status.NEW, epic);
        taskManager.newSubtask(subTask1);
        Subtask subTask2 = new Subtask("Подзадача 2", "Описание 2", ++id, Status.NEW, epic);
        taskManager.newSubtask(subTask2);
        Subtask subTask3 = new Subtask("Подзадача 3", "Описание 3", ++id, Status.NEW, epic);
        taskManager.newSubtask(subTask3);

        Epic epic2 = new Epic("Эпик ", "Описание ", ++id, Status.NEW);
        taskManager.newEpic(epic2);

        taskManager.getTask(task1.getId());
        taskManager.getEpic(epic.getId());
        taskManager.getSubtask(subTask1.getId());
        taskManager.getTask(task2.getId());
        taskManager.getEpic(epic2.getId());
        taskManager.getSubtask(subTask2.getId());
        taskManager.getSubtask(subTask3.getId());
        for (Task tmp : taskManager.history()) {
            System.out.println(tmp);
        }

        taskManager.getTask(task1.getId());
        taskManager.getSubtask(subTask2.getId());
        for (Task tmp : taskManager.history()) {
            System.out.println(tmp);
        }

        taskManager.removeTask(task1.getId());
        for (Task tmp : taskManager.history()) {
            System.out.println(tmp);
        }

        taskManager.removeEpic(epic.getId());
        for (Task tmp : taskManager.history()) {
            System.out.println(tmp);
        }
        Task task2update = new Task("Задача 2", "Описание 2", ++id, Status.IN_PROGRESS);
        taskManager.updateTask(task2update);
        for (Task tmp : taskManager.history()) {
            System.out.println(tmp);
        }
    }
}
