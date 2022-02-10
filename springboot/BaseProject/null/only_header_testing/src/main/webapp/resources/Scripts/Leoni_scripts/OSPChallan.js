function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

   // document.getElementById("Save_fin_date " + GetCurrentRow).value = '';

    $(".clsch_item_id" + GetCurrentRow).val('');
    $(".clspo_header_id" + GetCurrentRow).val('');
    $(".clspo_line_id" + GetCurrentRow).val('');
    $(".clspo_number" + GetCurrentRow).val('');
    $(".clsch_item_name" + GetCurrentRow).val('');
    $(".clsch_service" + GetCurrentRow).val('');
    $(".clsch_qty" + GetCurrentRow).val('');
    $(".clsch_uom" + GetCurrentRow).val('');
    $(".clsch_item_price" + GetCurrentRow).val('');
    $(".clsch_value" + GetCurrentRow).val('');
    $(".clsch_tariff_no" + GetCurrentRow).val('');
    $(".clsch_in_sub_inv" + GetCurrentRow).val('');
    

    var clsch_item_id = "";
    var clspo_header_id = "";
    var clspo_line_id = ""
    var clspo_number = "";
    var clsch_item_name = ""
    var clsch_service = "";
    var clsch_qty = "";
    var clsch_uom = ""
    var clsch_item_price = "";
    var clsch_value = "";
    var clsch_tariff_no = ""
    var clsch_in_sub_inv = "";    

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;


    var clsch_item_id = $(".clsch_item_id" + PreviousRow).val();
    var clspo_header_id = $(".clspo_header_id" + PreviousRow).val();
    var clspo_line_id = $(".clspo_line_id" + PreviousRow).val();
    var clspo_number = $(".clspo_number" + PreviousRow).val();
    var clsch_item_name = $(".clsch_item_name" + PreviousRow).val();
    var clsch_service = $(".clsch_service" + PreviousRow).val();
    var clsch_qty = $(".clsch_qty" + PreviousRow).val();
    var clsch_uom = $(".clsch_uom" + PreviousRow).val();
    var clsch_item_price = $(".clsch_item_price" + PreviousRow).val();
    var clsch_value = $(".clsch_value" + PreviousRow).val();
    var clsch_tariff_no = $(".clsch_tariff_no" + PreviousRow).val();
    var clsch_in_sub_inv = $(".clsch_in_sub_inv" + PreviousRow).val();

    $(".clsch_item_id" + GetCurrentRow).val(clsch_item_id);
    $(".clspo_header_id" + GetCurrentRow).val(clspo_header_id);
    $(".clspo_line_id" + GetCurrentRow).val(clspo_line_id);
    $(".clspo_number" + GetCurrentRow).val(clspo_number);
    $(".clsch_item_name" + GetCurrentRow).val(clsch_item_name);
    $(".clsch_service" + GetCurrentRow).val(clsch_service);
    $(".clsch_qty" + GetCurrentRow).val(clsch_qty);
    $(".clsch_uom" + GetCurrentRow).val(clsch_uom);
    $(".clsch_item_price" + GetCurrentRow).val(clsch_item_price);
    $(".clsch_value" + GetCurrentRow).val(clsch_value);
    $(".clsch_tariff_no" + GetCurrentRow).val(clsch_tariff_no);
    $(".clsch_in_sub_inv" + GetCurrentRow).val(clsch_in_sub_inv);
   

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}