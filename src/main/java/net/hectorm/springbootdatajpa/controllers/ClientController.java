package net.hectorm.springbootdatajpa.controllers;

import lombok.extern.log4j.Log4j2;
import net.hectorm.springbootdatajpa.domain.Client;
import net.hectorm.springbootdatajpa.service.IClientService;
import net.hectorm.springbootdatajpa.service.IFileUploadService;
import net.hectorm.springbootdatajpa.utils.PageRender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
@Controller
@Log4j2
public class ClientController {

    private final IClientService clientService;
    private final IFileUploadService fileUploadService;

    public ClientController(@Qualifier("repositoryDataJpa") IClientService clientService, IFileUploadService fileUploadService) {
        this.clientService = clientService;
        this.fileUploadService = fileUploadService;
    }



    @GetMapping({"/list", "", "/"})
    public String listClients(@RequestParam(name = "page", defaultValue = "0") int page, Model model){

        Pageable pageRequest = PageRequest.of(page, 4);

        Page<Client> clients = clientService.findAll(pageRequest);

        PageRender<Client> pageRender = new PageRender<>("/list", clients);

        model.addAttribute("title","Clients listing");
        model.addAttribute("clients", clients);
        model.addAttribute("page", pageRender);
        return "list";
    }


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showFormCreate(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("title", "Form to create a new client");
        return "client_form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveClient(@Valid Client client, BindingResult result, Model model, @RequestParam("file") MultipartFile image, RedirectAttributes attributes){



        if(result.hasErrors()){
            model.addAttribute("title", "Form to create a new client");
            return "client_form";
        }

        if(!image.isEmpty()){


            if(client.getId() != null & client.getImage() != null){

                fileUploadService.delete(client.getImage());
            }

            String uniqueFileName = null;
            try {
                uniqueFileName = fileUploadService.copy(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

            attributes.addFlashAttribute("info", "The profile image has been upload correctly " + uniqueFileName);

            client.setImage(uniqueFileName);


        }

        String msg = (client.getId() != null) ? "The client was successful updated" : "The client was successful saved";

        clientService.save(client);
        attributes.addFlashAttribute("success", msg);
        return "redirect:/list";
    }

    @RequestMapping(value = "/update/{clientId}", method = RequestMethod.GET)
    public String showFormUpdate(Model model, @PathVariable Long clientId, RedirectAttributes attributes){
        Client client = null;
        if(clientId > 0 ){
            client = clientService.findById(clientId);
            if(client == null)
            {
                attributes.addFlashAttribute("error", "The client does not exist in the BD");
                return "redirect:/list";
            }
        }
        else{
            attributes.addFlashAttribute("error", "The client id can't be Zero");
            return "redirect:/list";
        }

        model.addAttribute("title", "Form to update client");
        model.addAttribute("client", client);
        return "client_form";
    }


    @RequestMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable Long clientId, RedirectAttributes attributes){
        Client client = null;
        if(clientId > 0){
            client = clientService.findById(clientId);

            if(client == null)
            {
                attributes.addFlashAttribute("error", "The client does not exist in the BD");
                return "redirect:/list";
            }
            else{
                clientService.deleteById(clientId);
                attributes.addFlashAttribute("success", "The client was successful deleted");

               if(fileUploadService.delete(client.getImage())){
                   attributes.addFlashAttribute("info", "Image "+ client.getImage()+" successful deleted");
               }
            }
        }
        else{
            attributes.addFlashAttribute("error", "The client id can't be Zero");
            return "redirect:/list";
        }

        return "redirect:/list";
    }

    @RequestMapping("/view/{clientId}")
    public String viewClient(@PathVariable Long clientId, RedirectAttributes attributes, Model model){
        Client  client = clientService.findById(clientId);
        if(client == null){
            attributes.addFlashAttribute("The client was not found in the DataBase");
            return "redirect:/list";
        }
        model.addAttribute("client", client);
        model.addAttribute("title", "Client detail page");
        return "view";
    }
}
