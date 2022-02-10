function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".orgId" + GetCurrentRow).val('');
    $(".docType" + GetCurrentRow).val('');
    $(".OrderType" + GetCurrentRow).val('');
    $(".nextNo" + GetCurrentRow).val('');
   
   
    var orgId = "";
    var docType = ""
    var OrderType = "";
    var nextNo = ""

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var orgId = $(".orgId" + PreviousRow).val();
    var docType = $(".docType" + PreviousRow).val();
    var OrderType = $(".OrderType" + PreviousRow).val();
    var nextNo = $(".nextNo" + PreviousRow).val();  


    $(".orgId" + GetCurrentRow).val(orgId);
    $(".docType" + GetCurrentRow).val(docType);
    $(".OrderType" + GetCurrentRow).val(OrderType);
    $(".nextNo" + GetCurrentRow).val(nextNo); 
    

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}