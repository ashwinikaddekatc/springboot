function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();


    $(".savefinancialYear" + GetCurrentRow).val('');
    $(".SavePeriodMonth" + GetCurrentRow).val('');
    $(".SavePeriodNo" + GetCurrentRow).val('');
    $(".SaveStartDate" + GetCurrentRow).val('');
    $(".SaveEndDate" + GetCurrentRow).val('');
    $(".SaveStatus" + GetCurrentRow).val('');

   



    var savefinancialYear = "";
    var SavePeriodMonth = ""
    var SavePeriodNo = "";
    var SaveStartDate = ""
    var SaveEndDate = "";
    var SaveStatus = "";
   


}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;


    var savefinancialYear = $(".savefinancialYear" + PreviousRow).val();
    var SavePeriodMonth = $(".SavePeriodMonth" + PreviousRow).val();
    var SavePeriodNo = $(".SavePeriodNo" + PreviousRow).val();
    var SaveStartDate = $(".SaveStartDate" + PreviousRow).val();
    var SaveEndDate = $(".SaveEndDate" + PreviousRow).val();
    var SaveStatus = $(".SaveStatus" + PreviousRow).val();
   


    $(".savefinancialYear" + GetCurrentRow).val(savefinancialYear);
    $(".SavePeriodMonth" + GetCurrentRow).val(SavePeriodMonth);
    $(".SavePeriodNo" + GetCurrentRow).val(SavePeriodNo);
    $(".SaveStartDate" + GetCurrentRow).val(SaveStartDate);
    $(".SaveEndDate" + GetCurrentRow).val(SaveEndDate);
    $(".SaveStatus" + GetCurrentRow).val(SaveStatus);
   

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}