function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    
    $(".SaveAccountno" + GetCurrentRow).val('');
    $(".SaveDescription" + GetCurrentRow).val('');
    $(".SaveDebit" + GetCurrentRow).val('');
    $(".SaveCredit" + GetCurrentRow).val('');
    $(".savebet_line_tax_code" + GetCurrentRow).val('');
    $(".savebet_line_company" + GetCurrentRow).val('');
    $(".savebet_line_department" + GetCurrentRow).val('');
    $(".savebet_line_account" + GetCurrentRow).val('');
    $(".savebet_line_project" + GetCurrentRow).val('');
    $(".savebet_line_product" + GetCurrentRow).val('');
    $(".savebet_line_future" + GetCurrentRow).val('');
    $(".savebet_line_future1" + GetCurrentRow).val('');
   


    var SaveAccountno = "";
    var SaveDescription = "";
    var SaveDebit = "";
    var SaveCredit = "";
    var savebet_line_tax_code = "";
    var savebet_line_company = "";
    var savebet_line_department = "";
    var savebet_line_account = "";
    var savebet_line_project = "";
    var savebet_line_product = "";
    var savebet_line_future = "";
    var savebet_line_future1 = "";
   
}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

   
    var SaveAccountno = $(".SaveAccountno" + PreviousRow).val();
    var SaveDescription = $(".SaveDescription" + PreviousRow).val();
    var SaveDebit = $(".SaveDebit" + PreviousRow).val();
    var SaveCredit = $(".SaveCredit" + PreviousRow).val();
    var savebet_line_tax_code = $(".savebet_line_tax_code" + PreviousRow).val();
    var savebet_line_company = $(".savebet_line_company" + PreviousRow).val();
    var savebet_line_department = $(".savebet_line_department" + PreviousRow).val();
    var savebet_line_account = $(".savebet_line_account" + PreviousRow).val();
    var savebet_line_project = $(".savebet_line_project" + PreviousRow).val();
    var savebet_line_product = $(".savebet_line_product" + PreviousRow).val();
    var savebet_line_future = $(".savebet_line_future" + PreviousRow).val();
    var savebet_line_future1 = $(".savebet_line_future1" + PreviousRow).val();
   

  

    $(".SaveAccountno" + GetCurrentRow).val(SaveAccountno);
    $(".SaveDescription" + GetCurrentRow).val(SaveDescription);
    $(".SaveDebit" + GetCurrentRow).val(SaveDebit);
    $(".SaveCredit" + GetCurrentRow).val(SaveCredit);
    $(".savebet_line_tax_code" + GetCurrentRow).val(savebet_line_tax_code);
    $(".savebet_line_company" + GetCurrentRow).val(savebet_line_company);
    $(".savebet_line_department" + GetCurrentRow).val(savebet_line_department);
    $(".savebet_line_account" + GetCurrentRow).val(savebet_line_account);
    $(".savebet_line_project" + GetCurrentRow).val(savebet_line_project);
    $(".savebet_line_product" + GetCurrentRow).val(savebet_line_product);
    $(".savebet_line_future" + GetCurrentRow).val(savebet_line_future);
    $(".savebet_line_future1" + GetCurrentRow).val(savebet_line_future1);
  

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}