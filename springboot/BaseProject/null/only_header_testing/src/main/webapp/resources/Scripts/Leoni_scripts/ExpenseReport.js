function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".Saveacc_no" + GetCurrentRow).val('');
    $(".Saveacc_name" + GetCurrentRow).val('');
    $(".Saveacc_owner" + GetCurrentRow).val('');
    $(".Save_fin_expense_amount" + GetCurrentRow).val('');
    $(".Save_fin_expense_tax_amount" + GetCurrentRow).val('');

   

    var Saveacc_no = "";
    var Saveacc_name = ""
    var Saveacc_owner = "";
    var Save_fin_expense_amount = ""
    var Save_fin_expense_tax_amount = "";

    
}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var Saveacc_no = $(".Saveacc_no" + PreviousRow).val();
    var Saveacc_name = $(".Saveacc_name" + PreviousRow).val();
    var Saveacc_owner = $(".Saveacc_owner" + PreviousRow).val();
    var Save_fin_expense_amount = $(".Save_fin_expense_amount" + PreviousRow).val();
    var Save_fin_expense_tax_amount = $(".Save_fin_expense_tax_amount" + PreviousRow).val();

    

    $(".Saveacc_no" + GetCurrentRow).val(Saveacc_no);
    $(".Saveacc_name" + GetCurrentRow).val(Saveacc_name);
    $(".Saveacc_owner" + GetCurrentRow).val(Saveacc_owner);
    $(".Save_fin_expense_amount" + GetCurrentRow).val(Save_fin_expense_amount);
    $(".Save_fin_expense_tax_amount" + GetCurrentRow).val(Save_fin_expense_tax_amount);

    

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}