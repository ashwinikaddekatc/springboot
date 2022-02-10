function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    document.getElementById("savePSPLineNo " + GetCurrentRow).value = '';
    $(".updateItem" + GetCurrentRow).val('');
    $(".updateDescription" + GetCurrentRow).val('');
    $(".updateQTy" + GetCurrentRow).val('');
    $(".updateUOM" + GetCurrentRow).val('');
    $(".updateNeedDate" + GetCurrentRow).val('');
    $(".updateVendor1" + GetCurrentRow).val('');
    $(".updateVendor2" + GetCurrentRow).val('');
    $(".updateVendor3" + GetCurrentRow).val('');    


    var savePSPLineNo = "";
    var updateItem = "";
    var updateDescription = ""
    var updateQTy = "";
    var updateUOM = ""
    var updateNeedDate = "";
    var updateVendor1 = "";
    var updateVendor2 = ""
    var updateVendor3 = "";
   
}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var savePSPLineNo = document.getElementById('savePSPLineNo ' + PreviousRow).value;
    var updateItem = $(".updateItem" + PreviousRow).val();
    var updateDescription = $(".updateDescription" + PreviousRow).val();
    var updateQTy = $(".updateQTy" + PreviousRow).val();
    var updateUOM = $(".updateUOM" + PreviousRow).val();
    var updateNeedDate = $(".updateNeedDate" + PreviousRow).val();
    var updateVendor1 = $(".updateVendor1" + PreviousRow).val();
    var updateVendor2 = $(".updateVendor2" + PreviousRow).val();
    var updateVendor3 = $(".updateVendor3" + PreviousRow).val();
   
    document.getElementById("savePSPLineNo " + GetCurrentRow).value = savePSPLineNo;
    $(".updateItem" + GetCurrentRow).val(updateItem);
    $(".updateDescription" + GetCurrentRow).val(updateDescription);
    $(".updateQTy" + GetCurrentRow).val(updateQTy);
    $(".updateUOM" + GetCurrentRow).val(updateUOM);
    $(".updateNeedDate" + GetCurrentRow).val(updateNeedDate);

    $(".updateVendor1" + GetCurrentRow).val(updateVendor1);
    $(".updateVendor2" + GetCurrentRow).val(updateVendor2);
    $(".updateVendor3" + GetCurrentRow).val(updateVendor3);
    
  }

  function SelectedRowData(rowNo)
 {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}