function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".updateJobNO" + GetCurrentRow).val('');
    $(".SelectText" + GetCurrentRow).val('');
    $(".updateQTy" + GetCurrentRow).val('');

    document.getElementById("SaveStartDate " + GetCurrentRow).value = '';
    document.getElementById("SaveEndDate " + GetCurrentRow).value = '';


    var updateJobNO = "";
    var SelectText = "";
    var updateQTy = ""
    var SaveStartDate = "";
    var SaveEndDate = "";
    

}

function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var updateJobNO = $(".updateJobNO" + PreviousRow).val();
    var SelectText = $(".SelectText" + PreviousRow).val();
    var updateQTy = $(".updateQTy" + PreviousRow).val();

    var SaveStartDate = document.getElementById('SaveStartDate ' + PreviousRow).value;
    var SaveEndDate = document.getElementById('SaveEndDate ' + PreviousRow).value;

    $(".updateJobNO" + GetCurrentRow).val(updateJobNO);
    $(".SelectText" + GetCurrentRow).val(SelectText);
    $(".updateQTy" + GetCurrentRow).val(updateQTy);

    document.getElementById("SaveStartDate " + GetCurrentRow).value = SaveStartDate;
    document.getElementById("SaveEndDate " + GetCurrentRow).value = SaveEndDate;




}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}