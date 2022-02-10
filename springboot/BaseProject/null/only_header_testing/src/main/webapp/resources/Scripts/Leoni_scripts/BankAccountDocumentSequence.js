function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();


    $(".orgId" + GetCurrentRow).val('');
    $(".Bank_Name" + GetCurrentRow).val('');
    $(".AccountNo" + GetCurrentRow).val('');
    $(".nextNo" + GetCurrentRow).val('');
    $(".StartingSeq" + GetCurrentRow).val('');
    $(".EndingSeq" + GetCurrentRow).val('');
   


    var orgId = "";
    var Bank_Name = ""
    var AccountNo = "";
    var nextNo = ""
    var StartingSeq = "";
    var EndingSeq = "";

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;


    var orgId = $(".orgId" + PreviousRow).val();
    var Bank_Name = $(".Bank_Name" + PreviousRow).val();
    var AccountNo = $(".AccountNo" + PreviousRow).val();
    var nextNo = $(".nextNo" + PreviousRow).val();
    var StartingSeq = $(".StartingSeq" + PreviousRow).val();
    var EndingSeq = $(".EndingSeq" + PreviousRow).val();
    



    $(".orgId" + GetCurrentRow).val(orgId);
    $(".Bank_Name" + GetCurrentRow).val(Bank_Name);
    $(".AccountNo" + GetCurrentRow).val(AccountNo);
    $(".nextNo" + GetCurrentRow).val(nextNo);
    $(".StartingSeq" + GetCurrentRow).val(StartingSeq);
    $(".EndingSeq" + GetCurrentRow).val(EndingSeq);
   

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}