package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import main.model.ToDo;

@RestController
public class ToDoListController {

  @Autowired
  private ToDoRepository toDoRepository;

  @GetMapping("/toDo/")
  public List<ToDo> list() {
    Iterable<ToDo> toDoIterable = toDoRepository.findAll();
    ArrayList<ToDo> toDos = new ArrayList<>();
    for (ToDo toDo : toDoIterable) {
      toDos.add(toDo);
    }
    return toDos;
  }

  @PostMapping("/toDo/")
  public int add(ToDo toDo) {
    ToDo newToDo = toDoRepository.save(toDo);
    return newToDo.getId();
  }

  @GetMapping("/toDo/{id}/")
  public ResponseEntity get(@PathVariable int id) {
    Optional<ToDo> toDoOptional = toDoRepository.findById(id);
    if (toDoOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity(toDoOptional, HttpStatus.OK);
  }

  @DeleteMapping("/toDo/{id}/")
  public ResponseEntity remove(@PathVariable int id) {
    if (list().size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    toDoRepository.deleteById(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/toDo/")
  public ResponseEntity removeAll() {
    if (list().size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    else {
      toDoRepository.deleteAll();
      return new ResponseEntity(HttpStatus.OK);
    }
  }

  @PatchMapping("/toDo/{id}/")
  public ResponseEntity replace(@PathVariable int id, ToDo value) {
    Optional<ToDo> toDoOptional = toDoRepository.findById(id);
    if (toDoOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    ToDo toDo = toDoRepository.save(value);
    return new ResponseEntity(toDo, HttpStatus.OK);
  }
}
