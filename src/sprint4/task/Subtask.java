package sprint4.task;

import sprint4.util.Status;

public class Subtask extends Task {

    private Epic epic;

    public Subtask(String title, String description, int id, Status status, Epic epic) {
        super(title, description, id, status);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "\n\t name= " + getName() +
                "\n\t description= " + getDescription() +
                "\n\t id= " + getId() +
                "\n\t status= " + getStatus() +
                "\n\t}";
    }
}
