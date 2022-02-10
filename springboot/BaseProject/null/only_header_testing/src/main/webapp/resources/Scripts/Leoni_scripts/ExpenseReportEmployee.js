function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    document.getElementById("Save_fin_date " + GetCurrentRow).value = '';
    $(".Saveacc_name" + GetCurrentRow).val('');
    $(".save_department" + GetCurrentRow).val('');
    $(".Saveacc_owner" + GetCurrentRow).val('');
    $(".Save_fin_expense_amount" + GetCurrentRow).val('');
    $(".Save_fin_expense_tax_amount" + GetCurrentRow).val('');

    $(".Save_fin_expense_total_amount" + GetCurrentRow).val('');
    $(".Save_description" + GetCurrentRow).val('');
    $(".Save_project" + GetCurrentRow).val('');
    $(".Save_billable" + GetCurrentRow).val('').attr('checked', false);
    $(".Save_customer" + GetCurrentRow).val('');


    var Save_fin_date = "";
    var Saveacc_name = "";
    var save_department = ""
    var Saveacc_owner = "";
    var Save_fin_expense_amount = ""
    var Save_fin_expense_tax_amount = "";

    var Save_fin_expense_total_amount = "";
    var Save_description = ""
    var Save_project = "";
    var Save_billable = "";
    var Save_customer = ""
    

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var Save_fin_date = document.getElementById('Save_fin_date ' + PreviousRow).value;
    var Saveacc_name = $(".Saveacc_name" + PreviousRow).val();
    var save_department = $(".save_department" + PreviousRow).val();
    var Saveacc_owner = $(".Saveacc_owner" + PreviousRow).val();
    var Save_fin_expense_amount = $(".Save_fin_expense_amount" + PreviousRow).val();
    var Save_fin_expense_tax_amount = $(".Save_fin_expense_tax_amount" + PreviousRow).val();

    var Save_fin_expense_total_amount = $(".Save_fin_expense_total_amount" + PreviousRow).val();
    var Save_description = $(".Save_description" + PreviousRow).val();
    var Save_project = $(".Save_project" + PreviousRow).val();
    var Save_billable = $(".Save_billable" + PreviousRow).val();
    var Save_customer = $(".Save_customer" + PreviousRow).val();

    document.getElementById("Save_fin_date " + GetCurrentRow).value = Save_fin_date;
    $(".Saveacc_name" + GetCurrentRow).val(Saveacc_name);
    $(".save_department" + GetCurrentRow).val(save_department);
    $(".Saveacc_owner" + GetCurrentRow).val(Saveacc_owner);
    $(".Save_fin_expense_amount" + GetCurrentRow).val(Save_fin_expense_amount);
    $(".Save_fin_expense_tax_amount" + GetCurrentRow).val(Save_fin_expense_tax_amount);

    $(".Save_fin_expense_total_amount" + GetCurrentRow).val(Save_fin_expense_total_amount);
    $(".Save_description" + GetCurrentRow).val(Save_description);
    $(".Save_project" + GetCurrentRow).val(Save_project);
    $(".Save_billable" + GetCurrentRow).val(Save_billable);
    $(".Save_customer" + GetCurrentRow).val(Save_customer);
   
}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}