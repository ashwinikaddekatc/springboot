function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    //document.getElementById("Save_fin_date " + GetCurrentRow).value = '';
    $(".save_account" + GetCurrentRow).val('');
    $(".save_department" + GetCurrentRow).val('');
    $(".unitPriceToSave" + GetCurrentRow).val('');
    $(".quantityToSave" + GetCurrentRow).val('');
    $(".totalPriceToSave" + GetCurrentRow).val('');
    $(".TaxTotalToSave" + GetCurrentRow).val('');
    $(".save_invoice_line_remark" + GetCurrentRow).val('');
    $(".save_invoice_payment_status" + GetCurrentRow).val('');
    $(".saveEnable" + GetCurrentRow).val('').attr('checked', false);
    $(".save_project" + GetCurrentRow).val('');
    $(".save_product" + GetCurrentRow).val('');
    $(".save_future" + GetCurrentRow).val('');
    $(".save_taxgroup_id" + GetCurrentRow).val('');

   // var Save_fin_date = "";
    var save_account = "";
    var save_department = ""
    var unitPriceToSave = "";
    var quantityToSave = ""
    var totalPriceToSave = "";
    var TaxTotalToSave = "";
    var save_invoice_line_remark = ""
    var save_invoice_payment_status = "";
    var saveEnable = "";
    var save_project = ""
    var save_product = "";
    var save_future = ""
    var save_taxgroup_id = "";  


}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    //var Save_fin_date = document.getElementById('Save_fin_date ' + PreviousRow).value;
    var save_account = $(".save_account" + PreviousRow).val();
    var save_department = $(".save_department" + PreviousRow).val();
    var unitPriceToSave = $(".unitPriceToSave" + PreviousRow).val();
    var quantityToSave = $(".quantityToSave" + PreviousRow).val();
    var totalPriceToSave = $(".totalPriceToSave" + PreviousRow).val();
    var TaxTotalToSave = $(".TaxTotalToSave" + PreviousRow).val();
    var save_invoice_line_remark = $(".save_invoice_line_remark" + PreviousRow).val();
    var save_invoice_payment_status = $(".save_invoice_payment_status" + PreviousRow).val();
    var save_project = $(".save_project" + PreviousRow).val();
    var save_product = $(".save_product" + PreviousRow).val();
    var save_future = $(".save_future" + PreviousRow).val();
    var save_taxgroup_id = $(".save_taxgroup_id" + PreviousRow).val();

    //document.getElementById("Save_fin_date " + GetCurrentRow).value = Save_fin_date;
    $(".save_account" + GetCurrentRow).val(save_account);
    $(".save_department" + GetCurrentRow).val(save_department);
    $(".unitPriceToSave" + GetCurrentRow).val(unitPriceToSave);
    $(".quantityToSave" + GetCurrentRow).val(quantityToSave);
    $(".totalPriceToSave" + GetCurrentRow).val(totalPriceToSave);
    $(".TaxTotalToSave" + GetCurrentRow).val(TaxTotalToSave);
    $(".save_invoice_line_remark" + GetCurrentRow).val(save_invoice_line_remark);
    $(".save_invoice_payment_status" + GetCurrentRow).val(save_invoice_payment_status);
    $(".save_project" + GetCurrentRow).val(save_project);
    $(".save_product" + GetCurrentRow).val(save_product);
    $(".save_future" + GetCurrentRow).val(save_future);
    $(".save_taxgroup_id" + GetCurrentRow).val(save_taxgroup_id);
    

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}