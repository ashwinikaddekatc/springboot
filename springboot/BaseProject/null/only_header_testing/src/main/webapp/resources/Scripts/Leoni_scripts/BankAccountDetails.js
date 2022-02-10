function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

  
    $(".Saveacc_no" + GetCurrentRow).val('');
    $(".Saveacc_name" + GetCurrentRow).val('');
    $(".updateacc_owner" + GetCurrentRow).val('');
    document.getElementById("Save_sdate " + GetCurrentRow).value = '';
    document.getElementById("Save_edate " + GetCurrentRow).value = '';



    var Saveacc_no = "";
    var Saveacc_name = ""
    var updateacc_owner = "";
    var Save_sdate = ""
    var Save_edate = "";
    

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    
    var Saveacc_no = $(".Saveacc_no" + PreviousRow).val();
    var Saveacc_name = $(".Saveacc_name" + PreviousRow).val();
    var updateacc_owner = $(".updateacc_owner" + PreviousRow).val();
    var Save_sdate = document.getElementById('Save_sdate ' + PreviousRow).value;
    var Save_edate = document.getElementById('Save_edate ' + PreviousRow).value;


    
    $(".Saveacc_no" + GetCurrentRow).val(Saveacc_no);
    $(".Saveacc_name" + GetCurrentRow).val(Saveacc_name);
    $(".updateacc_owner" + GetCurrentRow).val(updateacc_owner);
    document.getElementById("Save_sdate " + GetCurrentRow).value = Save_sdate;
    document.getElementById("Save_edate " + GetCurrentRow).value = Save_edate;


}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}