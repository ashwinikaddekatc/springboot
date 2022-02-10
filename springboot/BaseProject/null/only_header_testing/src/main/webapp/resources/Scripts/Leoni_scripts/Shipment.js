function ContactPersonClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveContactFname1" + GetCurrentRow).val('');
    $(".saveContactMname2" + GetCurrentRow).val('');
    $(".saveContactLname3" + GetCurrentRow).val('');
    $(".saveContactGender" + GetCurrentRow).val('');
    $(".saveContactPhone" + GetCurrentRow).val('');

    $(".saveContactMobile" + GetCurrentRow).val('');
    $(".rowNumEMail1456" + GetCurrentRow).val('');
    $(".rowNumLines87" + GetCurrentRow).val('');

    // document.getElementById("srNo " + GetCurrentRow).value = '';

    var saveContactFname1 = "";
    var saveContactMname2 = "";
    var saveContactLname3 = ""
    var saveContactGender = "";
    var saveContactPhone = "";

    var saveContactMobile = "";
    var rowNumEMail1456 = "";
    var rowNumLines87 = "";

    //var srNo = "";

}

function ContactPersonCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveContactFname1 = $(".saveContactFname1" + PreviousRow).val();
    var saveContactMname2 = $(".saveContactMname2" + PreviousRow).val();
    var saveContactLname3 = $(".saveContactLname3" + PreviousRow).val();
    var saveContactGender = $(".saveContactGender" + PreviousRow).val();
    var saveContactPhone = $(".saveContactPhone" + PreviousRow).val();

    var saveContactMobile = $(".saveContactMobile" + PreviousRow).val();
    var rowNumEMail1456 = $(".rowNumEMail1456" + PreviousRow).val();
    var rowNumLines87 = $(".rowNumLines87" + PreviousRow).val();

    //var srNo = document.getElementById('srNo' + PreviousRow).value;

    $(".saveContactFname1" + GetCurrentRow).val(saveContactFname1);
    $(".saveContactMname2" + GetCurrentRow).val(saveContactMname2);
    $(".saveContactLname3" + GetCurrentRow).val(saveContactLname3);
    $(".saveContactGender" + GetCurrentRow).val(saveContactGender);
    $(".saveContactPhone" + GetCurrentRow).val(saveContactPhone);

    $(".saveContactMobile" + GetCurrentRow).val(saveContactMobile);
    $(".rowNumEMail1456" + GetCurrentRow).val(rowNumEMail1456);
    $(".rowNumLines87" + GetCurrentRow).val(rowNumLines87);

    // document.getElementById("srNo " + GetCurrentRow).value = srNo;

}

function ContactPersonSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Csr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Csr_' + rowNo).css('background-color', '#BCC6D7');


}