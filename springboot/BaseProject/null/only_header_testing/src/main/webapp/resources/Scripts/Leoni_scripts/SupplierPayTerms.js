function SupplierPayTermsClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".savePaymentTerms" + GetCurrentRow).val('');

    $(".saveFreightTerms" + GetCurrentRow).val('');
    $(".saveCarrier" + GetCurrentRow).val('');
    $(".rowNumLines2" + GetCurrentRow).val('');
    $(".saveSupFob" + GetCurrentRow).val('');
   // $(".rowNumLines3" + GetCurrentRow).val('');


    //document.getElementById("address_id " + GetCurrentRow).value = '';

    var savePaymentTerms = "";

    var saveFreightTerms = "";
    var saveCarrier = "";
    var rowNumLines2 = ""
    var saveSupFob = "";
   // var rowNumLines3 = "";


    //var address_id = "";

}

function SupplierPayTermsCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var savePaymentTerms = $(".savePaymentTerms" + PreviousRow).val();

    var saveFreightTerms = $(".saveFreightTerms" + PreviousRow).val();
    var saveCarrier = $(".saveCarrier" + PreviousRow).val();
    var rowNumLines2 = $(".rowNumLines2" + PreviousRow).val();
    var saveSupFob = $(".saveSupFob" + PreviousRow).val();
  //  var rowNumLines3 = $(".rowNumLines3" + PreviousRow).val();

    //var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".savePaymentTerms" + GetCurrentRow).val(savePaymentTerms);

    $(".saveFreightTerms" + GetCurrentRow).val(saveFreightTerms);
    $(".saveCarrier" + GetCurrentRow).val(saveCarrier);
    $(".rowNumLines2" + GetCurrentRow).val(rowNumLines2);
    $(".saveSupFob" + GetCurrentRow).val(saveSupFob);
 //   $(".rowNumLines3" + GetCurrentRow).val(rowNumLines3);


    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function SupplierPayTermsSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Psr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Psr_' + rowNo).css('background-color', '#BCC6D7');


}