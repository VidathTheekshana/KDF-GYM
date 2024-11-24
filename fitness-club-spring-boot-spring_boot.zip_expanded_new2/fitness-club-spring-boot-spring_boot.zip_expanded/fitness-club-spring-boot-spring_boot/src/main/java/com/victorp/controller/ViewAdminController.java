package com.victorp.controller;

import com.victorp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewAdminController {

    private AdminService adminService;

    @Autowired
    public ViewAdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/adminView")
    public String viewTrainer(Model model, String keyword) throws Exception{

        if(keyword != null){
            model.addAttribute("allAdmins", adminService.findAdminByKeyword(keyword));
        }else {
            model.addAttribute("allAdmins", adminService.getAll());
        }


        return "adminView";

    }

    @PostMapping("/adminView")
    public String deleteAdmin(@RequestParam Long idAdmin)throws Exception{

        adminService.delete(idAdmin);

        return "redirect:/";

    }
}
