package hu.otpmobil.ticket.core.controller;

import hu.otpmobil.common.dto.UserValidationResponseDto;
import hu.otpmobil.ticket.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/validatetoken", method = RequestMethod.GET)
    public UserValidationResponseDto validateUserToken(@RequestParam(required = false) String userToken) {
        return userService.validateUser(userToken);
    }
}

