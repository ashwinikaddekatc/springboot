function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    // document.getElementById("Save_fin_date " + GetCurrentRow).value = '';

    $(".rowNum1" + GetCurrentRow).val('');
    $(".rowNum2" + GetCurrentRow).val('');
    $(".save_adv_immediate" + GetCurrentRow).val('');
  

    var rowNum1 = "";
    var rowNum2 = "";
    var save_adv_immediate = ""
   
}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;


    var rowNum1 = $(".rowNum1" + PreviousRow).val();
    var rowNum2 = $(".rowNum2" + PreviousRow).val();
    var save_adv_immediate = $(".save_adv_immediate" + PreviousRow).val();
   

    $(".rowNum1" + GetCurrentRow).val(rowNum1);
    $(".rowNum2" + GetCurrentRow).val(rowNum2);
    $(".save_adv_immediate" + GetCurrentRow).val(save_adv_immediate);
   

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}