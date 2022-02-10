function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();
   

    $(".saveJobNo" + GetCurrentRow).val('');
    $(".saveOP" + GetCurrentRow).val('');
    $(".saveOutPutComponent" + GetCurrentRow).val('');
    $(".saveQueued" + GetCurrentRow).val('');
    $(".savepassed" + GetCurrentRow).val('');
    $(".saveRejected" + GetCurrentRow).val('');
    $(".saveScraped" + GetCurrentRow).val('');
    $(".saveToRTV" + GetCurrentRow).val('');
   
   }

function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();
    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveJobNo = $(".saveJobNo" + PreviousRow).val();
    var saveOP = $(".saveOP" + PreviousRow).val();
    var saveOutPutComponent = $(".saveOutPutComponent" + PreviousRow).val();
    var saveQueued = $(".saveQueued" + PreviousRow).val();
    var savepassed = $(".savepassed" + PreviousRow).val();
    var saveRejected = $(".saveRejected" + PreviousRow).val();
    var saveScraped = $(".saveScraped" + PreviousRow).val();
    var saveToRTV = $(".saveToRTV" + PreviousRow).val();


    $(".saveJobNo" + GetCurrentRow).val(saveJobNo);
    $(".saveOP" + GetCurrentRow).val(saveOP);
    $(".saveOutPutComponent" + GetCurrentRow).val(saveOutPutComponent);
    $(".saveQueued" + GetCurrentRow).val(saveQueued);
    $(".savepassed" + GetCurrentRow).val(savepassed);
    $(".saveRejected" + GetCurrentRow).val(saveRejected);
    $(".saveScraped" + GetCurrentRow).val(saveScraped);
    $(".saveToRTV" + GetCurrentRow).val(saveToRTV);
    
}
