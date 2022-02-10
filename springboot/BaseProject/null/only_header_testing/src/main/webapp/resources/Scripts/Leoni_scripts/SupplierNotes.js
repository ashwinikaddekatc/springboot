function SupplierNotesClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveNoteFor" + GetCurrentRow).val('');

    $(".saveBranch" + GetCurrentRow).val('');
    $(".saveDescription" + GetCurrentRow).val('');
    $(".saveUser" + GetCurrentRow).val('');
   
    //    $(".sup_tax" + GetCurrentRow).val('');


   
    var saveNoteFor = "";

    var saveBranch = "";
    var saveDescription = "";
    var saveUser = ""
   
    //    var sup_tax = "";



}

function SupplierNotesCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveNoteFor = $(".saveNoteFor" + PreviousRow).val();

    var saveBranch = $(".saveBranch" + PreviousRow).val();
    var saveDescription = $(".saveDescription" + PreviousRow).val();
    var saveUser = $(".saveUser" + PreviousRow).val();
  
    //    var sup_tax = $(".sup_tax" + PreviousRow).val();


    //    var address_id = document.getElementById('address_id' + PreviousRow).value;

    $(".saveNoteFor" + GetCurrentRow).val(saveNoteFor);

    $(".saveBranch" + GetCurrentRow).val(saveBranch);
    $(".saveDescription" + GetCurrentRow).val(saveDescription);
    $(".saveUser" + GetCurrentRow).val(saveUser);
   
    //    $(".sup_tax" + GetCurrentRow).val(sup_tax);


    //    document.getElementById("address_id" + GetCurrentRow).value = address_id;

}

function SupplierNotesSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Nsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Nsr_' + rowNo).css('background-color', '#BCC6D7');


}