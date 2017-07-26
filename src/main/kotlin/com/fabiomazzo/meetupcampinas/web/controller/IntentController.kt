package com.fabiomazzo.meetupcampinas.web.controller

import com.fabiomazzo.meetupcampinas.neo4j.model.Participant
import com.fabiomazzo.meetupcampinas.neo4j.repository.ParticipantRepository
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
 * SimpleStep Controller
 * @author Fabio Covolo Mazzo
 */
@Controller
class ParticipantController {

    @Autowired
    lateinit var participantRepository: ParticipantRepository

    val EDIT_PAGE = "participant/view"

    val LIST_PAGE = "participant/list"


    @ModelAttribute("allParticipants")
    fun getAllParticipants(): List<Participant> {
        return participantRepository.findAll().toList()
    }


    @RequestMapping(value = "/participant/{id}/edit")
    fun editParticipant(@PathVariable("id") id: Long, model: Model): String {
        val optParticipant = participantRepository.findById(id)
        if(optParticipant.isPresent()) {
            model.addAttribute("participant", optParticipant.get())
            return EDIT_PAGE
        }
        return LIST_PAGE
    }

    @RequestMapping(value = "/participant/new")
    fun newParticipant(model: Model): String {
        model.addAttribute("participant", Participant())
        return EDIT_PAGE
    }

    @RequestMapping(value = "/participant/{id}/delete")
    fun deleteParticipant(@PathVariable("id") id: Long): String {
        participantRepository.deleteById(id)
        return LIST_PAGE
    }

    @RequestMapping("/participant")
    fun participants(): String {
        return LIST_PAGE
    }

    @PostMapping("/participant/save")
    fun save(@ModelAttribute("participant") participant: Participant, model: Model, bindingResult : BindingResult): String {
        val savedParticipant  = participantRepository.save(participant)
        model.addAttribute("participant", savedParticipant)
        model.set("allParticipants",getAllParticipants())
        if(bindingResult.hasErrors()) {
            model.set("error","Erro ao salvar Participant")
            return EDIT_PAGE
        }
        model.set("message","Participant salvo com sucesso")
        return LIST_PAGE
    }
}