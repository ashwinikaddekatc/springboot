function SalesOrderClearLines() {
    
    var GetCurrentRow = $("#RowForData").val();
    //alert("copy");
//    document.getElementById("so_Header_Id" + GetCurrentRow).value = '';
//    document.getElementById("sales_order_id" + GetCurrentRow).value = '';
//   
    $(".clsUOM" + GetCurrentRow).val('');
    $(".saveso_line_type" + GetCurrentRow).val('');
    $(".saveItem_Id" + GetCurrentRow).val('');
    $(".items" + GetCurrentRow).val('');
    $(".descrptionToSave" + GetCurrentRow).val('');
    
    $(".updateGRNLine" + GetCurrentRow).val('');
    $(".unitPriceToSave" + GetCurrentRow).val('');
    $(".quantityToSave" + GetCurrentRow).val('');
    $(".clsUnitSellingPriceToSave" + GetCurrentRow).val('');
    $(".saveso_line_total" + GetCurrentRow).val('');

    $(".saveso_line_tax" + GetCurrentRow).val('');
    $(".saveso_line_discount" + GetCurrentRow).val('');
    $(".saveso_line_charges" + GetCurrentRow).val('');
    $(".saveso_total" + GetCurrentRow).val('');
    $(".rowDate1" + GetCurrentRow).val('');

    //document.getElementById("saveso_line_scheduled_ship_date " + GetCurrentRow).value = '';
      
    $(".rowDate4" + GetCurrentRow).val('');      
    $(".clsprzList" + GetCurrentRow).val ('');
    $(".saveso_line_status" + GetCurrentRow).val('');
    $(".saveso_line_next_action" + GetCurrentRow).val('');
    $(".clssaveso_line_ship_to" + GetCurrentRow).val('');
    
    $(".clssaveso_line_bill_to" + GetCurrentRow).val('');
    $(".clssaveso_line_customer_po" + GetCurrentRow).val('');
    $(".saveso_line_cust_item_no" + GetCurrentRow).val('');
   // 2  $(".clsFinalPrice" + GetCurrentRow).val('');
    $(".clssaveso_line_edi_location" + GetCurrentRow).val('');
    $(".clsPaymentTerms" + GetCurrentRow).val('');
   
    $(".clssaveso_line_freight_terms" + GetCurrentRow).val('');
    $(".saveso_line_shipping_instructions" + GetCurrentRow).val('');
    $(".saveso_line_packing_instructions" + GetCurrentRow).val('');
    $(".saveW" + GetCurrentRow).val('');
    $(".saveso_line_subinventory" + GetCurrentRow).val('');
    
    $(".saveso_line_locator" + GetCurrentRow).val('');
    $(".saveso_line_so_no" + GetCurrentRow).val('');
    $(".saveso_line_bsa_no" + GetCurrentRow).val('');
    $(".rowNum" + GetCurrentRow).val('');
    $(".Saveso_line_tax_group" + GetCurrentRow).val('');

    $(".Saveso_line_charges_group" + GetCurrentRow).val('');
    $(".Saveso_line_shipdependent" + GetCurrentRow).val('');
    $(".Saveso_line_shippedqty" + GetCurrentRow).val('');
    $(".saveLineType" + GetCurrentRow).val('');
    $(".saveIsExist" + GetCurrentRow).val('');
    //   6 Saveso_line_charges_group    7    Saveso_line_shipdependent 8 Saveso_line_shippedqty
    //    $(".rowNumChar15" + GetCurrentRow).val('');
//    $(".savePostalCode" + GetCurrentRow).val('');
    
    // document.getElementById("srNo " + GetCurrentRow).value = '';

    var so_Header_Id = "";
    var sales_order_id = "";
    
    var clsUOM = "";
    var saveso_line_type = "";
    var saveItem_Id = "";
    var items = "";
    var descrptionToSave = "";

    var updateGRNLine = "";
    var unitPriceToSave = "";
    var quantityToSave = "";
    var clsUnitSellingPriceToSave = "";
    var saveso_line_total = "";

    var saveso_line_tax = "";
    var saveso_line_discount = "";
    var saveso_line_charges = "";
    var saveso_total = "";
    var rowDate1 = "";
    
    var rowDate2 = "";
    
    var rowDate4 = "";
    var clsprzList = "";
    var saveso_line_status = "";
    var saveso_line_next_action = "";
    var clssaveso_line_ship_to = "";

    var clssaveso_line_bill_to = "";
    var clssaveso_line_customer_po = "";
    //var clsFinalPrice = "";
    var clssaveso_line_edi_location = "";
    var clsPaymentTerms = "";

    var saveso_line_cust_item_no = "";
    var saveso_line_shipping_instructions = "";
    var Saveso_line_charges_group = "";
    var Saveso_line_shipdependent = "";
    var Saveso_line_shippedqty = "";

    var clssaveso_line_freight_terms = "";
//    var clsFinalPrice = "";
    var saveso_line_packing_instructions = "";
    var saveW = "";
    var saveso_line_subinventory = "";

    var saveso_line_locator = "";
    var saveso_line_so_no = "";
    var saveso_line_bsa_no = "";
    var rowNum = "";
    var Saveso_line_tax_group = "";
    
//    var saveW = "";
    var saveLineType = "";
    var saveIsExist = "";
//    var rowNumChar15 = "";
//    var savePostalCode = "";

    //var srNo = "";
   
}

