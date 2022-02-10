function RFQTermsClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    
    $(".savePaymentTerms" + GetCurrentRow).val('');
    $(".saveFreightTerms" + GetCurrentRow).val('');
    $(".saveCarrierTerms" + GetCurrentRow).val('');
    $(".saveFob" + GetCurrentRow).val('');

    document.getElementById("head" + GetCurrentRow).value = '';
    document.getElementById("LINE_ID" + GetCurrentRow).value = '';
    //document.getElementById("address_id " + GetCurrentRow).value = '';

   
    var savePaymentTerms = "";
    var saveFreightTerms = "";
    var saveCarrierTerms = ""
    var saveFob = "";

    var head = "";
    var LINE_ID = "";
    //var address_id = "";

}

function RFQTermsCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    
    var savePaymentTerms = $(".savePaymentTerms" + PreviousRow).val();
    var saveFreightTerms = $(".saveFreightTerms" + PreviousRow).val();
    var saveCarrierTerms = $(".saveCarrierTerms" + PreviousRow).val();
    var saveFob = $(".saveFob" + PreviousRow).val();

    var head = document.getElementById('head' + PreviousRow).value;
    var LINE_ID = document.getElementById('LINE_ID' + PreviousRow).value;
    //var address_id = document.getElementById('address_id' + PreviousRow).value;

   
    $(".savePaymentTerms" + GetCurrentRow).val(savePaymentTerms);
    $(".saveFreightTerms" + GetCurrentRow).val(saveFreightTerms);
    $(".saveCarrierTerms" + GetCurrentRow).val(saveCarrierTerms);
    $(".saveFob" + GetCurrentRow).val(saveFob);

    document.getElementById("head" + GetCurrentRow).value = head;
    document.getElementById("LINE_ID" + GetCurrentRow).value = LINE_ID;
    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function RFQTermsSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Tsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Tsr_' + rowNo).css('background-color', '#BCC6D7');


}