package net.imad.sdiaspringmvc.web;

import jakarta.validation.Valid;
import net.imad.sdiaspringmvc.entities.Product;
import net.imad.sdiaspringmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productRepository.findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products); // <-- Correction: inject as 'products' for template compatibility
        model.addAttribute("productList", products); // (optional: keep for backward compatibility)
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @PostMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/newProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("/admin/saveProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) return "new-product";
        Product savedProduct = productRepository.save(product);
        redirectAttributes.addFlashAttribute("success", "✔ Produit ajouté avec succès !");
        redirectAttributes.addFlashAttribute("highlightId", savedProduct.getId());
        return "redirect:/user/index";
    }

    @GetMapping("/admin/editProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/admin/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateProduct(@Valid Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return "edit-product";
        }
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("success", "Produit modifié avec succès ✅");
        return "redirect:/user/index";
    }

    @GetMapping("/notAuthenticated")
    public String notAuthenticatedPage() {
        return "notAuthenticated";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout-success")
    public String logoutSuccessPage() {
        return "logout-success";
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String dashboard(Model model) {
        List<Product> products = productRepository.findAll();
        int totalProducts = products.size();
        double totalStockValue = products.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
        Product mostExpensive = products.stream().max(Comparator.comparing(Product::getPrice)).orElse(null);
        Product mostInStock = products.stream().max(Comparator.comparing(Product::getQuantity)).orElse(null);
        // Préparer les listes pour Chart.js
        List<String> productNames = products.stream().map(Product::getName).collect(Collectors.toList());
        List<Double> productPrices = products.stream().map(Product::getPrice).collect(Collectors.toList());
        List<Double> productQuantities = products.stream().map(Product::getQuantity).collect(Collectors.toList());
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalStockValue", totalStockValue);
        model.addAttribute("mostExpensive", mostExpensive);
        model.addAttribute("mostInStock", mostInStock);
        model.addAttribute("products", products);
        model.addAttribute("productNames", productNames);
        model.addAttribute("productPrices", productPrices);
        model.addAttribute("productQuantities", productQuantities);
        return "dashboard";
    }
}