function SalesOrderCopyAbove() {

    
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    //alert("copy");
//    var so_Header_Id = document.getElementById('so_Header_Id' + PreviousRow).value;
//    var sales_order_id = document.getElementById('sales_order_id' + PreviousRow).value;
//   
    var clsUOM = $(".clsUOM" + PreviousRow).val();
    var saveso_line_type = $(".saveso_line_type" + PreviousRow).val();
    var saveItem_Id = $(".saveItem_Id" + PreviousRow).val();
    var items = $(".items" + PreviousRow).val();
    var descrptionToSave = $(".descrptionToSave" + PreviousRow).val();

    var updateGRNLine = $(".updateGRNLine" + PreviousRow).val();
    var unitPriceToSave = $(".unitPriceToSave" + PreviousRow).val();
    var quantityToSave = $(".quantityToSave" + PreviousRow).val();
    var clsUnitSellingPriceToSave = $(".clsUnitSellingPriceToSave" + PreviousRow).val();
    var saveso_line_total = $(".saveso_line_total" + PreviousRow).val();

    var saveso_line_tax = $(".saveso_line_tax" + PreviousRow).val();
    var saveso_line_discount = $(".saveso_line_discount" + PreviousRow).val();
    var saveso_line_charges = $(".saveso_line_charges" + PreviousRow).val();
    var saveso_total = $(".saveso_total" + PreviousRow).val();
    var rowDate1 = $(".rowDate1" + PreviousRow).val();

//    var rowDate2 = document.getElementById('rowDate 2 ' + PreviousRow).value;
   
    var rowDate4 = $(".rowDate4" + PreviousRow).val();
    var clsprzList = $(".clsprzList" + PreviousRow).val();
    var saveso_line_status = $(".saveso_line_status" + PreviousRow).val();
    var saveso_line_next_action = $(".saveso_line_next_action" + PreviousRow).val();
    var clssaveso_line_ship_to = $(".clssaveso_line_ship_to" + PreviousRow).val();

    var clssaveso_line_bill_to = $(".clssaveso_line_bill_to" + PreviousRow).val();
    var clssaveso_line_customer_po = $(".clssaveso_line_customer_po" + PreviousRow).val();
    //var clsFinalPrice = $(".clsFinalPrice" + PreviousRow).val();
    var clssaveso_line_edi_location = $(".clssaveso_line_edi_location" + PreviousRow).val();
    var clsPaymentTerms = $(".clsPaymentTerms" + PreviousRow).val();

    var clssaveso_line_freight_terms = $(".clssaveso_line_freight_terms" + PreviousRow).val();
//    var clsFinalPrice = $(".clsFinalPrice" + PreviousRow).val();
    var saveso_line_packing_instructions = $(".saveso_line_packing_instructions" + PreviousRow).val();
    var saveW = $(".saveW" + PreviousRow).val();
    var saveso_line_subinventory = $(".saveso_line_subinventory" + PreviousRow).val();

    var saveso_line_cust_item_no = $(".saveso_line_cust_item_no" + PreviousRow).val();
    var saveW = $(".saveso_line_shipping_instructions" + PreviousRow).val();
    var saveW = $(".Saveso_line_charges_group" + PreviousRow).val();
    var saveW = $(".Saveso_line_shipdependent" + PreviousRow).val();
    var saveW = $(".Saveso_line_shippedqty" + PreviousRow).val();

    var saveso_line_locator = $(".saveso_line_locator" + PreviousRow).val();
    var saveso_line_so_no = $(".saveso_line_so_no" + PreviousRow).val();
    var saveso_line_bsa_no = $(".saveso_line_bsa_no" + PreviousRow).val();
    var rowNum = $(".rowNum" + PreviousRow).val();
    var Saveso_line_tax_group = $(".Saveso_line_tax_group" + PreviousRow).val();

//    var saveW = $(".saveW" + PreviousRow).val();
    var saveLineType = $(".saveLineType" + PreviousRow).val();
    var saveIsExist = $(".saveIsExist" + PreviousRow).val();
//    var rowNumChar15 = $(".rowNumChar15" + PreviousRow).val();
//    var savePostalCode = $(".savePostalCode" + PreviousRow).val();
    //var srNo = document.getElementById('srNo' + PreviousRow).value;

//    document.getElementById("so_Header_Id" + GetCurrentRow).value = so_Header_Id;
//    document.getElementById("sales_order_id" + GetCurrentRow).value = sales_order_id;
   
    $(".clsUOM" + GetCurrentRow).val(clsUOM);
    $(".saveso_line_type" + GetCurrentRow).val(saveso_line_type);
    $(".saveItem_Id" + GetCurrentRow).val(saveItem_Id);
    $(".items" + GetCurrentRow).val(items);
    $(".descrptionToSave" + GetCurrentRow).val(descrptionToSave);

    $(".updateGRNLine" + GetCurrentRow).val(updateGRNLine);
    $(".unitPriceToSave" + GetCurrentRow).val(unitPriceToSave);
    $(".quantityToSave" + GetCurrentRow).val(quantityToSave);
    $(".clsUnitSellingPriceToSave" + GetCurrentRow).val(clsUnitSellingPriceToSave);
    $(".saveso_line_total" + GetCurrentRow).val(saveso_line_total);

    $(".saveso_line_tax" + GetCurrentRow).val(saveso_line_tax);
    $(".saveso_line_discount" + GetCurrentRow).val(saveso_line_discount);
    $(".saveso_line_charges" + GetCurrentRow).val(saveso_line_charges);
    $(".saveso_total" + GetCurrentRow).val(saveso_total);
    $(".rowDate1" + GetCurrentRow).val(rowDate1);

//    document.getElementById("rowDate 2 " + GetCurrentRow).value = rowDate2;
   
    $(".rowDate4" + GetCurrentRow).val(rowDate4);
    $(".clsprzList" + GetCurrentRow).val(clsprzList);
    $(".saveso_line_status" + GetCurrentRow).val(saveso_line_status);
    $(".saveso_line_next_action" + GetCurrentRow).val(saveso_line_next_action);
    $(".clssaveso_line_ship_to" + GetCurrentRow).val(clssaveso_line_ship_to);

    $(".clssaveso_line_bill_to" + GetCurrentRow).val(clssaveso_line_bill_to);
    $(".clssaveso_line_customer_po" + GetCurrentRow).val(clssaveso_line_customer_po);
    $(".saveso_line_cust_item_no" + GetCurrentRow).val(saveso_line_cust_item_no);
    $(".clssaveso_line_edi_location" + GetCurrentRow).val(clssaveso_line_edi_location);
    $(".clsPaymentTerms" + GetCurrentRow).val(clsPaymentTerms);

    $(".clssaveso_line_freight_terms" + GetCurrentRow).val(clssaveso_line_freight_terms);
    $(".saveso_line_shipping_instructions" + GetCurrentRow).val(saveso_line_shipping_instructions);
    $(".saveso_line_packing_instructions" + GetCurrentRow).val(saveso_line_packing_instructions);
    $(".saveW" + GetCurrentRow).val(saveW);
    $(".saveso_line_subinventory" + GetCurrentRow).val(saveso_line_subinventory);


    $(".Saveso_line_charges_group" + GetCurrentRow).val(Saveso_line_charges_group);
    $(".Saveso_line_shipdependent" + GetCurrentRow).val(Saveso_line_shipdependent);
    $(".Saveso_line_shippedqty" + GetCurrentRow).val(Saveso_line_shippedqty);
    //$(".saveW" + GetCurrentRow).val(saveW);
   
    $(".saveso_line_locator" + GetCurrentRow).val(saveso_line_locator);
    $(".saveso_line_so_no" + GetCurrentRow).val(saveso_line_so_no);
    $(".saveso_line_bsa_no" + GetCurrentRow).val(saveso_line_bsa_no);
    $(".rowNum" + GetCurrentRow).val(rowNum);
    $(".Saveso_line_tax_group" + GetCurrentRow).val(Saveso_line_tax_group);

   // $(".saveW" + GetCurrentRow).val(saveW);
    $(".saveLineType" + GetCurrentRow).val(saveLineType);
    $(".saveIsExist" + GetCurrentRow).val(saveIsExist);
//    $(".rowNumChar15" + GetCurrentRow).val(rowNumChar15);
//    $(".savePostalCode" + GetCurrentRow).val(savePostalCode);
//    // document.getElementById("srNo " + GetCurrentRow).value = srNo;
    
}

function SalesOrderSelectedRowData(rowNo) {
    
    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}