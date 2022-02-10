  function CopyAbove() { 
 var GetCurrentRow = $("#RowForData").val(); 
 var PreviousRow = parseInt(GetCurrentRow) - 1; 
var clsprice_list_name = $(".clsprice_list_name" + PreviousRow).val()
var clsprice_list_lines_line_item_id = $(".clsprice_list_lines_line_item_id" + PreviousRow).val()
var clsprice_list_lines_item_code = $(".clsprice_list_lines_item_code" + PreviousRow).val()
var clsprice_list_lines_item_description = $(".clsprice_list_lines_item_description" + PreviousRow).val()
var clsprice_list_lines_description = $(".clsprice_list_lines_description" + PreviousRow).val()
var clsprice_list_lines_uom = $(".clsprice_list_lines_uom" + PreviousRow).val()
var clsprice_list_lines_price = $(".clsprice_list_lines_price" + PreviousRow).val()
 
 
$(".clsprice_list_name" + GetCurrentRow).val(clsprice_list_name)   
$(".clsprice_list_lines_line_item_id" + GetCurrentRow).val(clsprice_list_lines_line_item_id)   
$(".clsprice_list_lines_item_code" + GetCurrentRow).val(clsprice_list_lines_item_code)   
$(".clsprice_list_lines_item_description" + GetCurrentRow).val(clsprice_list_lines_item_description)   
$(".clsprice_list_lines_description" + GetCurrentRow).val(clsprice_list_lines_description)   
$(".clsprice_list_lines_uom" + GetCurrentRow).val(clsprice_list_lines_uom)   
$(".clsprice_list_lines_price" + GetCurrentRow).val(clsprice_list_lines_price)   
}
 function SelectedRowData(rowNo) { 
$("#RowForData").val(rowNo)
}
 function ClearLines() { 
var GetCurrentRow = $("#RowForData").val();
  $(".clsprice_list_name" + GetCurrentRow).val('');
  $(".clsprice_list_lines_line_item_id" + GetCurrentRow).val('');
  $(".clsprice_list_lines_item_code" + GetCurrentRow).val('');
  $(".clsprice_list_lines_item_description" + GetCurrentRow).val('');
  $(".clsprice_list_lines_description" + GetCurrentRow).val('');
  $(".clsprice_list_lines_uom" + GetCurrentRow).val('');
  $(".clsprice_list_lines_price" + GetCurrentRow).val('');
}

