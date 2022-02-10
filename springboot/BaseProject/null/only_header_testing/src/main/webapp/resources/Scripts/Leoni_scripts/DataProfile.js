function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".lentityID" + GetCurrentRow).val('');
    $(".OUName" + GetCurrentRow).val('');
    $(".InvOrg" + GetCurrentRow).val('');
    $(".primary_flag" + GetCurrentRow).val('').attr('checked', false); 
    $(".PrimaryFlag" + GetCurrentRow).val('');

    var lentityID = "";
    var OUName = ""
    var InvOrg = "";
    var primary_flag = ""
    var PrimaryFlag = "";
    

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var lentityID = $(".lentityID" + PreviousRow).val();
    var OUName = $(".OUName" + PreviousRow).val();
    var InvOrg = $(".InvOrg" + PreviousRow).val();
    var primary_flag = $(".primary_flag" + PreviousRow).val();
    var PrimaryFlag = $(".PrimaryFlag" + PreviousRow).val();

    $(".lentityID" + GetCurrentRow).val(lentityID);
    $(".OUName" + GetCurrentRow).val(OUName);
    $(".InvOrg" + GetCurrentRow).val(InvOrg);
    $(".primary_flag" + GetCurrentRow).val(primary_flag);
    $(".PrimaryFlag" + GetCurrentRow).val(PrimaryFlag);

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}