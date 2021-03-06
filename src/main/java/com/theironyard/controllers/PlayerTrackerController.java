package com.theironyard.controllers;

import com.theironyard.entities.Player;
import com.theironyard.entities.User;
import com.theironyard.services.PlayerRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by michaeldelli-gatti on 6/23/16.
 */
@Controller
public class PlayerTrackerController {

    @Autowired
    UserRepository users;

    @Autowired
    PlayerRepository players;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("players", players.findAll());
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = users.findByUsername(username);
        if (user == null){
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPassword())){
            throw new Exception("wrong password");
        }

        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "create-player", method = RequestMethod.POST)
    public String createPlayer(HttpSession session, String name, String team, String position, int number, String comments) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception("not logged in");
        }
        User user = users.findByUsername(username);
        Player player = new Player(name, team, position, number, comments, username, user);
        players.save(player);
        return "redirect:/";
    }

    @RequestMapping(path = "delete-player", method = RequestMethod.POST)
    public String deletePlayer(HttpSession session, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception("not logged in");
        }
        players.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "edit-player", method = RequestMethod.POST)
    public String editPlayer(int id, HttpSession session, String name, String team, String position, int number, String comments) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception("not logged in");
        }
        //get author
        User user = users.findByUsername(username);
        Player player = new Player(id, name, team, position, number, comments, username, user);
        players.save(player);
        return "redirect:/";
    }
}
