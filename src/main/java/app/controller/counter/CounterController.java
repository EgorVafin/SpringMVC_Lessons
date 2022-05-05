package app.controller.counter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CounterController {

    @GetMapping("/counter")
    public String index(HttpSession session, Model model) {

        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
        }
        counter++;
        session.setAttribute("counter", counter);
        model.addAttribute("counter", counter);

        return ("counter");

    }


}
