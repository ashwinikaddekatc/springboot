function TaxClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveSupplierType" + GetCurrentRow).val('');
    $(".saveExiceNo" + GetCurrentRow).val('');
    $(".saveEccCode" + GetCurrentRow).val('');
    $(".saveRange" + GetCurrentRow).val('');
    $(".saveDivision" + GetCurrentRow).val('');

    $(".saveCollectrate" + GetCurrentRow).val('');
    // document.getElementById("srNo " + GetCurrentRow).value = '';



    var saveSupplierType = "";
    var saveExiceNo = "";
    var saveEccCode = ""
    var saveRange = "";
    var saveDivision = "";

    var saveCollectrate = "";
    //var srNo = "";

}

function TaxCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveSupplierType = $(".saveSupplierType" + PreviousRow).val();
    var saveExiceNo = $(".saveExiceNo" + PreviousRow).val();
    var saveEccCode = $(".saveEccCode" + PreviousRow).val();
    var saveRange = $(".saveRange" + PreviousRow).val();
    var saveDivision = $(".saveDivision" + PreviousRow).val();

    var saveCollectrate = $(".saveCollectrate" + PreviousRow).val();
    //var srNo = document.getElementById('srNo' + PreviousRow).value;



    $(".saveSupplierType" + GetCurrentRow).val(saveSupplierType);
    $(".saveExiceNo" + GetCurrentRow).val(saveExiceNo);
    $(".saveEccCode" + GetCurrentRow).val(saveEccCode);
    $(".saveRange" + GetCurrentRow).val(saveRange);
    $(".saveDivision" + GetCurrentRow).val(saveDivision);

    $(".saveCollectrate" + GetCurrentRow).val(saveCollectrate);
    // document.getElementById("srNo " + GetCurrentRow).value = srNo;


}

function TaxSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Tsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Tsr_' + rowNo).css('background-color', '#BCC6D7');


}
