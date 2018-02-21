package project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller class for some "/**" requests
 */
@RestController
public class MainController {

    /**
     * TODO
     * Map all "/" and "/about" GET requests
     * @return - string hello
     * TODO
     */
    @RequestMapping(value = { "/", "/about" }, method = RequestMethod.GET)
    public String index() {
        return "Hello !";
    }

}
