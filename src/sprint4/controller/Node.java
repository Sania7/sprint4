package sprint4.controller;

import sprint4.task.Task;

public class Node {

    private Node first;
    private Node last;
    private Task task;

    public Node(Node first, Node last, Task task) {
        this.first = first;
        this.last = last;
        this.task = task;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
