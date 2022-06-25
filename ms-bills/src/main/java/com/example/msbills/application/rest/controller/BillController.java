package com.example.msbills.application.rest.controller;

import com.example.msbills.domain.models.Bill;
import com.example.msbills.infrastructure.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/home")
    public String getHome(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("webpageTitle", "Bienvenido a mi sitio (style-1993)");

        String idToken = principal.getIdToken().getTokenValue();
        model.addAttribute("idToken", idToken);

        String name = principal.getGivenName() + " " + principal.getFamilyName();
        model.addAttribute("fullName", name);

        return "home";
    }

    @GetMapping("/public")
    public String getPublicHome() {
        return "public";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @GetMapping("/test")
    public String getMessage() {
        return "El servicio funciona...";
    }

}
