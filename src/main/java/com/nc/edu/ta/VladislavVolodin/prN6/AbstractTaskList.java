package com.nc.edu.ta.VladislavVolodin.prN6;

import java.io.*;
import java.util.Iterator;


/**
 * Description class AbstractTaskList
 */
public abstract class AbstractTaskList implements Serializable, Iterable<Task> {

    /**
     * Cunt of tasks
     */
    protected int size;

    /**
     * Add task
     *
     * @param task name task
     */
    public abstract void add(Task task);

    /**
     * Remove task
     *
     * @param task name task
     */
    public abstract void remove(Task task);

    /**
     * Return task by index
     *
     * @param index task position
     * @return task
     */
    public abstract Task getTask(int index);

    /**
     * Return count of tasks
     *
     * @return size task
     */
    public int size() {
        return size;
    }

    @Override
    public AbstractTaskList clone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(this);
            ous.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (AbstractTaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }
}
