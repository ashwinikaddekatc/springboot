function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    
    $(".SaveFromCurrency" + GetCurrentRow).val('');
    $(".SaveToCurrency" + GetCurrentRow).val('');
    document.getElementById("rowDate" + GetCurrentRow).value = '';
    $(".SaveType" + GetCurrentRow).val('');
    $(".SaveUSDToINR" + GetCurrentRow).val('');
    $(".SaveINRToUSD" + GetCurrentRow).val('');

    
    var SaveFromCurrency = "";
    var SaveToCurrency = ""
    var rowDate = "";
    var SaveType = "";
    var SaveUSDToINR = ""
    var SaveINRToUSD = ""; 

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

   
    var SaveFromCurrency = $(".SaveFromCurrency" + PreviousRow).val();
    var SaveToCurrency = $(".SaveToCurrency" + PreviousRow).val();
    var rowDate  = document.getElementById('rowDate' + PreviousRow).value;
    var SaveType = $(".SaveType" + PreviousRow).val();
    var SaveUSDToINR = $(".SaveUSDToINR" + PreviousRow).val();
    var SaveINRToUSD = $(".SaveINRToUSD" + PreviousRow).val();   

  
    $(".SaveFromCurrency" + GetCurrentRow).val(SaveFromCurrency);
    $(".SaveToCurrency" + GetCurrentRow).val(SaveToCurrency);
    document.getElementById("rowDate" + GetCurrentRow).value = rowDate ;
    $(".SaveType" + GetCurrentRow).val(SaveType);
    $(".SaveUSDToINR" + GetCurrentRow).val(SaveUSDToINR);
    $(".SaveINRToUSD" + GetCurrentRow).val(SaveINRToUSD);
  

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}