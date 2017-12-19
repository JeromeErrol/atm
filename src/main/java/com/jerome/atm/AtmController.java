package com.jerome.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {

    @Autowired
    private Atm atm;

    /***
     * This would normally be a post call because it is making a change on the server
     * however for the sake of simplifying the demo I'll leave it as a get request so it can be run in the browser
     */
    @GetMapping(value = "/{amount}")
    public String withdraw(@PathVariable int amount) {
        atm.withdraw(amount);
        return display();
    }

    @GetMapping()
    public String display() {
        return "Fifties:" + atm.getFifties() + ", Twenties: " + atm.getTwenties() + ", Total: " + atm.getTotal();
    }
}
