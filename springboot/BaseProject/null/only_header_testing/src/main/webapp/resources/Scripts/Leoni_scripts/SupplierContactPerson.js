function SupplierContactPersonClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".rowNumChar19" + GetCurrentRow).val('');

    $(".rowNumChar21" + GetCurrentRow).val('');
    $(".rowNumLines1" + GetCurrentRow).val('');
    $(".rowNumLines2" + GetCurrentRow).val('');
    $(".rowNumEMail1" + GetCurrentRow).val('');
    $(".rowNumLines3" + GetCurrentRow).val('');

   
    //document.getElementById("address_id " + GetCurrentRow).value = '';

    var rowNumChar19 = "";

    var rowNumChar21 = "";
    var rowNumLines1 = "";
    var rowNumLines2 = ""
    var rowNumEMail1 = "";
    var rowNumLines3 = "";

  
    //var address_id = "";

}

function SupplierContactPersonCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var rowNumChar19 = $(".rowNumChar19" + PreviousRow).val();

    var rowNumChar21 = $(".rowNumChar21" + PreviousRow).val();
    var rowNumLines1 = $(".rowNumLines1" + PreviousRow).val();
    var rowNumLines2 = $(".rowNumLines2" + PreviousRow).val();
    var rowNumEMail1 = $(".rowNumEMail1" + PreviousRow).val();
    var rowNumLines3 = $(".rowNumLines3" + PreviousRow).val();

    //var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".rowNumChar19" + GetCurrentRow).val(rowNumChar19);

    $(".rowNumChar21" + GetCurrentRow).val(rowNumChar21);
    $(".rowNumLines1" + GetCurrentRow).val(rowNumLines1);
    $(".rowNumLines2" + GetCurrentRow).val(rowNumLines2);
    $(".rowNumEMail1" + GetCurrentRow).val(rowNumEMail1);
    $(".rowNumLines3" + GetCurrentRow).val(rowNumLines3);

   
    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function SupplierContactPersonSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Csr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Csr_' + rowNo).css('background-color', '#BCC6D7');


}