package edu.hm.cs.fs.scriptinat0r7.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hm.cs.fs.scriptinat0r7.controller.AbstractController;
import edu.hm.cs.fs.scriptinat0r7.model.StudentOrder;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptDocumentService;
import edu.hm.cs.fs.scriptinat0r7.service.StudentOrderService;

@Controller
@RequestMapping("/manage-orders")
@Secured("ROLE_FACHSCHAFTLER")
public class OrdersManagementController extends AbstractController {

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private ScriptDocumentService scriptDocumentService;

    @RequestMapping()
    public String index(final ModelMap model) {
        final Collection<StudentOrder> ordersNotTransmittedToCopyshop = studentOrderService.findOrdersNotTransmittedToCopyshop();

        for (final StudentOrder order : ordersNotTransmittedToCopyshop) {
            order.setScriptDocuments(scriptDocumentService.findByOrder(order));
        }

        model.addAttribute("ordersNotAtCopyShop", ordersNotTransmittedToCopyshop);
        return "manage-orders/index";
    }

}
