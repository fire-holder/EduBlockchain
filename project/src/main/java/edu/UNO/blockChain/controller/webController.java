package edu.uno.blockchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.uno.blockchain.form.UserForm;
import edu.uno.blockchain.form.Broadcastform;
import edu.uno.blockchain.form.Tokenform;
import edu.uno.blockchain.form.Pollform;
import edu.uno.blockchain.repository.UserRepository;
import edu.uno.blockchain.repository.Broadcastrepo;

/*
 * This class is the web controller.
 * Will try to use RestController in the future implemetation
 */
@Controller
public class webController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Broadcastrepo broadcastrepo;

    private String user;
    /*
     * Get mapping and go to the home page.
     */
    @GetMapping("/")
    public String index() {
        return "home";
    }
    /*
     * Get mapping and go to the home page.
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /*
     * Get mapping and go to the register page. This might be changed to avoid the
     * circular error.
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /*
     * Get mapping and go to the login page. This might be changed to avoid the
     * circular error.
     */
    @GetMapping("/sign")
    public String login() {
        return "sign";
    }

    /*
     * Get mapping and go to the user page. This might be changed to avoid the
     * circular error.
     */
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    /*
     * Get mapping and go to the sha256 page. This might be changed to avoid the
     * circular error.
     */
    @GetMapping("/sha256")
    public String hash() {
        return "sha256";
    }

    /*
     * Get mapping and go to the block page. This might be changed to avoid the
     * circular error.
     */
    @GetMapping("/block")
    public String block() {
        return "block";
    }

    @GetMapping(value = "/animation")
    public ModelAndView animation() {
        return new ModelAndView("redirect:http://localhost:3000");
    }

    /*
     * Post mapping and go to the user page if signed in successfully. Remain in the
     * sign in page if unsuccessful.
     */
    @PostMapping(value = "/sign")
    public String login(UserForm sig) {
        if (userRepository.signinUser(sig)) {
            user = sig.getnuid();
            return "user";
        }
        return "sign";
    }

    /*
     * Post mapping and go to the home page if registered.
     */
    @PostMapping(value = "/register")
    public String userInfo(UserForm reg) {
        userRepository.registerUser(reg);
        return "home";
    }


    /*
     * Get mapping and create the transaction, then go to the transaction page. This
     * might be changed to avoid the circular error.
     */
    @GetMapping("/broadcast")
    public String broadcast(Model model) {
        List<Tokenform> ctf = broadcastrepo.findByTokens(user);
        model.addAttribute("tokenList", ctf);
        return "broadcast";
    }
     /*
     * Post mapping and go to the user page if the transaction was created.
     */
    @PostMapping(value = "/broadcast")
    public String createTX(Broadcastform tx) {
        broadcastrepo.createTransaction(tx, user);
        return "user";
    }


    @GetMapping("/token")
    public String token(Model model) {
        List<Tokenform> ctf = broadcastrepo.findByTokens(user);
        model.addAttribute("tokenList1", ctf);
        return "token";
    }

    @GetMapping("/transactionpoll")
    public String txpoll(Model model) {
        List<Pollform> ctf = broadcastrepo.findByPolls();
        model.addAttribute("pollList", ctf);
        return "transactionpoll";
    }

    @GetMapping(value = "/mining")
    public String miningpending() {
        userRepository.miningpending();
        broadcastrepo.minereward(user);
        return "home";
    }
}
