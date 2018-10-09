package ru.tretyakov.controllers;

import com.sun.deploy.net.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tretyakov.models.Message;
import ru.tretyakov.models.User;
import ru.tretyakov.service.MessageInterface;
import ru.tretyakov.service.UserInterface;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * This controller provides http request handel.
 *
 * @author Rooter
 * @since 15.01.2018
 **/

@Controller
public class WebController {
    /** Json object to response. */
    private final JSONArray jsonArray;
    /** Json object to response. */
    private final JSONObject jsonObject;
    /** Messages service. */
    @Autowired
    private  MessageInterface messages;
    /** User service. */
    @Autowired
    private UserInterface userService;

    /**
     * Init constructor.
     * @param jsonArray Json object to response
     * @param jsonObject Json object to response
     */
    @Autowired
    public WebController(final JSONArray jsonArray,
                         final JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.jsonArray = jsonArray;
    }
    /**
     *  Provides processing http GET request to /.
     * @return index view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "/index";
    }

    /**
     * Provides processing http GET request to signin endpoint.
     * @return page
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String getSignin() {
        return "/signin";
    }

    /**
     * Provides processing http POST request to signin endpoint.
     * @param session session
     * @param user entity from client side
     * @return status
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject signin(final HttpSession session,
                      final @ModelAttribute User user,
                      final HttpServletResponse response) {
        if (!userService.isUserExist(user)) {
            session.setAttribute("login", user.getUsername());
            userService.add(user);
            jsonObject.put("status", "ok");
            response.setStatus(200);
        } else {
            jsonObject.put("status", "error");
            response.setStatus(403);
        }
        return jsonObject;
    }

    /**
     * Provides processing http POST request to signout endpoint.
     *
     * @param session session
     * @return redirect to signin page
     */
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signout(final HttpSession session) {
        String username = (String) session.getAttribute("login");
        userService.removeByUsername(username);
        session.setAttribute("login", null);
        return "redirect:signin";
    }

    /**
     * Provides processing http POST request to items endpoint.
     * @param index to range
     * @return json array of messages
     */
    @RequestMapping(value = "/items",
            method = RequestMethod.GET)
    public @ResponseBody JSONArray getItems(@RequestParam final Integer index) {
        for (Message message : (List<Message>) messages.getMessages(index)) {
            jsonArray.add(message);
        }
        return jsonArray;
    }

    /**
     * Provides processing http POST request to users endpoint.
     * @param index to range
     * @return json array of users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody JSONArray getUsers(@RequestParam final Integer index) {
        for (User user : userService.getUsers(index)) {
            jsonArray.add(user);
        }
        return jsonArray;
    }

    /**
     * Provides processing http POST request to users endpoint.
     * @param message new messages
     * @param session session
     * @return index page
     */
    @RequestMapping(value = "/send",
            method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute final Message message,
                              final HttpSession session) {
        String username = (String) session.getAttribute("login");
        User user = userService.getUserByName(username);
        message.setUser(user);
        messages.writeMessage(message);
        return "index";
    }
}


