package com.eShoppingCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eShoppingCart.model.Cart;
import com.eShoppingCart.model.Customer;
import com.eShoppingCart.service.CustomerService;

@Controller
@RequestMapping("/customer/cart")
@SessionAttributes("cart")
public class CartController {
	
	@ModelAttribute("cart")
	public Cart populateForm() {
	    return new Cart();
	}

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping
	public String getCart(@AuthenticationPrincipal User activeUser){
		
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
		
		int cartId = customer.getCart().getCartId();
		
		return "redirect:/customer/cart/" + cartId;
	}
	
	@RequestMapping("/{cartId}")
	public String getCartRedirect(@ModelAttribute("cart") Cart cart,@PathVariable(value="cartId") int cartId, Model model){
		model.addAttribute("cartId", cart.getCartId() );
		
		return "cart";
		
	}
}
