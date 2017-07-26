package com.fabiomazzo.meetupcampinas.web.controller

import com.fabiomazzo.meetupcampinas.neo4j.model.Meetup
import com.fabiomazzo.meetupcampinas.neo4j.repository.MeetupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


/**
 * Meetup Controller
 * @author Fabio Covolo Mazzo
 */
@Controller
class MeetupController {

    @Autowired
    lateinit var meetupRepository: MeetupRepository

    val EDIT_PAGE = "meetup/view"

    val LIST_PAGE = "meetup/list"


    @ModelAttribute("allMeetups")
    fun getAllDialogSpecification(): List<Meetup> {
        return meetupRepository.findAll().toList()
    }

    @RequestMapping(value = "/meetup/{id}/edit")
    fun editDialogspecification(@PathVariable("id") id: Long, model: Model): String {
        val optMeetup = meetupRepository.findById(id);
        if(optMeetup.isPresent()) {
            model.addAttribute("dialogSpecification", optMeetup.get())
            return EDIT_PAGE
        }
        return LIST_PAGE
    }

    @RequestMapping(value = "/meetup/new")
    fun newDialogspecification(model: Model): String {
        model.addAttribute("meetup", Meetup())
        return EDIT_PAGE
    }

    @RequestMapping(value = "/meetup/{id}/delete")
    fun deleteDialogspecification(@PathVariable("id") user: Long): String {
        return "meetup/list"
    }

    @RequestMapping("/meetup")
    fun dialogspecification(model: Model): String {
        return "meetup/list"
    }

    @PostMapping("/meetup/save")
    fun save(@ModelAttribute("meetup") meetup: Meetup, model: Model, bindingResult : BindingResult): String {
        val savedMeetup  = meetupRepository.save(meetup)
        model.addAttribute("dialogSpecification", savedMeetup)
        model.set("allDialogs",getAllDialogSpecification())
        if(bindingResult.hasErrors()) {
            return EDIT_PAGE
        }
        return LIST_PAGE
    }
}