function LocatorFieldClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveName" + GetCurrentRow).val('');

    $(".saveValueSet" + GetCurrentRow).html('');
//    $(".saveCarrier" + GetCurrentRow).val('');
//    $(".rowNumLines2" + GetCurrentRow).val('');
//    $(".saveSupFob" + GetCurrentRow).val('');
//    // $(".rowNumLines3" + GetCurrentRow).val('');


    //document.getElementById("address_id " + GetCurrentRow).value = '';

    var saveName = "";

    var saveValueSet = "";
//    var saveCarrier = "";
//    var rowNumLines2 = ""
//    var saveSupFob = "";
//    // var rowNumLines3 = "";


    //var address_id = "";

}

function LocatorFieldCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveName = $(".saveName" + PreviousRow).val();

    var saveValueSet = $(".saveValueSet" + PreviousRow).html();
//    var saveCarrier = $(".saveCarrier" + PreviousRow).val();
//    var rowNumLines2 = $(".rowNumLines2" + PreviousRow).val();
//    var saveSupFob = $(".saveSupFob" + PreviousRow).val();
//    //  var rowNumLines3 = $(".rowNumLines3" + PreviousRow).val();

    //var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".saveName" + GetCurrentRow).val(saveName);

    $(".saveValueSet" + GetCurrentRow).html(saveValueSet);
//    $(".saveCarrier" + GetCurrentRow).val(saveCarrier);
//    $(".rowNumLines2" + GetCurrentRow).val(rowNumLines2);
//    $(".saveSupFob" + GetCurrentRow).val(saveSupFob);
    //   $(".rowNumLines3" + GetCurrentRow).val(rowNumLines3);


    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function LocatorFieldSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}