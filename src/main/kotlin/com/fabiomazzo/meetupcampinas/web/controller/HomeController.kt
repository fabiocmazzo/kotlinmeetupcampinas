package com.fabiomazzo.meetupcampinas.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Home WebController
 *
 * @author Fabio Covolo Mazzo
 */
@Controller
class HomeController {

    @RequestMapping("/")
    fun home(): String {
        return "index"
    }

}