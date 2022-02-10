function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

  
    $(".clsaccount_entry_type" + GetCurrentRow).val('');
    $(".saveaccount_code_id" + GetCurrentRow).val('');
    $(".clsaccount_code_value" + GetCurrentRow).val('');
   
  
    var clsaccount_entry_type = "";
    var saveaccount_code_id = ""
    var clsaccount_code_value = "";   
}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var clsaccount_entry_type = $(".clsaccount_entry_type" + PreviousRow).val();
    var saveaccount_code_id = $(".saveaccount_code_id" + PreviousRow).val();
    var Saveacc_owner = $(".clsaccount_code_value" + PreviousRow).val();
 
   
    $(".clsaccount_entry_type" + GetCurrentRow).val(clsaccount_entry_type);
    $(".saveaccount_code_id" + GetCurrentRow).val(saveaccount_code_id);
    $(".clsaccount_code_value" + GetCurrentRow).val(Saveacc_owner);
  

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}