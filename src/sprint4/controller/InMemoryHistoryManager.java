package sprint4.controller;

import sprint4.task.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private static final Map<Integer, Node> mapHistory = new HashMap<>();
    private Node head;
    private Node tail;

    @Override
    public void add(Task task) {
        int taskId = task.getId();
        if (mapHistory.containsKey(taskId)) {
            Node node = mapHistory.get(taskId);
            removeNode(node);
        }
        Node lastNode = linkLast(task);
        mapHistory.put(taskId, lastNode);
        tail = lastNode;
    }

    @Override
    public void remove(int id) {
        if (mapHistory.containsKey(id)) {
            removeNode(mapHistory.get(id));
        }
    }

    @Override
    public void update(Task taskUpdate) {
        Node oldNode = mapHistory.get(taskUpdate.getId());
        if (oldNode != null) {
            Node newNode = new Node(oldNode.getFirst(), oldNode.getLast(), taskUpdate);
            mapHistory.put(taskUpdate.getId(), newNode);
            if (tail == oldNode) tail = newNode;
            if (head == oldNode) head = newNode;
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private Node linkLast(Task task) {
        Node node;
        if (tail == null) {
            node = new Node(null, null, task);
            head = node;
        } else {
            node = new Node(tail, null, task);
            tail.setLast(node);
        }
        return node;
    }

    private List<Task> getTasks() {
        if (head == null) {
            return new ArrayList<>();
        }
        ArrayList<Task> getTasks = new ArrayList<>(mapHistory.size());
        getTasks.add(head.getTask());
        Node nextNode = head.getLast();
        while (nextNode != null) {
            getTasks.add(nextNode.getTask());
            nextNode = nextNode.getLast();
        }
        return getTasks;
    }

    private void removeNode(Node node) {
        Node previousNode = node.getFirst();
        Node nextNode = node.getLast();
        mapHistory.remove(node.getTask().getId());
        if ((previousNode == null) && (nextNode == null)) {
            head = null;
            tail = null;
        } else if (previousNode == null) {
            nextNode.setFirst(null);
            head = nextNode;
        } else if (nextNode == null) {
            previousNode.setLast(null);
            tail = previousNode;
        } else {
            previousNode.setLast(nextNode);
            nextNode.setFirst(previousNode);
        }
    }
}
