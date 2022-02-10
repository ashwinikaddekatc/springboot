function SupplierBankClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".rowNumChar1" + GetCurrentRow).val('');

    $(".rowNumChar2" + GetCurrentRow).val('');
    $(".rowNumChar3" + GetCurrentRow).val('');
    $(".rowNumLines4" + GetCurrentRow).val('');
    $(".rowNumEMail2" + GetCurrentRow).val('');
    $(".saveBankIfscCode" + GetCurrentRow).val('');

    $(".rowNumChar16" + GetCurrentRow).val('');
    $(".saveBankAcNO" + GetCurrentRow).val('');
    $(".rowNumChar17" + GetCurrentRow).val('');
  

    //document.getElementById("address_id " + GetCurrentRow).value = '';

    var rowNumChar1 = "";

    var rowNumChar2 = "";
    var rowNumChar3 = "";
    var rowNumLines4 = ""
    var rowNumEMail2 = "";
    var saveBankIfscCode = "";

    var rowNumChar16 = "";
    var saveBankAcNO = "";
    var rowNumChar17 = "";
   

    //var address_id = "";

}

function SupplierBankCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var rowNumChar1 = $(".rowNumChar1" + PreviousRow).val();

    var rowNumChar2 = $(".rowNumChar2" + PreviousRow).val();
    var rowNumChar3 = $(".rowNumChar3" + PreviousRow).val();
    var rowNumLines4 = $(".rowNumLines4" + PreviousRow).val();
    var rowNumEMail2 = $(".rowNumEMail2" + PreviousRow).val();
    var saveBankIfscCode = $(".saveBankIfscCode" + PreviousRow).val();

    var rowNumChar16 = $(".rowNumChar16" + PreviousRow).val();
    var saveBankAcNO = $(".saveBankAcNO" + PreviousRow).val();
    var rowNumChar17 = $(".rowNumChar17" + PreviousRow).val();
    

    //var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".rowNumChar1" + GetCurrentRow).val(rowNumChar1);

    $(".rowNumChar2" + GetCurrentRow).val(rowNumChar2);
    $(".rowNumChar3" + GetCurrentRow).val(rowNumChar3);
    $(".rowNumLines4" + GetCurrentRow).val(rowNumLines4);
    $(".rowNumEMail2" + GetCurrentRow).val(rowNumEMail2);
    $(".saveBankIfscCode" + GetCurrentRow).val(saveBankIfscCode);

    $(".rowNumChar16" + GetCurrentRow).val(rowNumChar16);
    $(".saveBankAcNO" + GetCurrentRow).val(saveBankAcNO);
    $(".rowNumChar17" + GetCurrentRow).val(rowNumChar17);
  

    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function SupplierBankSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Bsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Bsr_' + rowNo).css('background-color', '#BCC6D7');


}