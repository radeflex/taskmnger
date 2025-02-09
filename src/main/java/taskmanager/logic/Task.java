package taskmanager.logic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Task {
    private final String desc;
    private Status status;
    private final Timestamp dateAdded;
    private final String owner;

    public Task(String desc, Status status, Timestamp dateAdded, String owner) {
        this.desc = desc;
        this.status = status;
        this.dateAdded = dateAdded;
        this.owner = owner;
    }

    public Task(String desc, Status status, String owner) {
        this(desc, status, new Timestamp(new Date().getTime()), owner);
    }

    public String getDesc() {
        return desc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public String getOwner() { return owner; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(desc, task.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, dateAdded);
    }

    @Override
    public String toString() {
        return "Task{" +
                "desc='" + desc + '\'' +
                ", status=" + status +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
