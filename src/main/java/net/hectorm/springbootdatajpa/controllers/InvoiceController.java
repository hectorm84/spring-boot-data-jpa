package net.hectorm.springbootdatajpa.controllers;

import net.hectorm.springbootdatajpa.domain.Client;
import net.hectorm.springbootdatajpa.domain.Invoice;
import net.hectorm.springbootdatajpa.domain.InvoiceLine;
import net.hectorm.springbootdatajpa.domain.Product;
import net.hectorm.springbootdatajpa.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private final IClientService clientService;

    @Autowired
    public InvoiceController(@Qualifier("repositoryDataJpa") IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/form/{clientId}")
    public String showInvoiceForm(Model model, @PathVariable Long clientId, RedirectAttributes attributes){

        Client client = clientService.findById(clientId);
        if(client == null){
            attributes.addFlashAttribute("error", "The client was not found in thr DataBase");
            return "redirect:/list";
        }
        Invoice invoice = new Invoice();
        invoice.setClient(client);

        model.addAttribute("title", "New invoice creation form");
        model.addAttribute("invoice", invoice);
        return "invoice/invoice_form";
    }

    @GetMapping(value = "/load-products/{criteria}", produces = {"application/json"})
    public @ResponseBody Set<Product> loadProducts(@PathVariable String criteria){
        return clientService.findByNameLikeIgnoreCase(criteria);
    }

    @PostMapping("/form")
    public String saveInvoice(@Valid Invoice invoice, BindingResult result, Model model, RedirectAttributes flash,
                              @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                              @RequestParam(name = "quantity[]", required = false) Integer[] quantity) {

        if(result.hasErrors()){
            model.addAttribute("title", "New invoice creation form");
            return "invoice/invoice_form";
        }
        if(itemId == null || itemId.length == 0){
            model.addAttribute("title", "New invoice creation form");
            model.addAttribute("error", "The invoice need to have at least one Line");
            return "invoice/invoice_form";
        }
        for(int i = 0; i<itemId.length;i++){
            Product product = clientService.findProductById(itemId[i]);
            InvoiceLine invoiceLine = new InvoiceLine();
            invoiceLine.setProduct(product);
            invoiceLine.setQuantity(quantity[i]);
            invoice.addInvoiceLine(invoiceLine);
        }

        clientService.saveInvoice(invoice);
        flash.addFlashAttribute("success", "The invoice was successful saved");

        return "redirect:/view/" + invoice.getClient().getId();
    }

    @GetMapping("/view/{id}")
    public String invoiceDetail(@PathVariable Long id, Model model, RedirectAttributes flash){
        Invoice invoice = clientService.findInvoiceById(id);
        if(invoice == null){
            flash.addFlashAttribute("error", "The invoice was not found");
            return "redirect:/list";
        }
        model.addAttribute("invoice", invoice);
        model.addAttribute("title", "Invoice : " .concat(invoice.getDescription()));

        return "invoice/view";
    }


    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable Long id, Model model, RedirectAttributes flash){
        Invoice invoice = null;
        if(id>0) {
             invoice = clientService.findInvoiceById(id);
             if(invoice == null){
                 flash.addFlashAttribute("error", "The Invoice was not found in the DB");
                 return "redirect:/list";
             }else{
                 clientService.deleteInvoiceById(id);
                 flash.addFlashAttribute("success", "The Invoice was successful delete");
             }

        } else{
            flash.addFlashAttribute("error", "The ID value can't be Zero or negative");
            return "redirect:/list";
        }


        return "redirect:/view/" + invoice.getClient().getId();
    }


}
