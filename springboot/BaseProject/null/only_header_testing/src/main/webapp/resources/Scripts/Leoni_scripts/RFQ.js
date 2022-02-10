function RFQClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    //$(".saveName" + GetCurrentRow).val('');

    $(".saveLineType" + GetCurrentRow).val('');
    $(".itemidToSave" + GetCurrentRow).val('');
    $(".items" + GetCurrentRow).val('');
    $(".descrptionToSave" + GetCurrentRow).val('');
    $(".uomToSave" + GetCurrentRow).val('');

    $(".quantityToSave" + GetCurrentRow).val('');
    $(".unitPriceToSave" + GetCurrentRow).val('');
    $(".totalPriceToSave" + GetCurrentRow).val('');
    $(".saveLineType1" + GetCurrentRow).val('');
    $(".saveLineType2" + GetCurrentRow).val('');

    $(".saveLineType3" + GetCurrentRow).val('');
   
    //document.getElementById("RFQHeader_id" + GetCurrentRow).value = '';
    document.getElementById("SaveNeedByDate " + GetCurrentRow).value = '';

    //var saveName = "";

    var saveLineType = "";
    var itemidToSave = "";
    var items = ""
    var descrptionToSave = "";
    var uomToSave = "";

    var quantityToSave = "";
    var unitPriceToSave = "";
    var totalPriceToSave = "";
    var saveLineType1 = "";
    var saveLineType2 = "";

    var saveLineType3 = "";
   
    //var RFQHeader_id = "";
    var SaveNeedByDate = "";

}

function RFQCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;
    
    //var saveName = $(".saveName" + PreviousRow).val();

    var saveLineType = $(".saveLineType" + PreviousRow).val();
    var itemidToSave = $(".itemidToSave" + PreviousRow).val();
    var items = $(".items" + PreviousRow).val();
    var descrptionToSave = $(".descrptionToSave" + PreviousRow).val();
    var uomToSave = $(".uomToSave" + PreviousRow).val();
    
    var quantityToSave = $(".quantityToSave" + PreviousRow).val();
    var unitPriceToSave = $(".unitPriceToSave" + PreviousRow).val();
    var totalPriceToSave = $(".totalPriceToSave" + PreviousRow).val();
    var saveLineType1 = $(".saveLineType1" + PreviousRow).val();
    var saveLineType2 = $(".saveLineType2" + PreviousRow).val();
   
    var saveLineType3 = $(".saveLineType3" + PreviousRow).val();
   // alert("1");
//    var RFQHeader_id = document.getElementById('RFQHeader_id' + PreviousRow).value;
    var SaveNeedByDate = document.getElementById('SaveNeedByDate ' + PreviousRow).value;
   // alert("2");
    //$(".saveName" + GetCurrentRow).val(saveName);

    $(".saveLineType" + GetCurrentRow).val(saveLineType);
    $(".itemidToSave" + GetCurrentRow).val(itemidToSave);
    $(".items" + GetCurrentRow).val(items);
    $(".descrptionToSave" + GetCurrentRow).val(descrptionToSave);
    $(".uomToSave" + GetCurrentRow).val(uomToSave);

    $(".quantityToSave" + GetCurrentRow).val(quantityToSave);
    $(".unitPriceToSave" + GetCurrentRow).val(unitPriceToSave);
    $(".totalPriceToSave" + GetCurrentRow).val(totalPriceToSave);
    $(".saveLineType1" + GetCurrentRow).val(saveLineType1);
    $(".saveLineType2" + GetCurrentRow).val(saveLineType2);

    $(".saveLineType3" + GetCurrentRow).val(saveLineType3);
   
    //document.getElementById("RFQHeader_id" + GetCurrentRow).value = RFQHeader_id;
    document.getElementById("SaveNeedByDate " + GetCurrentRow).value = SaveNeedByDate;
    //alert("3");
}

function RFQSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}