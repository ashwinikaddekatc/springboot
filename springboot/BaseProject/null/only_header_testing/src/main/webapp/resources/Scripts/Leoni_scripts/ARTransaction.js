function ARTransactionClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveLineType" + GetCurrentRow).val('');

    $(".itemidToSave" + GetCurrentRow).val('');
    $(".items" + GetCurrentRow).val('');
    $(".descrptionToSave" + GetCurrentRow).val('');
    $(".uomToSave" + GetCurrentRow).val('');
    $(".quantityToSave" + GetCurrentRow).val('');

    $(".unitPriceToSave" + GetCurrentRow).val('');
    $(".totalPriceToSave" + GetCurrentRow).val('');
    $(".clsfin_ar_trans_lines_tax_group" + GetCurrentRow).val('');
    $(".Save_fin_expense_amount1" + GetCurrentRow).val('');
    $(".121" + GetCurrentRow).val('');

    $(".lineTotal" + GetCurrentRow).val('');
//    $(".saveOrderType" + GetCurrentRow).val('');
//    $(".savePriceList" + GetCurrentRow).val('');
//    $(".rowNumChar15" + GetCurrentRow).val('');
//    $(".savePostalCode" + GetCurrentRow).val('');

//    $(".savePmFlg" + GetCurrentRow).val('').attr("Checked", false);
//    $(".clsOwner" + GetCurrentRow).val('');

    document.getElementById("getCurrentValue" + GetCurrentRow).value = '';
    document.getElementById("HeaderID_ForLines" + GetCurrentRow).value = '';

    var saveLineType = "";

    var itemidToSave = "";
    var items = "";
    var descrptionToSave = ""
    var uomToSave = "";
    var quantityToSave = "";

    var unitPriceToSave = "";
    var totalPriceToSave = "";
    var clsfin_ar_trans_lines_tax_group = "";
    var Save_fin_expense_amount1 = "";
    var a121 = "";

    var lineTotal = "";
//    var saveOrderType = "";
//    var savePriceList = "";
//    var rowNumChar15 = "";
//    var savePostalCode = "";

//    var savePmFlg = "";
//    var clsOwner = "";

    var getCurrentValue = "";
    var HeaderID_ForLines = "";

}

function ARTransactionCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveLineType = $(".saveLineType" + PreviousRow).val();

    var itemidToSave = $(".itemidToSave" + PreviousRow).val();
    var items = $(".items" + PreviousRow).val();
    var descrptionToSave = $(".descrptionToSave" + PreviousRow).val();
    var uomToSave = $(".uomToSave" + PreviousRow).val();
    var quantityToSave = $(".quantityToSave" + PreviousRow).val();

    var unitPriceToSave = $(".unitPriceToSave" + PreviousRow).val();
    var totalPriceToSave = $(".totalPriceToSave" + PreviousRow).val();
    var clsfin_ar_trans_lines_tax_group = $(".clsfin_ar_trans_lines_tax_group" + PreviousRow).val();
    var Save_fin_expense_amount1 = $(".Save_fin_expense_amount1" + PreviousRow).val();
    var a121 = $(".121" + PreviousRow).val();

    var lineTotal = $(".lineTotal" + PreviousRow).val();
//    var saveOrderType = $(".saveOrderType" + PreviousRow).val();
//    var savePriceList = $(".savePriceList" + PreviousRow).val();
//    var rowNumChar15 = $(".rowNumChar15" + PreviousRow).val();
//    var savePostalCode = $(".savePostalCode" + PreviousRow).val();

//    var savePmFlg = $(".savePmFlg" + PreviousRow).val();
//    var clsOwner = $(".clsOwner" + PreviousRow).val();

    var getCurrentValue = document.getElementById('getCurrentValue' + PreviousRow).value;
    var HeaderID_ForLines = document.getElementById('HeaderID_ForLines' + PreviousRow).value;

    $(".saveLineType" + GetCurrentRow).val(saveLineType);

    $(".itemidToSave" + GetCurrentRow).val(itemidToSave);
    $(".items" + GetCurrentRow).val(items);
    $(".descrptionToSave" + GetCurrentRow).val(descrptionToSave);
    $(".uomToSave" + GetCurrentRow).val(uomToSave);
    $(".quantityToSave" + GetCurrentRow).val(quantityToSave);

    $(".unitPriceToSave" + GetCurrentRow).val(unitPriceToSave);
    $(".totalPriceToSave" + GetCurrentRow).val(totalPriceToSave);
    $(".clsfin_ar_trans_lines_tax_group" + GetCurrentRow).val(clsfin_ar_trans_lines_tax_group);
    $(".Save_fin_expense_amount1" + GetCurrentRow).val(Save_fin_expense_amount1);
    $(".121" + GetCurrentRow).val(121);

    $(".lineTotal" + GetCurrentRow).val(lineTotal);
//    $(".saveOrderType" + GetCurrentRow).val(saveOrderType);
//    $(".savePriceList" + GetCurrentRow).val(savePriceList);
//    $(".rowNumChar15" + GetCurrentRow).val(rowNumChar15);
//    $(".savePostalCode" + GetCurrentRow).val(savePostalCode);

//    $(".savePmFlg" + GetCurrentRow).val(savePmFlg);
//    $(".clsOwner" + GetCurrentRow).val(clsOwner);

    document.getElementById("getCurrentValue" + GetCurrentRow).value = getCurrentValue;
    document.getElementById("HeaderID_ForLines" + GetCurrentRow).value = HeaderID_ForLines;
    
}

function ARTransactionSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}