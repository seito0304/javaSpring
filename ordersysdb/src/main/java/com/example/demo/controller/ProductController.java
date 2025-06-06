package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Product;
import com.example.demo.form.ProductInputForm;
import com.example.demo.form.ProductUpdateForm;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ProductController {
	private final ProductRepository productRepository;
	private final ProductService productService;

	@GetMapping("/productList")
	public ModelAndView showProductList(ModelAndView mv) {
		List<Product> productList = productRepository.findAllByOrderByPriceDescPid();
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}

	@GetMapping("/productListfindByPriceAndStock")
	public ModelAndView showProductFindBySctokAndPrice(
			@RequestParam("price") Integer price,
			@RequestParam("stock") Integer stock,
			ModelAndView mv) {
		List<Product> productList = productRepository.findByPriceAndStock(price, stock);
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}

	@GetMapping("/productInputForm")
	public String showForm(ProductInputForm productInputForm) {
		return "productInputForm";
	}

	@PostMapping("/productRegist")
	public String regist(@ModelAttribute @Validated ProductInputForm productInputForm,
			BindingResult result) {
		if (!result.hasErrors()) {
			Product product = productInputForm.getEntity();
			productRepository.saveAndFlush(product);
			return "redirect:/productList";
		} else {
			return "productInputForm";
		}
	}

	@GetMapping("/productUpdateForm")
	public String showForm(ProductUpdateForm productUpdateForm) {
		return "productUpdateForm";
	}

	@PostMapping("/productUpdate")
	public String update(@ModelAttribute @Validated ProductUpdateForm productUpdateForm, BindingResult result) {
		Product originalProduct = productService.getByPid(productUpdateForm, result);
		if (!result.hasErrors()) {
			Product newProduct = productUpdateForm.getEntity();
			newProduct.setPname(originalProduct.getPname());
			newProduct.setCategory(originalProduct.getCategory());
			productRepository.saveAndFlush(newProduct);
			return "redirect:/productList";
		} else {
			return "productUpdateForm";
		}
	}
}