function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".lookupValueToSave" + GetCurrentRow).val('');
    $(".meaningToSave" + GetCurrentRow).val('');
    $(".lookuptype" + GetCurrentRow).val('');
    document.getElementById("startDateToSave " + GetCurrentRow).value = '';
    document.getElementById("endDateToSave " + GetCurrentRow).value = '';
   
    var lookupValueToSave = "";
    var meaningToSave = ""
    var lookuptype = "";
    var startDateToSave = ""
    var endDateToSave = "";
  } 


 function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var lookupValueToSave = $(".lookupValueToSave" + PreviousRow).val();
    var meaningToSave = $(".meaningToSave" + PreviousRow).val();
    var lookuptype = $(".lookuptype" + PreviousRow).val();   
    var startDateToSave = document.getElementById('startDateToSave ' + PreviousRow).value;
    var endDateToSave = document.getElementById('endDateToSave ' + PreviousRow).value;

    $(".lookupValueToSave" + GetCurrentRow).val(lookupValueToSave);
    $(".meaningToSave" + GetCurrentRow).val(meaningToSave);
    $(".lookuptype" + GetCurrentRow).val(lookuptype);    
    document.getElementById("startDateToSave " + GetCurrentRow).value = startDateToSave;
    document.getElementById("endDateToSave " + GetCurrentRow).value = endDateToSave;
   
}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}