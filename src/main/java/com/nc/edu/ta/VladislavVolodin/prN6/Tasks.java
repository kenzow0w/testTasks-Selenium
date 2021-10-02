package com.nc.edu.ta.VladislavVolodin.prN6;

import java.util.*;
import java.util.function.Predicate;

public class Tasks {

    /**
     * Return task list in a set interval
     *
     * @param from start notification time
     * @param to   end notification time
     * @return task list
     */
    public static List<Task> incoming(List<Task> tasks, Date from, Date to) {
        exceptionDate(from, to);
        Predicate<Date> predicate = date -> date.compareTo(from) > 0 && date.compareTo(to) <= 0;
        return getResultTasks(tasks, predicate);
    }

    public static List<Task> incoming(List<Task> tasks, Date point) {
        Predicate<Date> predicate = date -> date.compareTo(point) == 0;
        return getResultTasks(tasks, predicate);
    }

    private static List<Task> getResultTasks(List<Task> tasks, Predicate<Date> predicate) {
        List<Task> resultList = new ArrayList<>();
        for (Task tmp : tasks) {
            if (tmp.isActive()) {
                if (tmp.isRepeated()) {
                    if (tmp.getRepeatInterval() != 0) {
                        for (Date date = tmp.getStartTime(); date.compareTo(tmp.getEndTime()) <= 0; date = new Date(date.getTime() + (tmp.getRepeatInterval() * 1000L))) {
                            if (predicate.test(date)) {
                                resultList.add(tmp);
                                break;
                            }
                        }
                    }
                } else {
                    Date date = tmp.getTime();
                    if (predicate.test(date)) {
                        resultList.add(tmp);
                    }
                }
            }
        }
        return resultList;
    }


    public static SortedMap<Date, Set<Task>> timeline(List<Task> tasks, Date from, Date to) {
        exceptionDate(from, to);
        TreeMap<Date, Set<Task>> result = new TreeMap<>();
        from = new Date(from.getTime() + 1);
        for (Date j = from; j.compareTo(to) <= 0; j = new Date(j.getTime() + (60 * 60 * 1000L))) {
            Set<Task> setTasks = new HashSet<>(incoming(tasks, j));
            result.put(j, setTasks);
        }

        return result;
    }

    private static void exceptionDate(Date from, Date to) {
        if (from.compareTo(to) >= 0) {
            throw new RuntimeException("from can't be more or equals to");
        }
    }

}



