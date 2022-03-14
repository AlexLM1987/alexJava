package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import main.model.ToDo;

public class Storage {

  private static ConcurrentMap<Integer, ToDo> toDoLists = new ConcurrentHashMap<>();
  private static int currentId = 1;

  public static List<ToDo> getAllToDoList() {
    List<ToDo> toDo = new ArrayList<>();
    toDo.addAll(toDoLists.values());
    return toDo;
  }

  public static int addToDoList(ToDo toDo) {
    int id = currentId++;
    toDo.setId(id);
    toDoLists.put(id, toDo);
    return id;
  }

  public static ToDo getToDo(int toDoId) {
    if (toDoLists.containsKey(toDoId)) {
      return toDoLists.get(toDoId);
    }
    return null;
  }

  public static ToDo removeToDo(int toDoId) {
    if (toDoLists.containsKey(toDoId)) {
      return toDoLists.remove(toDoId);
    }
    return null;
  }

  public static void removeAllToDo(){
    if (!toDoLists.isEmpty()) {
      toDoLists.clear();
      }
  }

  public static ToDo replaceToDo(int toDoId, ToDo value) {
    if (toDoLists.containsKey(toDoId)) {
      return toDoLists.replace(toDoId, value);
    }
    return null;
  }
}
