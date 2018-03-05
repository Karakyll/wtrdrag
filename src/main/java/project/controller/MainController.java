package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * REST controller class for some "/**" requests
 */
@RestController
@RequestMapping(value = { "/", "/about" })
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    @Autowired
    MessageSource messageSource;

    /**
     * TODO
     * Map all "/" and "/about" GET requests
     * @return - string hello
     * TODO
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return messageSource.getMessage("hello", null, new Locale("en","US"));
    }

    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String admin() {
        return messageSource.getMessage("hello", null, new Locale("en","US")) + " admin!";
    }

}
