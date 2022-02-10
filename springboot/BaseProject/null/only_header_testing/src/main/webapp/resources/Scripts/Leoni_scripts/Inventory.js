function InventoryTransactionClearLines() {
    

    var GetCurrentRow = $("#RowForData").val();

    $(".transaction_item" + GetCurrentRow).val('');

    $(".SelectText" + GetCurrentRow).val('');
    $(".transaction_to_locator" + GetCurrentRow).val('');
    $(".rowNum1" + GetCurrentRow).val('');
    $(".rowNum2" + GetCurrentRow).val('');
    $(".transaction_uom" + GetCurrentRow).val('');

    $(".transaction_account" + GetCurrentRow).val('');
    $(".transaction_reason" + GetCurrentRow).val('');
//    $(".rowNumChar9" + GetCurrentRow).val('');
//    $(".saveCustomerContact" + GetCurrentRow).val('');
//    $(".saveEDILocation" + GetCurrentRow).val('');

//    $(".saveW" + GetCurrentRow).val('');
//    $(".saveOrderType" + GetCurrentRow).val('');
//    $(".savePriceList" + GetCurrentRow).val('');
//    $(".rowNumChar15" + GetCurrentRow).val('');
//    $(".savePostalCode" + GetCurrentRow).val('');

//    $(".savePmFlg" + GetCurrentRow).val('').attr("Checked", false);
//    $(".clsOwner" + GetCurrentRow).val('');

    //document.getElementById("address_id " + GetCurrentRow).value = '';

    var transaction_item = "";

    var SelectText = "";
    var transaction_to_locator = "";
    var rowNum1 = ""
    var rowNum2 = "";
    var transaction_uom = "";

    var transaction_account = "";
    var transaction_reason = "";
//    var rowNumChar9 = "";
//    var saveCustomerContact = "";
//    var saveEDILocation = "";

//    var saveW = "";
//    var saveOrderType = "";
//    var savePriceList = "";
//    var rowNumChar15 = "";
//    var savePostalCode = "";

//    var savePmFlg = "";
//    var clsOwner = "";

    //var address_id = "";

}

function InventoryTransactionCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var transaction_item = $(".transaction_item" + PreviousRow).val();

    var SelectText = $(".SelectText" + PreviousRow).val();
    var transaction_to_locator = $(".transaction_to_locator" + PreviousRow).val();
    var rowNum1 = $(".rowNum1" + PreviousRow).val();
    var rowNum2 = $(".rowNum2" + PreviousRow).val();
    var transaction_uom = $(".transaction_uom" + PreviousRow).val();

    var transaction_account = $(".transaction_account" + PreviousRow).val();
    var transaction_reason = $(".transaction_reason" + PreviousRow).val();
    var rowNumChar9 = $(".rowNumChar9" + PreviousRow).val();
    var saveCustomerContact = $(".saveCustomerContact" + PreviousRow).val();
    var saveEDILocation = $(".saveEDILocation" + PreviousRow).val();

    var saveW = $(".saveW" + PreviousRow).val();
    var saveOrderType = $(".saveOrderType" + PreviousRow).val();
    var savePriceList = $(".savePriceList" + PreviousRow).val();
    var rowNumChar15 = $(".rowNumChar15" + PreviousRow).val();
    var savePostalCode = $(".savePostalCode" + PreviousRow).val();

    var savePmFlg = $(".savePmFlg" + PreviousRow).val();
    var clsOwner = $(".clsOwner" + PreviousRow).val();

    //var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".transaction_item" + GetCurrentRow).val(transaction_item);

    $(".SelectText" + GetCurrentRow).val(SelectText);
    $(".transaction_to_locator" + GetCurrentRow).val(transaction_to_locator);
    $(".rowNum1" + GetCurrentRow).val(rowNum1);
    $(".rowNum2" + GetCurrentRow).val(rowNum2);
    $(".transaction_uom" + GetCurrentRow).val(transaction_uom);

    $(".transaction_account" + GetCurrentRow).val(transaction_account);
    $(".transaction_reason" + GetCurrentRow).val(transaction_reason);
    $(".rowNumChar9" + GetCurrentRow).val(rowNumChar9);
    $(".saveCustomerContact" + GetCurrentRow).val(saveCustomerContact);
    $(".saveEDILocation" + GetCurrentRow).val(saveEDILocation);

    $(".saveW" + GetCurrentRow).val(saveW);
    $(".saveOrderType" + GetCurrentRow).val(saveOrderType);
    $(".savePriceList" + GetCurrentRow).val(savePriceList);
    $(".rowNumChar15" + GetCurrentRow).val(rowNumChar15);
    $(".savePostalCode" + GetCurrentRow).val(savePostalCode);

    $(".savePmFlg" + GetCurrentRow).val(savePmFlg);
    $(".clsOwner" + GetCurrentRow).val(clsOwner);

    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function InventoryTransactionSelectedRow(rowNo) {
    
    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}