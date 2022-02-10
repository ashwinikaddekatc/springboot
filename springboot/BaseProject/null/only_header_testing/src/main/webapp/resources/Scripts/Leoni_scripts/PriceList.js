function PriceListClearLines() {
    var GetCurrentRow = $("#RowForData").val();
    
    //$(".sr_no" + GetCurrentRow).val('');
    $(".price_list_lines_item_code" + GetCurrentRow).val('');
    $(".price_list_lines_item_description" + GetCurrentRow).val('');
    $(".price_list_lines_line_item_id" + GetCurrentRow).val('');
    $(".save_uom" + GetCurrentRow).val('');

    $(".save_price" + GetCurrentRow).val('');
   
    document.getElementById("save_start_date " + GetCurrentRow).value = '';
    document.getElementById("save_end_date " + GetCurrentRow).value = '';

    //var sr_no = "";
    var price_list_lines_item_code = "";
    var price_list_lines_item_description = ""
    var price_list_lines_line_item_id = "";
    var save_uom = "";

    var save_price = "";
   
    var save_start_date = "";
    var save_end_date = "";

}

function PriceListCopyAbove() {
    
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    //var sr_no = $(".sr_no" + PreviousRow).val();
    var price_list_lines_item_code = $(".price_list_lines_item_code" + PreviousRow).val();
    var price_list_lines_item_description = $(".price_list_lines_item_description" + PreviousRow).val();
    var price_list_lines_line_item_id = $(".price_list_lines_line_item_id" + PreviousRow).val();
    var save_uom = $(".save_uom" + PreviousRow).val();

    var save_price = $(".save_price" + PreviousRow).val();
  
    var save_start_date = document.getElementById('save_start_date ' + PreviousRow).value;
    var save_end_date = document.getElementById('save_end_date ' + PreviousRow).value;
    //alert(save_start_date);
    //$(".sr_no" + GetCurrentRow).val(sr_no);
    $(".price_list_lines_item_code" + GetCurrentRow).val(price_list_lines_item_code);
    $(".price_list_lines_item_description" + GetCurrentRow).val(price_list_lines_item_description);
    $(".price_list_lines_line_item_id" + GetCurrentRow).val(price_list_lines_line_item_id);
    $(".save_uom" + GetCurrentRow).val(save_uom);

    $(".save_price" + GetCurrentRow).val(save_price);
  
    document.getElementById("save_start_date " + GetCurrentRow).value = save_start_date;
    document.getElementById("save_end_date " + GetCurrentRow).value = save_end_date;

}

function PriceListSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}