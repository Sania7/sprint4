package sprint4.task;

import sprint4.util.Status;
import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> list = new ArrayList<>();

    public Epic(String name, String description, int id, Status status) {
        super(name, description, id, status);
    }

    public ArrayList<Subtask> getList() {
        return list;
    }

    public void addSubtask(Subtask subtask) {
        list.add(subtask);
    }

    public void delSubtask(Subtask subtask) {
        list.remove(subtask);
    }

    public void updateSubtask(Subtask subtask) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == subtask.getId()) {
                list.set(i, subtask);
                return;
            }
        }
    }

    @Override
    public Status getStatus() {
        if (list.isEmpty()) {
            return Status.NEW;
        }
        int newStatus = 0;
        int doneStatus = 0;

        for (Subtask lSubtask : list) {
            switch (lSubtask.getStatus()) {
                case NEW:
                    newStatus++;
                    break;
                case DONE:
                    doneStatus++;
                    break;
            }
        }
        if (newStatus == list.size()) {
            return Status.NEW;
        }
        if (doneStatus == list.size()) {
            return Status.DONE;
        }
        return Status.IN_PROGRESS;
    }

    @Override
    public String toString() {
        return "Epic {" +
                "\n\t name = " + getName() +
                "\n\t description = " + getDescription() +
                "\n\t id = " + getId() +
                "\n\t status = " + getStatus() +
                "\n\t subtasks = " + list +
                "\n\t}";
    }
}
