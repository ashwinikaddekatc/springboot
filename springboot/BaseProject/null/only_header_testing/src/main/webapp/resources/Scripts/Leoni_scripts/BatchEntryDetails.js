function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".Funds1" + GetCurrentRow).val('');
    $(".Funds2" + GetCurrentRow).val('');  


    var Funds1 = "";
    var Funds2 = ""; 


}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;
   
    var Funds1 = $(".Funds1" + PreviousRow).val();
    var Funds2 = $(".Funds2" + PreviousRow).val();

    $(".Funds1" + GetCurrentRow).val(Funds1);
    alert("chk1")
    $(".Funds2" + GetCurrentRow).val(Funds2);
    alert("chk2")

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}