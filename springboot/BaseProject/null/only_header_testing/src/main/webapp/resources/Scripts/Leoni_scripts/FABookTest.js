  function CopyAbove() { 
 var GetCurrentRow = $("#RowForData").val(); 
 var PreviousRow = parseInt(GetCurrentRow) - 1; 
var clsbook_last_run_date = $(".clsbook_last_run_date" + PreviousRow).val()
var clsbook_status = $(".clsbook_status" + PreviousRow).val()
var clsbook_requeast_status = $(".clsbook_requeast_status" + PreviousRow).val()
var clsbook_request_phase = $(".clsbook_request_phase" + PreviousRow).val()
 
 
$(".clsbook_last_run_date" + GetCurrentRow).val(clsbook_last_run_date)   
$(".clsbook_status" + GetCurrentRow).val(clsbook_status)   
$(".clsbook_requeast_status" + GetCurrentRow).val(clsbook_requeast_status)   
$(".clsbook_request_phase" + GetCurrentRow).val(clsbook_request_phase)   
}
 function SelectedRowData(rowNo) { 
$("#RowForData").val(rowNo)
}
 function ClearLines() { 
var GetCurrentRow = $("#RowForData").val();
  $(".clsbook_last_run_date" + GetCurrentRow).val('');
  $(".clsbook_status" + GetCurrentRow).val('');
  $(".clsbook_requeast_status" + GetCurrentRow).val('');
  $(".clsbook_request_phase" + GetCurrentRow).val('');
}

