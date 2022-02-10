package com.realnet.comm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realnet.comm.entity.Sales;

public interface SaleServiceInterface {
	public Page<Sales> getAll(Pageable page);
	public Sales createsale(Sales data);
}
