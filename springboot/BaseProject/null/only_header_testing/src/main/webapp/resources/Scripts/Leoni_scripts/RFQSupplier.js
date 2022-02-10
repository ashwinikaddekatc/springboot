function RFQSupplierClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    //$(".saveName" + GetCurrentRow).val('');

    $(".saveSupplierName" + GetCurrentRow).val('');
    $(".saveSiteId" + GetCurrentRow).val('');
    $(".saveContactPerson" + GetCurrentRow).val('');
    $(".savePhNo" + GetCurrentRow).val('');
    $(".saveEmailId" + GetCurrentRow).val('');

    document.getElementById("rfqno" + GetCurrentRow).value = '';
    document.getElementById("lineid" + GetCurrentRow).value = '';

   
    //document.getElementById("address_id " + GetCurrentRow).value = '';

    //var saveName = "";

    var saveSupplierName = "";
    var saveSiteId = "";
    var saveContactPerson = ""
    var savePhNo = "";
    var saveEmailId = "";


    var rfqno = "";
    var lineid = "";

   
    //var address_id = "";

}

function RFQSupplierCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    //var saveName = $(".saveName" + PreviousRow).val();

    var saveSupplierName = $(".saveSupplierName" + PreviousRow).val();
    var saveSiteId = $(".saveSiteId" + PreviousRow).val();
    var saveContactPerson = $(".saveContactPerson" + PreviousRow).val();
    var savePhNo = $(".savePhNo" + PreviousRow).val();
    var saveEmailId = $(".saveEmailId" + PreviousRow).val();

    var rfqno = document.getElementById('rfqno' + PreviousRow).value;
    var lineid = document.getElementById('lineid' + PreviousRow).value;

    //var address_id = document.getElementById('address_id' + PreviousRow).value;

    //$(".saveName" + GetCurrentRow).val(saveName);

    $(".saveSupplierName" + GetCurrentRow).val(saveSupplierName);
    $(".saveSiteId" + GetCurrentRow).val(saveSiteId);
    $(".saveContactPerson" + GetCurrentRow).val(saveContactPerson);
    $(".savePhNo" + GetCurrentRow).val(savePhNo);
    $(".saveEmailId" + GetCurrentRow).val(saveEmailId);

    document.getElementById("rfqno" + GetCurrentRow).value = rfqno;
    document.getElementById("lineid" + GetCurrentRow).value = lineid;

    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function RFQSupplierSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Ssr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Ssr_' + rowNo).css('background-color', '#BCC6D7');


}