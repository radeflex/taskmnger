package taskmanager.logic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Task {
    private String desc;
    private Status status;
    private final Timestamp dateAdded;

    public Task(String desc, Status status, Timestamp dateAdded) {
        this.desc = desc;
        this.status = status;
        this.dateAdded = dateAdded;
    }

    public Task(String desc, Status status) {
        this(desc, status, new Timestamp(new Date().getTime()));
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
