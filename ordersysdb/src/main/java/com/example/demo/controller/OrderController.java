package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderDetailPname;
import com.example.demo.repository.OrderDetailPnameRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class OrderController {
	private final OrderRepository orderReposotroy;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderDetailPnameRepository orderDetailPnameRepository;

	@Transactional // メソッド終了時にコミット
	@GetMapping("/orderTest")
	public ModelAndView showProductList(ModelAndView mv) {
		Order order = new Order();
		order.setEid("atsuki");
		order.setOrderdate(LocalDate.now());
		order.setOrderamt(7800);
		orderReposotroy.save(order); // save メソッドの実行後ではなくコミットされたときに DB へ反映
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setOrderid(order.getOrderid()); // 採番された orderid が入る
		orderDetail1.setPid("0029");
		orderDetail1.setAmount(2);
		orderDetailRepository.save(orderDetail1);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setOrderid(order.getOrderid()); // 採番された orderid が入る
		orderDetail2.setPid("0030");
		orderDetail2.setAmount(1);
		orderDetailRepository.save(orderDetail2);
		mv.setViewName("orderComplete");
		return mv;
	}

	@GetMapping("/orderDetailList/{orderid}")
	public ModelAndView showOrderDetailList(
			ModelAndView mv, @PathVariable("orderid") Integer orderid) {
		List<OrderDetailPname> list = orderDetailPnameRepository.findAllByOrderId(orderid);
		mv.addObject("orderDetailList", list);
		mv.setViewName("orderDetailList");
		return mv;
	}

}