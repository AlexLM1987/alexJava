package main;

import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    ToDoRepository toDoRepository;

    @GetMapping("/")
    public String index(Model model){
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDos = new ArrayList<>();
        for (ToDo toDo : toDoIterable){
            toDos.add(toDo);
        }
        model.addAttribute("toDos", toDos);
        return "index";
    }
}
