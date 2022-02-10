function NotesClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".saveNoteFor" + GetCurrentRow).val('');
    $(".saveBranch" + GetCurrentRow).val('');
    $(".saveDescription" + GetCurrentRow).val('');
    $(".saveUser" + GetCurrentRow).val('');
     // document.getElementById("srNo " + GetCurrentRow).value = '';



    var saveNoteFor = "";
    var saveBranch = "";
    var saveDescription = ""
    var saveUser = "";
    //var srNo = "";

}

function NotesCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveNoteFor = $(".saveNoteFor" + PreviousRow).val();
    var saveBranch = $(".saveBranch" + PreviousRow).val();
    var saveDescription = $(".saveDescription" + PreviousRow).val();
    var saveUser = $(".saveUser" + PreviousRow).val();
    //var srNo = document.getElementById('srNo' + PreviousRow).value;



    $(".saveNoteFor" + GetCurrentRow).val(saveNoteFor);
    $(".saveBranch" + GetCurrentRow).val(saveBranch);
    $(".saveDescription" + GetCurrentRow).val(saveDescription);
    $(".saveUser" + GetCurrentRow).val(saveUser);
    // document.getElementById("srNo " + GetCurrentRow).value = srNo;


}

function NotesSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#Nsr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#Nsr_' + rowNo).css('background-color', '#BCC6D7');


}
