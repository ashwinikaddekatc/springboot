  function CopyAbove() { 
 var GetCurrentRow = $("#RowForData").val(); 
 var PreviousRow = parseInt(GetCurrentRow) - 1; 
var clsSal = $(".clsSal" + PreviousRow).val()
 
 
$(".clsSal" + GetCurrentRow).val(clsSal)   
}
 function SelectedRowData(rowNo) { 
$("#RowForData").val(rowNo)
}
 function ClearLines() { 
var GetCurrentRow = $("#RowForData").val();
  $(".clsSal" + GetCurrentRow).val('');
}

