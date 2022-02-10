function SupplierTaxClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveSupplierType" + GetCurrentRow).val('');

    $(".rowNumLines27" + GetCurrentRow).val('');
    $(".rowNumLines28" + GetCurrentRow).val('');
    $(".rowNumLines29" + GetCurrentRow).val('');
    $(".rowNumLines30" + GetCurrentRow).val('');
    $(".rowNumLines31" + GetCurrentRow).val('');

//    $(".sup_tax" + GetCurrentRow).val('');
    

    document.getElementById("address_id" + GetCurrentRow).value = '';

    var saveSupplierType = "";

    var rowNumLines27 = "";
    var rowNumLines28 = "";
    var rowNumLines29 = ""
    var rowNumLines30 = "";
    var rowNumLines31 = "";

//    var sup_tax = "";
    

    var address_id = "";

}

function SupplierTaxCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveSupplierType = $(".saveSupplierType" + PreviousRow).val();

    var rowNumLines27 = $(".rowNumLines27" + PreviousRow).val();
    var rowNumLines28 = $(".rowNumLines28" + PreviousRow).val();
    var rowNumLines29 = $(".rowNumLines29" + PreviousRow).val();
    var rowNumLines30 = $(".rowNumLines30" + PreviousRow).val();
    var rowNumLines31 = $(".rowNumLines31" + PreviousRow).val();

//    var sup_tax = $(".sup_tax" + PreviousRow).val();
    

//    var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".saveSupplierType" + GetCurrentRow).val(saveSupplierType);

    $(".rowNumLines27" + GetCurrentRow).val(rowNumLines27);
    $(".rowNumLines28" + GetCurrentRow).val(rowNumLines28);
    $(".rowNumLines29" + GetCurrentRow).val(rowNumLines29);
    $(".rowNumLines30" + GetCurrentRow).val(rowNumLines30);
    $(".rowNumLines31" + GetCurrentRow).val(rowNumLines31);

//    $(".sup_tax" + GetCurrentRow).val(sup_tax);
    

//    document.getElementById("address_id" + GetCurrentRow).value = address_id;

}

function SupplierTaxSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Tsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Tsr_' + rowNo).css('background-color', '#BCC6D7');


}