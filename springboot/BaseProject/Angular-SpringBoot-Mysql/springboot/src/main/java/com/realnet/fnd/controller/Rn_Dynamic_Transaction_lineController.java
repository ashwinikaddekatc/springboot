package com.realnet.fnd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.exceptions.ResourceNotFoundException;
import com.realnet.fnd.entity.Rn_Dynamic_Transaction;
import com.realnet.fnd.entity.Rn_Dynamic_Transaction_line;
import com.realnet.fnd.response.Rn_DynamicTransactionResponse;
import com.realnet.fnd.response.Rn_Dynamic_Transaction_LineResponce;
import com.realnet.fnd.service.Rn_DynamicTransactionService;
import com.realnet.fnd.service.Rn_Dynamic_Transaction_LineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Rn_Dynamic_Transaction_line" })
public class Rn_Dynamic_Transaction_lineController {


	@Autowired
	private Rn_Dynamic_Transaction_LineService rn_dynamic_Transaction_LineService;
	

	// GET ALL
	@ApiOperation(value = "List of Dynamic Line Transaction", response = Rn_Dynamic_Transaction_LineResponce.class)
	@GetMapping("/dynamic_transaction_line/all")
	public Rn_Dynamic_Transaction_LineResponce getDynamicTx(
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size) {

		Rn_Dynamic_Transaction_LineResponce resp = new Rn_Dynamic_Transaction_LineResponce();
		Pageable paging = PageRequest.of(page, size);
		Page<Rn_Dynamic_Transaction_line> result = rn_dynamic_Transaction_LineService.getAll(paging);
		resp.setPageStats(result, true);
		resp.setItems(result.getContent());
		return resp;
	}
	
	// GET BY FROM ID (GRID)
		@ApiOperation(value = "Get A Dynamic Line Transaction")
		@GetMapping("/dynamic_transaction_line")
		public ResponseEntity<List<Rn_Dynamic_Transaction_line>> getDynamicTxByFormId(
				@RequestParam(value = "form_id") int form_id) {
			List<Rn_Dynamic_Transaction_line> rn_dynamic_transaction = rn_dynamic_Transaction_LineService.getByFormId(form_id);
			return ResponseEntity.ok().body(rn_dynamic_transaction);
		}
		
		// SAVE
		@ApiOperation(value = "Add A New Dynamic Line Transaction", response = Rn_Dynamic_Transaction_LineResponce.class)
		@PostMapping("/dynamic_transaction_line")
		public ResponseEntity<List<Rn_Dynamic_Transaction_line>> createDynamicTx(
				@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
				@Valid @RequestBody List<Rn_Dynamic_Transaction_line> rn_dynamic_transaction) {
//			String userId = tokenUtil.getUserId(authToken);
//			rn_dynamic_transaction.setCreatedBy(userId);
//			rn_dynamic_transaction.setAccountId(userId);
			List<Rn_Dynamic_Transaction_line> savedRn_Dynamic_Transaction = rn_dynamic_Transaction_LineService.save(rn_dynamic_transaction);
			if (savedRn_Dynamic_Transaction == null) {
				throw new ResourceNotFoundException("Dynamic Transaction Is Not Saved");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Dynamic_Transaction);
		}
		// GET BY ID
		@ApiOperation(value = "Get a Single line Form", response = Rn_Dynamic_Transaction_line.class)
		@GetMapping("/dynamic_transaction_line/{id}")
		public ResponseEntity<Rn_Dynamic_Transaction_line> getById(@PathVariable(value = "id") int id,
				@RequestParam(value = "form_id") int form_id) {
			Rn_Dynamic_Transaction_line dynamicTransaction = rn_dynamic_Transaction_LineService.getByIdAndFormId(id, form_id);
			return ResponseEntity.ok().body(dynamicTransaction);
		}
		
		// UPDATE
		@ApiOperation(value = "Update A Dynamic Line Transaction", response = Rn_Dynamic_Transaction_line.class)
		@PutMapping("/dynamic_transaction_line/{id}")
		public ResponseEntity<Rn_Dynamic_Transaction_line> updateDynamicTx(
				@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
				@PathVariable(value = "id") int id, @RequestParam(value = "form_id") int form_id,
				@Valid @RequestBody Rn_Dynamic_Transaction_line rn_dynamic_transaction) {
//			String userId = tokenUtil.getUserId(authToken);
//			rn_dynamic_transaction.setUpdatedBy(userId);
			Rn_Dynamic_Transaction_line updatedDynamicTransaction = rn_dynamic_Transaction_LineService.updateByFormId(id, form_id,
					rn_dynamic_transaction);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDynamicTransaction);
		}
		
		// DELETE
		@DeleteMapping("/dynamic_transaction_line/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteDynamicTx(@PathVariable(value = "id") int id) {
			boolean deleted = rn_dynamic_Transaction_LineService.deleteById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			// response.put("deleted", deleted);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

}
