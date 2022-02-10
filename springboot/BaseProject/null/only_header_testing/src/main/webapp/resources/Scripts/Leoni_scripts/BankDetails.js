function BankClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".rowNumChar1" + GetCurrentRow).val('');
    $(".saveBankCity" + GetCurrentRow).val('');
    $(".saveBankcountry" + GetCurrentRow).val('');
    $(".saveBankPhone" + GetCurrentRow).val('');
    $(".rowNumEMail5" + GetCurrentRow).val('');

    $(".saveBankIfscCode" + GetCurrentRow).val('');
    $(".saveBankAcName7" + GetCurrentRow).val('');
    $(".rowNumLines8" + GetCurrentRow).val('');
    $(".saveBankContact9" + GetCurrentRow).val('');

   // document.getElementById("srNo " + GetCurrentRow).value = '';
 
    var rowNumChar1 = "";
    var saveBankCity = "";
    var saveBankcountry = ""
    var saveBankPhone = "";
    var rowNumEMail5 = "";

    var saveBankIfscCode = "";
    var saveBankAcName7 = "";
    var rowNumLines8 = "";
    var saveBankContact9 = "";

    //var srNo = "";
   
}

function BankCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var rowNumChar1 = $(".rowNumChar1" + PreviousRow).val();
    var saveBankCity = $(".saveBankCity" + PreviousRow).val();
    var saveBankcountry = $(".saveBankcountry" + PreviousRow).val();
    var saveBankPhone = $(".saveBankPhone" + PreviousRow).val();
    var rowNumEMail5 = $(".rowNumEMail5" + PreviousRow).val();

    var saveBankIfscCode = $(".saveBankIfscCode" + PreviousRow).val();
    var saveBankAcName7 = $(".saveBankAcName7" + PreviousRow).val();
    var rowNumLines8 = $(".rowNumLines8" + PreviousRow).val();
    var saveBankContact9 = $(".saveBankContact9" + PreviousRow).val();

    //var srNo = document.getElementById('srNo' + PreviousRow).value;
    
    $(".rowNumChar1" + GetCurrentRow).val(rowNumChar1);
    $(".saveBankCity" + GetCurrentRow).val(saveBankCity);
    $(".saveBankcountry" + GetCurrentRow).val(saveBankcountry);
    $(".saveBankPhone" + GetCurrentRow).val(saveBankPhone);
    $(".rowNumEMail5" + GetCurrentRow).val(rowNumEMail5);

    $(".saveBankIfscCode" + GetCurrentRow).val(saveBankIfscCode);
    $(".saveBankAcName7" + GetCurrentRow).val(saveBankAcName7);
    $(".rowNumLines8" + GetCurrentRow).val(rowNumLines8);
    $(".saveBankContact9" + GetCurrentRow).val(saveBankContact9);

   // document.getElementById("srNo " + GetCurrentRow).value = srNo;

}

function BankSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Bsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Bsr_' + rowNo).css('background-color', '#BCC6D7');


}