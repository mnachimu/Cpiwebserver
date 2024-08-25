package com.orisinterview.cpiwebserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "This is the CPI webserver.\n" +
                "You can call the `/cpi` with the month and year(eg. 'May 2023') as the input body to get cpi value";
    }
}
